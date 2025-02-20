package com.f_lab.joyeuse_planete.foods.config;

import com.f_lab.joyeuse_planete.foods.dto.request.FoodSearchCondition;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CachingConfigurer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;

import java.time.Duration;

import static com.f_lab.joyeuse_planete.core.util.time.TimeConstantsString.THIRTY_MINUTES;

@EnableCaching
@Configuration
public class CacheConfig {

  @Bean
  public RedisCacheManager redisCacheManager(RedisConnectionFactory connectionFactory) {
    RedisCacheConfiguration redisCacheConfiguration = RedisCacheConfiguration.defaultCacheConfig()
        .entryTtl(Duration.ofMillis(Long.parseLong(THIRTY_MINUTES)))
        .disableCachingNullValues();

    return RedisCacheManager.builder(connectionFactory)
        .cacheDefaults(redisCacheConfiguration)
        .build();
  }

  @Bean
  public KeyGenerator foodSearchKeyGenerator() {
    return (target, method, params) -> {
      FoodSearchCondition condition = (FoodSearchCondition) params[0];

      String key = String.format(
          "lat:%f-lon:%f-search:%s-page:%d-size:%d-sortBy:%s",
          condition.getLat(),
          condition.getLon(),
          condition.getSearch() != null ? condition.getSearch() : "NONE",
          condition.getPage(),
          condition.getSize(),
          String.join(",", condition.getSortBy())
      );

      return key;
    };
  }
}
