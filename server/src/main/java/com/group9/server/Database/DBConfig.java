package com.group9.server.Database;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@Configuration
@PropertySource("classpath:JDBC.properties")
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
}
