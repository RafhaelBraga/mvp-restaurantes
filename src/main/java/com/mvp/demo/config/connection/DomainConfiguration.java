package com.mvp.demo.config.connection;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.mvp.demo.config.connection.properties.DataSourceProperties;


//@Configuration
@EnableTransactionManagement
@EntityScan(basePackages = { "com.mvp.demo" })
@EnableJpaRepositories(basePackages = { "com.mvp.demo" })
public class DomainConfiguration {

    @Bean
    @Primary
    public DataSource dataSource(DataSourceProperties dataSourceProperties) {
        AbstractRoutingDataSource dataSourceRouting = new DataSourceRouting();
        Map<Object, Object> targetDataSources = new HashMap<>();

        dataSourceProperties.getDatasources().forEach(dataSourceProperty -> {
            DataSource dataSource = DataSourceBuilder.create().url(dataSourceProperty.getUrl())
                    .username(dataSourceProperty.getUsername()).password(dataSourceProperty.getPassword())
                    .driverClassName(dataSourceProperty.getDriverClassName()).build();
            if (dataSourceProperty.getDataSourceDefault().booleanValue()) {
                dataSourceRouting.setDefaultTargetDataSource(dataSource);
            }
            targetDataSources.put(dataSourceProperty.getName(), dataSource);
        });
        dataSourceRouting.setTargetDataSources(targetDataSources);
        dataSourceRouting.afterPropertiesSet();
        return dataSourceRouting;
    }
}
