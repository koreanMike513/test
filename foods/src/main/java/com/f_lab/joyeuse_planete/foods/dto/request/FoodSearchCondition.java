package com.f_lab.joyeuse_planete.foods.dto.request;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class FoodSearchCondition {
  // TODO 거리 추가 구현

  Double lat = London.lat;

  Double lon = London.lon;

  String search;

  @Min(value = 0, message = "")
  int page = 0;

  @Min(value = 0, message = "")
  @Max(value = 100, message = "")
  int size = 25;

  List<String> sortBy = List.of("RATE_HIGH");

  // 위도 경도 DEFAULT SET TO LONDON
  static class London {
    private static final Double lat = 51.5072;
    private static final Double lon = -0.118092;
  }
}
