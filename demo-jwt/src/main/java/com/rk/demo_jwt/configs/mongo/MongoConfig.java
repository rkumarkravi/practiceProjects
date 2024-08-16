package com.rk.demo_jwt.configs.mongo;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableMongoRepositories(
        basePackages = "com.rk.demo_jwt.repos.mongo"
)
public class MongoConfig {
}
