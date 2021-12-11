package com.mvp.demo.config.connection;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//@Configuration
public class DataSourceConfiguration {

    @Bean
    public FilterRegistrationBean<DataSourceFilter> dawsonApiFilter() {
        FilterRegistrationBean<DataSourceFilter> registration = new FilterRegistrationBean<>();
        registration.setFilter(new DataSourceFilter());
        return registration;
    }
}
