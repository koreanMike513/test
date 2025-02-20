package com.f_lab.joyeuse_planete.foods.repository;

import com.f_lab.joyeuse_planete.foods.dto.request.FoodSearchCondition;
import com.f_lab.joyeuse_planete.foods.dto.response.FoodDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface FoodCustomRepository {

  Page<FoodDTO> getFoodList(FoodSearchCondition condition, Pageable pageable);
}
