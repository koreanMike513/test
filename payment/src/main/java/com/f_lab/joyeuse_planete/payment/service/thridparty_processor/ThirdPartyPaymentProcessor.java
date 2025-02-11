package com.f_lab.joyeuse_planete.payment.service.thridparty_processor;

import com.f_lab.joyeuse_planete.core.events.OrderCreatedEvent.PaymentInformation;
import com.f_lab.joyeuse_planete.payment.service.thridparty_processor.exceptions.ThirdPartyPaymentException;

public interface ThirdPartyPaymentProcessor {

  void process(PaymentInformation paymentInformation) throws ThirdPartyPaymentException;
}
