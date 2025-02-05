package com.f_lab.joyeuse_planete.core.domain;

import com.f_lab.joyeuse_planete.core.exceptions.JoyeusePlaneteApplicationException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class FoodTest {

  @Test
  @DisplayName("음식 수량을 줄였을 때 성공")
  void test_minus_food_quantity_success() {
    // given
    BigDecimal price = BigDecimal.valueOf(1000);
    int quantity = 10;
    Food food = createFood(price, quantity);

    // when
    food.minusQuantity(5);

    // then
    assertThat(food.getTotalQuantity()).isEqualTo(5);
  }

  @Test
  @DisplayName("음식 수량을 줄였을 때 수량보다 많아서 실패할 때 예외 던짐")
  void test_minus_food_quantity_fail() {
    // given
    BigDecimal price = BigDecimal.valueOf(1000);
    int quantity = 10;
    Food food = createFood(price, quantity);

    // then
    assertThatThrownBy(() -> food.minusQuantity(11))
        .isInstanceOf(JoyeusePlaneteApplicationException.class);
  }

  @Test
  @DisplayName("tag를 setter로 등록하고 getter로 설정한대로 가져오는 것을 확인")
  void testSetterTagsAndGetterTagsSuccess() {
    // given
    BigDecimal price = BigDecimal.valueOf(1000);
    int quantity = 10;
    Food food = createFood(price, quantity);
    List<String> expected = List.of("chicken", "good", "foods", "seoul");
    food.setTags(expected);

    // when
    List<String> tags = food.getTags();

    // then
    assertThat(tags).isEqualTo(expected);
  }

  private Food createFood(BigDecimal price, int quantity) {
    return Food.builder()
        .price(price)
        .totalQuantity(quantity)
        .build();
  }
}