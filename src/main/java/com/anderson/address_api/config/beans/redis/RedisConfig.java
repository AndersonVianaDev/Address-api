package com.anderson.address_api.config.beans.redis;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.Jedis;

@Configuration
public class RedisConfig {

    @Value("${spring.host.redis}")
    private String hostRedis;

    @Value("${spring.port.redis}")
    private int portRedis;

    @Bean
    public Jedis jedis() {
        return new Jedis(hostRedis, portRedis);
    }

}
