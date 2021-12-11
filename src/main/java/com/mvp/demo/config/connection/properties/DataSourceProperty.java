package com.mvp.demo.config.connection.properties;

import lombok.Data;

@Data
public class DataSourceProperty {
    private String name;
    private Boolean dataSourceDefault;
    private String url;
    private String username;
    private String password;
    private String driverClassName;
}
