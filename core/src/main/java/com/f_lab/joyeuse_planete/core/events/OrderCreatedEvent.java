package com.f_lab.joyeuse_planete.core.events;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderCreatedEvent {

  private Long orderId;

  private Long foodId;

  private String foodName;

  private Long storeId;

  private int quantity;

  private BigDecimal totalAmount;

  private Long voucherId;

  private PaymentInformation paymentInformation;

  @Data
  @Builder
  @NoArgsConstructor
  @AllArgsConstructor
  public static class PaymentInformation {
    private String cardNumber;
    private String cardHolderName;
    private String expiryDate;
    private String cvc;
  }
}
