package com.rk.demo_jwt;

import com.rk.demo_jwt.exchanges.TestExchange;
import com.rk.demo_jwt.services.DbTestService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

import javax.sql.DataSource;

@SpringBootApplication
@Slf4j
//@EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class})
public class DemoJwtApplication implements CommandLineRunner {

    @Autowired
    DbTestService dbTestService;

    @Autowired
    TestExchange testExchange;


    public static void main(String[] args) {
        SpringApplication.run(DemoJwtApplication.class, args);
        log.info("Started main!!!");
    }

    @Override
    public void run(String... args) throws Exception {
		log.info("Started run!!!");
//        dbTestService.addDataOracle();
//        dbTestService.addDataMongo();
//
//
//        log.info("oracle data: {}",dbTestService.getAllOracleList());
//        log.info("mongo data: {}",dbTestService.getAllMongoList());
//
//        dbTestService.weatherObsMongoEntities().forEach(System.out::println);

        testExchange.getAll().subscribe(x->{log.info(String.valueOf(x));});
    }
}
