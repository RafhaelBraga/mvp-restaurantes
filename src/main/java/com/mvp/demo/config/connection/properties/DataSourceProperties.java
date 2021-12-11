package com.mvp.demo.config.connection.properties;
import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
@ConfigurationProperties(prefix = "spring")
public class DataSourceProperties {
    private List<DataSourceProperty> datasources = new ArrayList<>();
}
