package com.f_lab.joyeuse_planete.orders.service;

import com.f_lab.joyeuse_planete.core.domain.Order;
import com.f_lab.joyeuse_planete.core.domain.OrderStatus;
import com.f_lab.joyeuse_planete.core.exceptions.ErrorCode;
import com.f_lab.joyeuse_planete.core.exceptions.JoyeusePlaneteApplicationException;
import com.f_lab.joyeuse_planete.core.kafka.service.KafkaService;
import com.f_lab.joyeuse_planete.core.util.log.LogUtil;
import com.f_lab.joyeuse_planete.orders.domain.OrderSearchCondition;
import com.f_lab.joyeuse_planete.orders.dto.response.OrderDTO;
import com.f_lab.joyeuse_planete.orders.dto.request.OrderCreateRequestDTO;
import com.f_lab.joyeuse_planete.orders.dto.response.OrderCreateResponseDTO;
import com.f_lab.joyeuse_planete.orders.repository.OrderRepository;
import io.micrometer.core.annotation.Timed;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Timed("orders")
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class OrderService {

  private final OrderRepository orderRepository;
  private final KafkaService kafkaService;

  @Value("${orders.events.topic.name}")
  String ORDER_CREATED_EVENT;

  public Page<OrderDTO> getOrderList(OrderSearchCondition condition, Pageable pageable) {
    return orderRepository.findOrders(condition, pageable);
  }

  public OrderDTO getOrder(Long orderId) {
    OrderDTO orderDTO = orderRepository.getOrder(orderId);

    if (orderDTO == null)
      throw new JoyeusePlaneteApplicationException(ErrorCode.ORDER_NOT_EXIST_EXCEPTION);

    return orderDTO;
  }

  @Transactional
  public void deleteOrderByMember(Long orderId) {
    Order order;
    try {
      order = updateOrderStatus(orderId, OrderStatus.MEMBER_CANCELED);
      order.setDeleted(true);
      orderRepository.save(order);

    } catch (Exception e) {

    }

    sendKafkaOrderCancellationEvent();
  }

  @Transactional
  public Order updateOrderStatus(Long orderId, OrderStatus status) {
    Order order = findOrderById(orderId);
    order.setStatus(status);
    orderRepository.save(order);

    return order;
  }

  @Transactional
  public OrderCreateResponseDTO createFoodOrder(OrderCreateRequestDTO request) {
    Order order;
    try {
      order = orderRepository.saveOrder(request);

    } catch (JoyeusePlaneteApplicationException e) {
      LogUtil.exception("OrderService.createFoodOrder", e);
      throw e;

    } catch (Exception e) {
      LogUtil.exception("OrderService.createFoodOrder", e);
      throw new RuntimeException(e);
    }

    sendKafkaOrderCreatedEvent(request, order);

    return new OrderCreateResponseDTO("PROCESSING");
  }


  public void sendKafkaOrderCreatedEvent(OrderCreateRequestDTO request, Order order) {
    try {
      kafkaService.sendKafkaEvent(ORDER_CREATED_EVENT, request.toEvent(order.getId()));

    } catch(JoyeusePlaneteApplicationException e) {
      LogUtil.exception("OrderService.sendKafkaOrderCreatedEvent", e);
      throw e;

    } catch(Exception e) {
      LogUtil.exception("OrderService.sendKafkaOrderCreatedEvent", e);
      throw new RuntimeException(e);
    }
  }

  public void sendKafkaOrderCancellationEvent() {

//    try {
//      kafkaService.sendKafkaEvent();
//    }
  }

  private Order findOrderById(Long orderId) {
    return orderRepository.findById(orderId)
        .orElseThrow(() -> new JoyeusePlaneteApplicationException(ErrorCode.ORDER_NOT_EXIST_EXCEPTION));
  }
}

