package com.epam.chadov.task3.xml.database;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Configuration
public class NewsOracleDao {

    @Bean
    public NewsOracleDao newsOracleDao() {
        return new NewsOracleDao();
    }
}
