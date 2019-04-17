package com.hxy.nzxy.stexam.common.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;

@ConditionalOnProperty(prefix = "eduyun", name = "spring-session-open", havingValue = "true")
public class SpringSessionConfig {

}
