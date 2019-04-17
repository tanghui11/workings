package com.hxy.nzxy.stexam.common.config;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by huguoju on 2016/12/29.
 * 数据库配置：解析properties文件
 */
@Configuration
@PropertySource("classpath:mybatis.properties")
public class DataBaseConfiguration {
    @Value("${spring.datasource.type}")
    private Class<? extends DataSource> dataSourceType;
    private static Logger log = LoggerFactory.getLogger(DataBaseConfiguration.class);
    @Bean(name = "writeDataSource", destroyMethod = "close", initMethod = "init")
    @Primary
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource writeDataSource() {
        log.info("-------------------- writeDataSource init ---------------------");
        return DataSourceBuilder.create().type(dataSourceType).build();
    }

    /**
     * 有多少个从库就要配置多少个DruidDataSource
     *
     * @return
     */
    @Bean(name = "readDataSource1")
    @ConfigurationProperties(prefix = "spring.slave")
    public DataSource readDataSourceOne() {
        log.info("-------------------- readDataSourceOne init ---------------------");
        return DataSourceBuilder.create().type(dataSourceType).build();
    }



    @Bean("readDataSources")
    public List<DataSource> readDataSources() {
        List<DataSource> dataSources = new ArrayList<>();
        dataSources.add(readDataSourceOne());
        return dataSources;

    }
}