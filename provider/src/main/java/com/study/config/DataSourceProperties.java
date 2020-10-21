package com.study.config;

import com.zaxxer.hikari.HikariDataSource;
import lombok.Data;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Component;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.sql.SQLException;

@Component
@Data
@Configuration
@RefreshScope
public class DataSourceProperties {

    @Value("${spring.datasource.url}")
    private String url;
    @Value("${spring.datasource.username}")
    private String username;
    @Value("${spring.datasource.password}")
    private String password;
    @Value("${spring.datasource.driver-class-name}")
    private String driverClassName;
    @Value("${spring.datasource.hikari.ready-only: false}")
    private boolean readOnly;
    @Value("${spring.datasource.hikari.connection-timeout: 30000}")
    private long connectionTimeout;
    @Value("${spring.datasource.hikari.idle-timeout: 600000}")
    private long idleTimeout;
    @Value("${spring.datasource.hikari.max-lifetime: 1800000}")
    private long maxLifetime;
    @Value("${spring.datasource.hikari.maximum-pool-size: 10}")
    private int maximumPoolSize;
    @Value("${spring.datasource.hikari.minimum-idle: 10}")
    private int minimumIdle;

    @Bean     //声明其为Bean实例,将数据源设置为hikari
    @Primary  //在同样的DataSource中，首先使用被标注的DataSource
    @RefreshScope
    public DataSource dataSource() {
        //创建数据源
        HikariDataSource dataSource = DataSourceBuilder.create().type(HikariDataSource.class).build();
        dataSource.setJdbcUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        dataSource.setDriverClassName(driverClassName);
        //configuration
        dataSource.setReadOnly(readOnly);
        dataSource.setConnectionTimeout(connectionTimeout);
        dataSource.setIdleTimeout(idleTimeout);
        dataSource.setMaxLifetime(maxLifetime);
        dataSource.setMaximumPoolSize(maximumPoolSize);
        dataSource.setMinimumIdle(minimumIdle);
        return dataSource;
    }

    @Bean
    public SqlSessionFactory sqlSessionFactory() throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource());
        return sqlSessionFactoryBean.getObject();
    }

    @Bean
    public PlatformTransactionManager transactionManager() throws SQLException {
        return new DataSourceTransactionManager(dataSource());
    }
}

