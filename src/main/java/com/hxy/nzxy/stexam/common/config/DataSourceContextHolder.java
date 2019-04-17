package com.hxy.nzxy.stexam.common.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 本地线程，数据源上下文
 * @author Jfei
 *
 */
public class DataSourceContextHolder {

	private static Logger log = LoggerFactory.getLogger(DataSourceContextHolder.class);
    private static final ThreadLocal<String> local = new ThreadLocal<String>();

    //线程本地环境
    public static ThreadLocal<String> getLocal() {
        return local;
    }

    /**
     * 读可能是多个库
     */
    public static void read() {

        local.set(DataSourceType.read.getType());
    }

    /**
     * 写只有一个库
     */
    public static void write() {
        log.debug("writewritewrite");
        local.set(DataSourceType.write.getType());
    }

    public static String getJdbcType() {
        return local.get();

    }
}
