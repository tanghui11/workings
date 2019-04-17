package com.hxy.nzxy.stexam.common.config;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by huguoju on 2016/12/29.
 * 多数据源切换
 */
public class MyAbstractRoutingDataSource extends AbstractRoutingDataSource {
    private final int dataSourceNumber;
    private AtomicInteger count = new AtomicInteger(0);

    public MyAbstractRoutingDataSource(int dataSourceNumber) {
        this.dataSourceNumber = dataSourceNumber;
    }

    @Override
    protected Object determineCurrentLookupKey() {
         String typeKey = DataSourceContextHolder.getJdbcType();
         if(typeKey==null){
             DataSourceContextHolder.read();
             logger.info("AOP切面没有转换,默认复制read");
             return DataSourceType.write.getType();
         }else {
             if (typeKey.equals(DataSourceType.write.getType()))
                 return DataSourceType.write.getType();
         }

        // 读 简单负载均衡
        int number = count.getAndAdd(1);
        int lookupKey = number % dataSourceNumber;
        return new Integer(lookupKey);
    }
}
