package com.f_lab.joyeuse_planete.foods.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@EntityScan(basePackages = { "com.f_lab.joyeuse_planete.core" })
public class JpaEntityScanConfig {
}
