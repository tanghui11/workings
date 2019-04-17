package com.hxy.nzxy.stexam.common.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 * 2018-07-30
 * 创建人：lzl
 */
@Configuration
public class FtpConfig {
    public String getAddress() {
        return address;
    }

    /**
     * 获取ip地址
     */
    @Value("${ftp.address}")
    private String address;
    /**
         * 端口号
         */
    @Value("${ftp.port}")
    private String ftpPort;
        /**
         * 用户名
         */

    @Value("${ftp.username}")
    private String ftpUserName;
    /**
     * 密码
     */
    @Value("${ftp.password}")
    private String ftpPassword;

    /**基本路径
     */
    @Value("${ftp.basepath}")
    private String ftpBasepath;

    /**
     * 下载地址地基础url
     */
    @Value("${ftp.baseUrl}")

    private String ftpBaseUrl;



    public String getFtpPort() {
        return ftpPort;
    }

    public String getFtpUserName() {
        return ftpUserName;
    }

    public String getFtpPassword() {
        return ftpPassword;
    }

    public String getFtpBasepath() {
        return ftpBasepath;
    }

    public String getFtpBaseUrl() {
        return ftpBaseUrl;
    }


    public void setAddress(String address) {
        this.address = address;
    }

    public void setFtpPort(String ftpPort) {
        this.ftpPort = ftpPort;
    }

    public void setFtpUserName(String ftpUserName) {
        this.ftpUserName = ftpUserName;
    }

    public void setFtpPassword(String ftpPassword) {
        this.ftpPassword = ftpPassword;
    }

    public void setFtpBasepath(String ftpBasepath) {
        this.ftpBasepath = ftpBasepath;
    }

    public void setFtpBaseUrl(String ftpBaseUrl) {
        this.ftpBaseUrl = ftpBaseUrl;
    }
}
