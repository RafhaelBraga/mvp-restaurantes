package com.mvp.demo.config.connection;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.springframework.web.filter.GenericFilterBean;

public class DataSourceFilter extends GenericFilterBean {

    static final String DATA_SOURCE_DEFFAULT = "MVP";
    
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {

        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        String clientId = httpServletRequest.getHeader("clientId");

        if (clientId == null || clientId.equals("")) {
            clientId = DATA_SOURCE_DEFFAULT;
        }

        DataSourceLocalStorage.setDataSourceName(clientId);
        filterChain.doFilter(servletRequest, servletResponse);
    }
}
