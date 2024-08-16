package com.rk.demo_jwt.repos.mongo;

import com.rk.demo_jwt.models.mongo.WeatherObsMongoEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface WeatherMongoRepo extends MongoRepository<WeatherObsMongoEntity,String> {
}
