package com.f_lab.joyeuse_planete.orders.controller;

import com.f_lab.joyeuse_planete.core.util.web.CommonResponses;
import com.f_lab.joyeuse_planete.orders.dto.response.OrderDTO;
import com.f_lab.joyeuse_planete.orders.service.OrderService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;


import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@WebMvcTest(OrderController.class)
class OrderControllerTest {

  @Autowired
  MockMvc mockMvc;
  @MockitoBean
  OrderService orderService;

  @Autowired
  ObjectMapper objectMapper;

  private static final String ORDERS_URL_PREFIX = "/api/v1/orders";

  @Test
  void a() throws Exception {
    mockMvc.perform(get(ORDERS_URL_PREFIX + "/ping"))
        .andExpect(status().isOk())
        .andExpect(content().string(CommonResponses.PONG));
  }

  @Test
  void b() throws Exception {
    // given
    Long orderId = 1L;
    OrderDTO expected = createOrderDTO(orderId);
    String expectedJson = objectMapper.writeValueAsString(expected);

    // when
    when(orderService.getOrder(anyLong())).thenReturn(expected);

    mockMvc.perform(get(ORDERS_URL_PREFIX + "/" + orderId))
        .andExpect(status().isOk())
        .andExpect(content().json(expectedJson));
  }

  private OrderDTO createOrderDTO(Long orderId) {
    OrderDTO orderDTO = new OrderDTO();
    orderDTO.setOrderId(orderId);
    return orderDTO;
  }
}