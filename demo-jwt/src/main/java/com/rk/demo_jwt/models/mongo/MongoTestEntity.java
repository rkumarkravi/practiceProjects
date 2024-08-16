package com.rk.demo_jwt.models.mongo;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "mongo_test_collection")
@Data
public class MongoTestEntity {

    @Id
    private String id;
    private String name;

    // Getters and Setters
}