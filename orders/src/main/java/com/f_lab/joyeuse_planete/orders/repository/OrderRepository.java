package com.f_lab.joyeuse_planete.orders.repository;


import com.f_lab.joyeuse_planete.core.domain.Order;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order, Long>, OrderRepositoryCustom {

  @Override
  @EntityGraph(attributePaths = { "food", "payment", "voucher" })
  Optional<Order> findById(Long orderId);
}
