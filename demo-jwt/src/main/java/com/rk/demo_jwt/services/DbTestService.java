package com.rk.demo_jwt.services;

import com.rk.demo_jwt.models.mongo.MongoTestEntity;
import com.rk.demo_jwt.models.mongo.WeatherObsMongoEntity;
import com.rk.demo_jwt.models.oracle.OracleTestEntity;
import com.rk.demo_jwt.repos.mongo.TestMongoRepo;
import com.rk.demo_jwt.repos.mongo.WeatherMongoRepo;
import com.rk.demo_jwt.repos.oracle.TestOracleRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DbTestService {
    @Autowired
    TestMongoRepo testMongoRepo;

    @Autowired
    TestOracleRepo testOracleRepo;

    @Autowired
    WeatherMongoRepo weatherMongoRepo;

    public void addDataOracle(){
        OracleTestEntity oracleTestEntity=new OracleTestEntity();
        oracleTestEntity.setName("abc"+Math.random());

        testOracleRepo.save(oracleTestEntity);
    }

    public void addDataMongo(){
        MongoTestEntity mongoTestEntity=new MongoTestEntity();
        mongoTestEntity.setName("abc"+Math.random());

        testMongoRepo.save(mongoTestEntity);
    }


    public List<OracleTestEntity> getAllOracleList(){
        return testOracleRepo.findAll();
    }

    public List<MongoTestEntity> getAllMongoList(){
        return testMongoRepo.findAll();
    }

    public List<WeatherObsMongoEntity> weatherObsMongoEntities(){
        return weatherMongoRepo.findAll();
    }
}
