package com.hxy.nzxy.stexam.common.utils;

import org.apache.velocity.app.Velocity;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.templateresolver.FileTemplateResolver;

import java.io.BufferedWriter;
import java.io.StringWriter;
import java.util.Map;
import java.util.Properties;

/**
 * @author Administrator
 * @Title: ${FILE_NAME}
 * @Package com.hxy.nzxy.stexam.common.utils
 * @Description: ${TODO}
 * @date 2018-03-09 14:49
 */
public class HtmlGenerator {
    public static String generate(String filePath, String template, Map<String, Object> variables) throws Exception{

        //创建模板解析器
        FileTemplateResolver templateResolver = new FileTemplateResolver();
        templateResolver.setPrefix(filePath);
        templateResolver.setSuffix(".html");
        templateResolver.setCharacterEncoding("UTF-8");
        templateResolver.setTemplateMode("XHTML");
        templateResolver.setCacheTTLMs(Long.valueOf(3600000L));
        templateResolver.setCacheable(true);

        //设置velocity资源加载器
        Properties prop = new Properties();
        prop.put("file.resource.loader.class", "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
        Velocity.init(prop);

        //创建模板引擎并初始化解析器
        TemplateEngine engine = new TemplateEngine();
        engine.setTemplateResolver(templateResolver);
        engine.initialize();

        //输出流
        StringWriter stringWriter = new StringWriter();
        BufferedWriter writer = new BufferedWriter(stringWriter);

        //获取上下文
        Context ctx = new Context();
        ctx.setVariables(variables);
        engine.process(template,  ctx, writer);

        stringWriter.flush();
        stringWriter.close();
        writer.flush();
        writer.close();

        //输出html
        String htmlStr = stringWriter.toString();
        return htmlStr;
    }

}
