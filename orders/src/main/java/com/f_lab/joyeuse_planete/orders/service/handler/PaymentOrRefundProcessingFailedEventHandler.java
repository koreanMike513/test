package com.f_lab.joyeuse_planete.orders.service.handler;


import com.f_lab.joyeuse_planete.core.events.PaymentOrRefundProcessingFailedEvent;
import com.f_lab.joyeuse_planete.core.exceptions.ErrorCodeOrderStatusTranslator;
import com.f_lab.joyeuse_planete.core.kafka.service.KafkaService;
import com.f_lab.joyeuse_planete.orders.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@KafkaListener(topics = { "${payment.events.topics.process-fail}", "${payment.events.topics.refund-fail}" }, groupId = "${spring.kafka.consumer.group-id}")
public class PaymentOrRefundProcessingFailedEventHandler {

  private final OrderService orderService;

  @KafkaHandler
  public void processPaymentOrRefundProcessingFailedEvent(@Payload PaymentOrRefundProcessingFailedEvent event) {
    orderService.updateOrderStatus(
        event.getOrderId(),
        ErrorCodeOrderStatusTranslator.translate(event.getErrorCode()));
  }
}
