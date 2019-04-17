/*
package com.hxy.nzxy.stexam.system.config;

import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import java.util.concurrent.Executor;
*/
/**
 * @description
 * @create 2017-02-22 下午11:53
 * @email gxz04220427@163.com
 *//*

@Configuration
@EnableAsync
public class ThreadConfig implements AsyncConfigurer {
    */
/**
     * The {@link Executor} instance to be used when processing async
     * method invocations.
     *//*

    @Override
    public Executor getAsyncExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(50);
        executor.setMaxPoolSize(50);
        executor.setQueueCapacity(50);
        executor.initialize();
        return executor;
    }
    */
/**
     * The {@link AsyncUncaughtExceptionHandler} instance to be used
     * when an exception is thrown during an asynchronous method execution
     * with {@code void} return type.
     *//*

    @Override
    public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
        return null;
    }
}
*/
