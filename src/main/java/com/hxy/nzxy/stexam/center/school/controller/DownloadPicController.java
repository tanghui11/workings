package com.hxy.nzxy.stexam.center.school.controller;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;

/**
 * @author ypp
 * @Title: DownloadPic
 * @Description:
 * @date 2018/12/1318:30
 */
@Controller
@RequestMapping("school/downloadPic")
public class DownloadPicController {

@GetMapping()
@RequiresPermissions("school:downloadPic:downloadPic")
String downloadPic(){
        return "center/school/downloadPic/downloadPic";
    }

/**
* 使用URLConnection下载文件或图片并保存到本地。
*/
@ResponseBody
@RequiresPermissions("school:school:school")
public class URLConnectionDownloader {
        public void main(String[] args) throws Exception {
            download("http://ftp.mukexiaoyuan.com:8899/182/1211/121118200080.jpg", "laozizhu.com.gif");
        }

//     @ResponseBody
//     @GetMapping("/list")
//     @RequiresPermissions("school:downloadPic:downloadPic")
//     public void download(){
//
//     }


  /**
 * 下载文件到本地
*
* @param urlString 被下载的文件地址
* @param filename  本地文件名
* @throws Exception 各种异常
*/
public void download(String urlString, String filename) throws Exception {
            // 构造URL
            URL url = new URL(urlString);
            // 打开连接
            URLConnection con = url.openConnection();
            // 输入流
            InputStream is = con.getInputStream();
            // 1K的数据缓冲
            byte[] bs = new byte[1024];
            // 读取到的数据长度
            int len;
            // 输出的文件流
            OutputStream os = new FileOutputStream(filename);
            // 开始读取
            while ((len = is.read(bs)) != -1) {
                os.write(bs, 0, len);
            }
            // 完毕，关闭所有链接
            os.close();
            is.close();
        }
    }
}
