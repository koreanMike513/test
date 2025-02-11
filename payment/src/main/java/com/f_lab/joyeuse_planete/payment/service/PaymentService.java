package com.f_lab.joyeuse_planete.payment.service;

import com.f_lab.joyeuse_planete.payment.service.thridparty_processor.ThirdPartyPaymentProcessor;
import com.f_lab.joyeuse_planete.payment.service.thridparty_processor.exceptions.IncorrectDetailsException;
import com.f_lab.joyeuse_planete.payment.service.thridparty_processor.exceptions.LostOrStolenCardException;
import com.f_lab.joyeuse_planete.payment.service.thridparty_processor.exceptions.NotEnoughFundException;
import com.f_lab.joyeuse_planete.payment.service.thridparty_processor.exceptions.ThirdPartyPaymentException;
import io.micrometer.core.annotation.Timed;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Timed("payment")
@Service
@RequiredArgsConstructor
public class PaymentService {

  private final ThirdPartyPaymentProcessor paymentProcessor;

  public void process() {

    try {
      paymentProcessor.process(null);
    } catch (IncorrectDetailsException | NotEnoughFundException e) {

    } catch (LostOrStolenCardException e) {

    } catch (ThirdPartyPaymentException e) {

    } catch (Exception e) {

    }
  }
}
