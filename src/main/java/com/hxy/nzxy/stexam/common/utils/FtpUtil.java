package com.hxy.nzxy.stexam.common.utils;

import com.hxy.nzxy.stexam.common.config.FtpConfig;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;

import java.io.*;

/**
 * 2018-07-30
 * 创建人：lzl
 */
public class FtpUtil {
    /**
     * ftp上传图片方法
     *title:pictureUpload
     *@param ftpConfig  由spring管理的FtpConfig配置，在调用本方法时，可以在使用此方法的类中通过@AutoWared注入该属性。由于本方法是静态方法，所以不能在此注入该属性
     *@param picNewName 图片新名称--防止重名 例如："1.jpg"
     *@param picSavePath 图片保存路径。注：最后访问路径是 ftpConfig.getFTP_ADDRESS()+"/images"+picSavePath
     *@param file 要上传的文件（图片）
     *@return 若上传成功，返回图片的访问路径，若上传失败，返回null
     * @throws IOException
     */

    public static String[] pictureUploadByConfig(FtpConfig ftpConfig, String picNewName, String picSavePath, InputStream inputStream) throws IOException{

        String picPath[] = new String[2];
        boolean flag = uploadFile(ftpConfig.getAddress(), ftpConfig.getFtpPort(), ftpConfig.getFtpUserName(),
                ftpConfig.getFtpPassword(), ftpConfig.getFtpBasepath(), picSavePath, picNewName, inputStream);

        if(!flag){

            return picPath;

        }
        //picHttpPath = ftpConfig.getFTP_ADDRESS()+"/images"+picSavePath+"/"+picNewName;
        String picHttpPath = ftpConfig.getFtpBaseUrl()+picSavePath+"/"+picNewName;
        System.out.println("==="+picHttpPath);
        picPath[0]=picHttpPath;
        picPath[1]= ftpConfig.getFtpBasepath()+"/"+picSavePath+"/"+picNewName;
        return picPath;
    }

        /**
         * Description: 向FTP服务器上传文件
         * @param host FTP服务器hostname
         * @param port FTP服务器端口
         * @param username FTP登录账号
         * @param password FTP登录密码
         * @param basePath FTP服务器基础目录
         * @param filePath FTP服务器文件存放路径。例如分日期存放：/2018/01/01。文件的路径为basePath+filePath
         * @param filename 上传到FTP服务器上的文件名
         * @param input 输入流
         * @return 成功返回true，否则返回false
         */

    public static boolean uploadFile(String host, String ftpPort, String username, String password, String basePath,
                       String filePath, String filename, InputStream input) {
        int port = Integer.parseInt(ftpPort);

        boolean result = false;

        FTPClient ftp = new FTPClient();
        try {
            int reply;
            ftp.connect(host, port);// 连接FTP服务器
            // 如果采用默认端口，可以使用ftp.connect(host)的方式直接连接FTP服务器
            ftp.login(username, password);// 登录
            reply = ftp.getReplyCode();
            if (!FTPReply.isPositiveCompletion(reply)) {
                try {
                    ftp.disconnect();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return result;
            }
            //切换到上传目录
            if (!ftp.changeWorkingDirectory(basePath+filePath)) {

                //如果目录不存在创建目录
                String[] dirs = filePath.split("/");
                String tempPath = basePath;
                for (String dir : dirs) {
                    if (null == dir || "".equals(dir)) continue;
                    tempPath += "/" + dir;
                    if (!ftp.changeWorkingDirectory(tempPath)) {
                        if (!ftp.makeDirectory(tempPath)) {
                            return result;
                        } else {
                            ftp.changeWorkingDirectory(tempPath);
                        }
                    }
                }
            }
            //设置上传文件的类型为二进制类型
            ftp.setFileType(FTP.BINARY_FILE_TYPE);
            ftp.enterLocalPassiveMode();//这个设置允许被动连接--访问远程ftp时需要
            //上传文件
            //删除文件
           // ftp.deleteFile(filename);
            if (!ftp.storeFile(filename, input)) {
                return result;

            }
            input.close();
            ftp.logout();
            result = true;

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (ftp.isConnected()) {

                try {
                    ftp.disconnect();
                } catch (IOException ioe) {
                }
            }

        }

        return result;

    }

        /**
         * Description: 从FTP服务器下载文件
         * @param host FTP服务器hostname
         * @param port FTP服务器端口
         * @param username FTP登录账
         * @param password FTP登录密码
         * @param remotePath FTP服务器上的相对路径
         * @param fileName 要下载的文件名
         * @param localPath 下载后保存到本地的路径
         * @return
         */

    public static boolean downloadFile(String host, int port, String username, String password, String remotePath,
                                       String fileName, String localPath) {

        boolean result = false;

        FTPClient ftp = new FTPClient();

        try {
            int reply;
            ftp.connect(host, port);

            // 如果采用默认端口，可以使用ftp.connect(host)的方式直接连接FTP服务器

            ftp.login(username, password);// 登录
            reply = ftp.getReplyCode();

            if (!FTPReply.isPositiveCompletion(reply)) {
                ftp.disconnect();

                return result;

            }

            ftp.changeWorkingDirectory(remotePath);// 转移到FTP服务器目录

            FTPFile[] fs = ftp.listFiles();
            for (FTPFile ff : fs) {
                if (ff.getName().equals(fileName)) {
                    File localFile = new File(localPath + "/" + ff.getName());
                    OutputStream is = new FileOutputStream(localFile);
                    ftp.retrieveFile(ff.getName(), is);
                    is.close();
                }
            }

            ftp.logout();
            result = true;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (ftp.isConnected()) {
                try {
                    ftp.disconnect();

                } catch (IOException ioe) {
                }
            }

        }

        return result;

    }
//    public static void main(String[] args)
//    {
//        File src ;
//        FileInputStream open;   //读取文件
//        try {
//            src = new File("H:\\0183\\018316100001.jpg");
//            open = new FileInputStream(src);
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }
//
//        FtpConfig ftpConfig= new FtpConfig();
//        FtpUtil.pictureUploadByConfig(ftpConfig,"newsName","pic",open);
//    }

}
