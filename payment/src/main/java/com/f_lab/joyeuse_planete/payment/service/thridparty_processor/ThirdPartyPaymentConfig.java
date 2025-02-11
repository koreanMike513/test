package com.f_lab.joyeuse_planete.payment.service.thridparty_processor;

import com.f_lab.joyeuse_planete.payment.service.thridparty_processor.exceptions.ThirdPartyPaymentExceptionTranslator;
import com.f_lab.joyeuse_planete.payment.service.thridparty_processor.stripe.StripeExceptionTranslator;
import com.f_lab.joyeuse_planete.payment.service.thridparty_processor.stripe.StripePaymentProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ThirdPartyPaymentConfig {

  @Bean
  public ThirdPartyPaymentProcessor thirdPartyPaymentProcessor() {
    return new StripePaymentProcessor(exceptionTranslator());
  }

  @Bean
  public ThirdPartyPaymentExceptionTranslator exceptionTranslator() {
    return new StripeExceptionTranslator();
  }
}
