package com.hxy.nzxy.stexam;

import org.apache.catalina.Context;
import org.apache.catalina.connector.Connector;
import org.apache.tomcat.util.descriptor.web.SecurityCollection;
import org.apache.tomcat.util.descriptor.web.SecurityConstraint;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.embedded.EmbeddedServletContainerFactory;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.io.PrintStream;

@EnableTransactionManagement
@ServletComponentScan
@MapperScan({"com.hxy.nzxy.stexam.*.dao","com.hxy.nzxy.stexam.*.*.dao"})
@SpringBootApplication
public class SelfTaughtExamApplication extends SpringBootServletInitializer {

    @Bean
    public EmbeddedServletContainerFactory servletContainer() {
        TomcatEmbeddedServletContainerFactory tomcat = new TomcatEmbeddedServletContainerFactory() {
            @Override
            protected void postProcessContext(Context context) {
                SecurityConstraint securityConstraint = new SecurityConstraint();
                securityConstraint.setUserConstraint("CONFIDENTIAL");
                SecurityCollection collection = new SecurityCollection();
                collection.addPattern("/*");
                securityConstraint.addCollection(collection);
                context.addConstraint(securityConstraint);
            }
        };

        tomcat.addAdditionalTomcatConnectors(httpConnector());
        return tomcat;
    }

    @Bean
    public Connector httpConnector() {
        Connector connector = new Connector("org.apache.coyote.http11.Http11NioProtocol");
        connector.setScheme("http");
        connector.setPort(8070);
        connector.setSecure(false);
        connector.setRedirectPort(8443);
        return connector;
    }
        public static void main(String[] args) {
            SpringApplication app = new SpringApplication(SelfTaughtExamApplication.class);

            app.setBanner(new Banner() {
                @Override
                public void printBanner(Environment environment, Class<?> sourceClass, PrintStream out) {
                    System.out.println("纳智校园欢迎您!");
                }
            });
            app.run(args);
       // SpringApplication.run(SelfTaughtExamApplication.class, args);
        System.out.println("=========================================");
        System.out.println("==============系统启动完成！==============");
        System.out.println("=========================================");
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        // 注意这里要指向原先用main方法执行的Application启动类
        return builder.sources(SelfTaughtExamApplication.class);
    }

}
