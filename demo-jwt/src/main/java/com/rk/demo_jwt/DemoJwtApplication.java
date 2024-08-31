package com.rk.demo_jwt;

import com.rk.demo_jwt.exchanges.TestExchange;
import com.rk.demo_jwt.models.oracle.PostEntity;
import com.rk.demo_jwt.models.oracle.UserEntity;
import com.rk.demo_jwt.repos.oracle.PostEntityRepository;
import com.rk.demo_jwt.repos.oracle.UserEntityRepository;
import com.rk.demo_jwt.services.DbTestService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

import javax.sql.DataSource;
import java.util.List;

@SpringBootApplication
@Slf4j
//@EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class})
public class DemoJwtApplication implements CommandLineRunner {

    @Autowired
    DbTestService dbTestService;

    @Autowired
    TestExchange testExchange;

    @Autowired
    UserEntityRepository userEntityRepository;

    @Autowired
    PostEntityRepository postEntityRepository;


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

//        testExchange.getAll().subscribe(x->{log.info(String.valueOf(x));});


       var post = postEntityRepository.save(PostEntity.builder().
                postDesc("First Post")
                .postType("TEXT")
                .build());

        userEntityRepository.save(UserEntity.builder()
                .postEntity( 
                       List.of( post)
                )
                        .name("user1")
                .build());
        userEntityRepository.findAll().forEach(System.out::println);
    }
}
