package com.tbcms.config;

import com.github.pagehelper.PageHelper;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.bind.RelaxedPropertyResolver;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.sql.DataSource;
import java.util.Properties;

/**
 * mybatis config
 *
 * @author : Hui.Wang [huzi.wh@gmail.com]
 * @version : 1.0
 * @created on  : 2016-01-13,  下午11:16
 */

@Configuration
@ConditionalOnClass({EnableTransactionManagement.class, EntityManager.class})
@AutoConfigureAfter({DataBaseConfiguration.class})
@MapperScan(basePackages = {"com.tbcms.dao"})
public class MyBatisConfiguration implements EnvironmentAware {

    private static Logger log = LoggerFactory.getLogger(MyBatisConfiguration.class);

    private RelaxedPropertyResolver propertyResolver;

    @Resource(name = "dataSource")
    private DataSource dataSource;

    @Override
    public void setEnvironment(Environment environment) {
        this.propertyResolver = new RelaxedPropertyResolver(environment, "spring.mybatis.");
    }

    @Bean
    @ConditionalOnMissingBean
    public SqlSessionFactory sqlSessionFactory() {
        try {
            SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
            sessionFactory.setDataSource(dataSource);
            sessionFactory.setTypeAliasesPackage(propertyResolver.getProperty("typeAliasesPackage"));
            sessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver().getResources(propertyResolver.getProperty("mapperLocations")));
            //分页插件配置
            PageHelper pageHelper = new PageHelper();
            Properties properties = new Properties();
            properties.put("dialect","mysql");
            pageHelper.setProperties(properties);

            sessionFactory.setPlugins(new Interceptor[]{pageHelper});
            return sessionFactory.getObject();
        } catch (Exception e) {
            log.warn("Could not confiure mybatis session factory");
            return null;
        }
    }

    @Bean
    @ConditionalOnMissingBean
    public DataSourceTransactionManager transactionManager() {
        return new DataSourceTransactionManager(dataSource);
    }
}
