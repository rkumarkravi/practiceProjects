package com.rk.demo_jwt.configs.oracle;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "spring.datasource.ora")
public class OracleDbProps {
    private String url;
    private String username;
    private String password;
    private String driverClassName;
    private String dialect;
    private String ddl;
}
