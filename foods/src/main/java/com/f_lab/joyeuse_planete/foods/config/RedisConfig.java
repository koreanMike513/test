package com.f_lab.joyeuse_planete.foods.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

@Configuration
public class RedisConfig {

  @Value("${spring.data.redis.host}")
  private String REDIS_HOST;

  @Value("${spring.data.redis.port}")
  private int REDIS_PORT;

  @Value("${spring.data.redis.password}")
  private String REDIS_PASSWORD;

  @Bean
  public RedisConnectionFactory redisConnectionFactory() {
    RedisStandaloneConfiguration config = new RedisStandaloneConfiguration(REDIS_HOST, REDIS_PORT);
    config.setPassword(REDIS_PASSWORD);

    return new LettuceConnectionFactory(config);
  }

  @Bean
  public RedisTemplate<String, String> redisTemplate(RedisConnectionFactory connectionFactory) {
    RedisTemplate<String, String> template = new RedisTemplate<>();
    template.setConnectionFactory(connectionFactory);
    template.setEnableTransactionSupport(true);
    return template;
  }
}
