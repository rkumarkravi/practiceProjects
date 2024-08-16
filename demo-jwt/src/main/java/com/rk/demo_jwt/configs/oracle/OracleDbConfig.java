package com.rk.demo_jwt.configs.oracle;

import jakarta.persistence.EntityManagerFactory;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableJpaRepositories(
        basePackages = "com.rk.demo_jwt.repos.oracle",
        entityManagerFactoryRef = "oracleEntityManagerFactory",
        transactionManagerRef = "oracleTransactionManager"
)
public class OracleDbConfig {
    @Bean(name = "oracleDataSource")
    public DataSource dataSourceOra(OracleDbProps props) {
        return DataSourceBuilder.create()
                .url(props.getUrl())
                .username(props.getUsername())
                .password(props.getPassword())
                .driverClassName(props.getDriverClassName())
                .build();
    }

    /*@Bean(name = "mysqlDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.mysql")
    public DataSource dataSourceMySql(){
        return DataSourceBuilder.create().build();
    }*/

    @Bean(name = "oracleEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean mysqlEntityManagerFactory(
            EntityManagerFactoryBuilder builder,
            DataSource oracleDataSource, OracleDbProps props) {
        Map<String, Object> properties = new HashMap<>();
        properties.put("hibernate.dialect", props.getDialect());
        properties.put("hibernate.hbm2ddl.auto", props.getDdl());

        return builder
                .dataSource(oracleDataSource)
                .packages("com.rk.demo_jwt.models.oracle")
                .persistenceUnit("oraclePU")
                .properties(properties)
                .build();
    }

    @Bean(name = "oracleTransactionManager")
    public PlatformTransactionManager mysqlTransactionManager(
            EntityManagerFactory mysqlEntityManagerFactory) {
        return new JpaTransactionManager(mysqlEntityManagerFactory);
    }
}
