package com.f_lab.joyeuse_planete.foods.controller;

import com.f_lab.joyeuse_planete.core.exceptions.JoyeusePlaneteApplicationException;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.resource.NoResourceFoundException;

@RestControllerAdvice
public class GlobalExceptionHandler {


  @ExceptionHandler(JoyeusePlaneteApplicationException.class)
  public ResponseEntity<ErrorResponse> handleJoyeusePlaneteApplicationException(JoyeusePlaneteApplicationException e) {
    return ResponseEntity.status(HttpStatus.BAD_REQUEST)
        .body(ErrorResponse.of(e.getMessage(), HttpStatus.BAD_REQUEST.value()));
  }

  @ExceptionHandler(NoResourceFoundException.class)
  public ResponseEntity<ErrorResponse> handelResourceNotFoundException() {
    return ResponseEntity
        .status(HttpStatus.NOT_FOUND)
        .body(ErrorResponse.of("올바르지 않은 주소입니다. 다시 확인해 주세요", HttpStatus.NOT_FOUND.value()));
  }


  @Getter
  @AllArgsConstructor
  static class ErrorResponse {
    private String message;

    @JsonProperty("status_code")
    private int statusCode;

    public static ErrorResponse of(String message, int statusCode) {
      return new ErrorResponse(message, statusCode);
    }
  }
}
