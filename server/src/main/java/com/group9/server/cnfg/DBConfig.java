package com.group9.server.cnfg;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

@Configuration
@PropertySource("classpath:JDBC.properties")
@Component
public class DBConfig {
    @Value("${JDBC.driver}")
    public String driverClassName;
    @Value("${JDBC.url}")
    public String url;
    @Value("${JDBC.user}")
    public String user;
    @Value("${JDBC.password}")
    public String password;

    @Bean
    public DataSource dataSources() {
        DriverManagerDataSource bs = new DriverManagerDataSource();
        bs.setDriverClassName(driverClassName);
        bs.setUrl(url);
        bs.setUsername(user);
        bs.setPassword(password);
        return bs;

    }

    public JdbcTemplate jdbcTemplate(DataSource dataSources) {
        return new JdbcTemplate(dataSources);
    }
}
