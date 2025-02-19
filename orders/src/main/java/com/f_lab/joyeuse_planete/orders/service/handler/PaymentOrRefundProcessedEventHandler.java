package com.f_lab.joyeuse_planete.orders.service.handler;


import com.f_lab.joyeuse_planete.core.domain.OrderStatus;
import com.f_lab.joyeuse_planete.core.events.PaymentOrRefundProcessedEvent;
import com.f_lab.joyeuse_planete.orders.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PaymentOrRefundProcessedEventHandler {

  private final OrderService orderService;

  @KafkaListener(topics = { "${payment.events.topics.process}" }, groupId = "${spring.kafka.consumer.group-id}")
  public void processPaymentProcessedEvent(@Payload PaymentOrRefundProcessedEvent event) {
    orderService.updateOrderStatus(event.getOrderId(), OrderStatus.DONE);
  }

  @KafkaListener(topics = { "${payment.events.topics.refund}" }, groupId = "${spring.kafka.consumer.group-id}")
  public void processRefundProcessedEvent(@Payload PaymentOrRefundProcessedEvent event) {
    orderService.updateOrderStatus(event.getOrderId(), OrderStatus.MEMBER_CANCELED);
  }
}
