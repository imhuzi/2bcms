package com.tbcms.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.bind.RelaxedPropertyResolver;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

/**
 * 数据库配置bean
 *
 * @author : Hui.Wang [huzi.wh@gmail.com]
 * @version : 1.0
 * @created on  : 2016-01-12,  下午20:50
 */
@Configuration
@EnableTransactionManagement
public class DataBaseConfiguration implements EnvironmentAware {

    private RelaxedPropertyResolver propertyResolver;
    private static Logger log = LoggerFactory.getLogger(DataBaseConfiguration.class);

    @Override
    public void setEnvironment(Environment environment) {
        this.propertyResolver = new RelaxedPropertyResolver(environment, "spring.datasource.");
    }

    /**
     * spring dataSource bean config
     *
     * @return
     */
    @Bean(name = "dataSource", destroyMethod = "close", initMethod = "init")
    public DataSource dataSource() {
        log.debug("Configruing Write DataSource");
        log.debug("Database url: "  + propertyResolver.getProperty("url"));
        DruidDataSource datasource = new DruidDataSource();
        datasource.setUrl(propertyResolver.getProperty("url"));
        datasource.setDriverClassName(propertyResolver.getProperty("driver-class-name"));
        datasource.setUsername(propertyResolver.getProperty("username"));
        datasource.setPassword(propertyResolver.getProperty("password"));
        return datasource;
    }
}
