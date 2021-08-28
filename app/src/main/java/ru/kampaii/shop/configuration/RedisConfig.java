package ru.kampaii.shop.configuration;

import org.springframework.cloud.aws.cache.config.annotation.CacheClusterConfig;
import org.springframework.cloud.aws.cache.config.annotation.EnableElastiCache;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableElastiCache({@CacheClusterConfig(name = "product-cache")})
public class RedisConfig {
}
