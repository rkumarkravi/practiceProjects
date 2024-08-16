package com.rk.demo_jwt.repos.mongo;

import com.rk.demo_jwt.models.mongo.MongoTestEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TestMongoRepo extends MongoRepository<MongoTestEntity,String> {
}
