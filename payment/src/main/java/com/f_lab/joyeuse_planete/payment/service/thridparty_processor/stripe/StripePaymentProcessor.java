package com.f_lab.joyeuse_planete.payment.service.thridparty_processor.stripe;

import com.f_lab.joyeuse_planete.core.events.OrderCreatedEvent.PaymentInformation;
import com.f_lab.joyeuse_planete.payment.service.thridparty_processor.ThirdPartyPaymentProcessor;
import com.f_lab.joyeuse_planete.payment.service.thridparty_processor.exceptions.ThirdPartyPaymentException;
import com.f_lab.joyeuse_planete.payment.service.thridparty_processor.exceptions.ThirdPartyPaymentExceptionTranslator;
import lombok.RequiredArgsConstructor;



@RequiredArgsConstructor
public class StripePaymentProcessor implements ThirdPartyPaymentProcessor {

  private final ThirdPartyPaymentExceptionTranslator translator;

  @Override
  public void process(PaymentInformation paymentInformation) throws ThirdPartyPaymentException {


    try {

    } catch (Exception e) {
//      translator.translate(e);
    }
  }
}
