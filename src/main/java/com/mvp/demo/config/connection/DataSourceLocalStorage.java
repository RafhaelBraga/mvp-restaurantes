package com.mvp.demo.config.connection;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class DataSourceLocalStorage {

    private static ThreadLocal<String> dataSource = new ThreadLocal<>();

    public static String getDataSourceName() {
        return dataSource.get();
    }

    public static void setDataSourceName(String dataSourceName) {
        dataSource.set(dataSourceName);
    }

    public void unload() {
        dataSource.remove();
    }
}
