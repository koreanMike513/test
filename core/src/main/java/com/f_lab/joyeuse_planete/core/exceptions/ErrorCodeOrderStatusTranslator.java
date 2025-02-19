package com.f_lab.joyeuse_planete.core.exceptions;

import com.f_lab.joyeuse_planete.core.domain.OrderStatus;

import java.util.Map;

public class ErrorCodeOrderStatusTranslator {

  private static final Map<ErrorCode, OrderStatus> ERROR_CODE_MAP = Map.ofEntries(
      Map.entry(ErrorCode.FOOD_NOT_ENOUGH_STOCK, OrderStatus.FAIL_FOOD_QUANTITY),
      Map.entry(ErrorCode.FOOD_NOT_EXIST_EXCEPTION, OrderStatus.FAIL_FOOD_INVALID_ID),

      Map.entry(ErrorCode.PAYMENT_NOT_EXIST_EXCEPTION, OrderStatus.PENDING_PAYMENT),
      Map.entry(ErrorCode.PAYMENT_NOT_SUPPORTED, OrderStatus.PENDING_PAYMENT),
      Map.entry(ErrorCode.PAYMENT_INVALID_STOPPED_CARD_EXCEPTION, OrderStatus.PENDING_PAYMENT),
      Map.entry(ErrorCode.INVALID_REJECT_CARD, OrderStatus.PENDING_PAYMENT),
      Map.entry(ErrorCode.EXCEED_MAX_AUTH_COUNT, OrderStatus.PENDING_PAYMENT),
      Map.entry(ErrorCode.NOT_FOUND_PAYMENT, OrderStatus.PENDING_PAYMENT),
      Map.entry(ErrorCode.EXCEED_MAX_AMOUNT, OrderStatus.PENDING_PAYMENT),
      Map.entry(ErrorCode.UNAUTHORIZED_KEY, OrderStatus.PENDING_PAYMENT),
      Map.entry(ErrorCode.REJECT_ACCOUNT_PAYMENT, OrderStatus.PENDING_PAYMENT),
      Map.entry(ErrorCode.INVALID_PASSWORD, OrderStatus.PENDING_PAYMENT),
      Map.entry(ErrorCode.PAYMENT_UNKNOWN_EXCEPTION, OrderStatus.PENDING_PAYMENT)
  );

  public static OrderStatus translate(ErrorCode errorCode) {
    return ERROR_CODE_MAP.getOrDefault(errorCode, OrderStatus.FAIL);
  }
}


