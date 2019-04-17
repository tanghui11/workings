package com.hxy.nzxy.stexam.common.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.charset.Charset;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.UUID;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

@Service
public class ZipUtils {

    private String idPrefix;

    private String zipDir;

    private static final int BUFFER_SIZE = 2 * 1024;

    public String getZipDir() {
        return zipDir;
    }

    public R unZipFile(MultipartFile multipartFile) {
        try {
            String unZipRealPath = getZipDir() + getUUID() + "/";
            File unZipFile = new File(unZipRealPath);
            if (!unZipFile.exists()) {
                unZipFile.mkdirs();
            }
            //ZipInputStream用来读取压缩文件的输入流
            ZipInputStream zipInputStream = new ZipInputStream(multipartFile.getInputStream(), Charset.forName("GBK"));
            //压缩文档中每一个项为一个zipEntry对象，可以通过getNextEntry方法获得，zipEntry可以是文件，也可以是路径，比如abc/test/路径下
            ZipEntry zipEntry;
            while ((zipEntry = zipInputStream.getNextEntry()) != null) {
                if (zipEntry.getSize() > 200 * 1024) {
                    continue;
                }
                String zipEntryName = zipEntry.getName();
                //将目录中的1个或者多个\置换为/，因为在windows目录下，以\或者\\为文件目录分隔符，linux却是/
                String outPath = unZipRealPath + zipEntryName;
                //判断所要添加的文件所在路径或者
                // 所要添加的路径是否存在,不存在则创建文件路径
                File file = new File(outPath.substring(0, outPath.lastIndexOf('/')));
                if (!file.exists()) {
                    file.mkdirs();
                }
                //判断文件全路径是否为文件夹,如果是,在上面三行已经创建,不需要解压
                if (new File(outPath).isDirectory()) {
                    continue;
                }
                OutputStream outputStream = new FileOutputStream(outPath);
                byte[] bytes = new byte[BUFFER_SIZE];
                int len;
                //当read的返回值为-1，表示碰到当前项的结尾，而不是碰到zip文件的末尾
                while ((len = zipInputStream.read(bytes)) > 0) {
                    outputStream.write(bytes, 0, len);
                }
                outputStream.close();
                //必须调用closeEntry()方法来读入下一项
                zipInputStream.closeEntry();
            }
            zipInputStream.close();
            R ret = new R();
            ret.put("path", unZipRealPath);
            return ret;
        } catch (IOException e) {
            e.printStackTrace();
            return R.error(e.getMessage());
        }
    }

    /**
     * 压缩成ZIP 方法1
     *
     * @param srcDir           压缩文件夹路径
     * @param out              压缩文件输出流
     * @param KeepDirStructure 是否保留原来的目录结构,true:保留目录结构;
     *                         false:所有文件跑到压缩包根目录下(注意：不保留目录结构可能会出现同名文件,会压缩失败)
     * @throws RuntimeException 压缩失败会抛出运行时异常
     */
    public static void toZip(String srcDir, OutputStream out, boolean KeepDirStructure)
            throws RuntimeException {
        long start = System.currentTimeMillis();
        ZipOutputStream zos = null;
        try {
            zos = new ZipOutputStream(out);
            File sourceFile = new File(srcDir);
            compress(sourceFile, zos, sourceFile.getName(), KeepDirStructure);
            long end = System.currentTimeMillis();
        } catch (Exception e) {
            throw new RuntimeException("zip error from ZipUtils", e);
        } finally {
            if (zos != null) {
                try {
                    zos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 递归压缩方法
     *
     * @param sourceFile       源文件
     * @param zos              zip输出流
     * @param name             压缩后的名称
     * @param KeepDirStructure 是否保留原来的目录结构,true:保留目录结构;
     *                         false:所有文件跑到压缩包根目录下(注意：不保留目录结构可能会出现同名文件,会压缩失败)
     * @throws Exception
     */
    private static void compress(File sourceFile, ZipOutputStream zos, String name,
                                 boolean KeepDirStructure) throws Exception {
        byte[] buf = new byte[BUFFER_SIZE];
        if (sourceFile.isFile()) {
            // 向zip输出流中添加一个zip实体，构造器中name为zip实体的文件的名字
            zos.putNextEntry(new ZipEntry(name));
            // copy文件到zip输出流中
            int len;
            FileInputStream in = new FileInputStream(sourceFile);
            while ((len = in.read(buf)) != -1) {
                zos.write(buf, 0, len);
            }
            // Complete the entry
            zos.closeEntry();
            in.close();
        } else {
            File[] listFiles = sourceFile.listFiles();
            if (listFiles == null || listFiles.length == 0) {
                // 需要保留原来的文件结构时,需要对空文件夹进行处理
                if (KeepDirStructure) {
                    // 空文件夹的处理
                    zos.putNextEntry(new ZipEntry(name + "/"));
                    // 没有文件，不需要文件的copy
                    zos.closeEntry();
                }

            } else {
                for (File file : listFiles) {
                    // 判断是否需要保留原来的文件结构
                    if (KeepDirStructure) {
                        // 注意：file.getName()前面需要带上父文件夹的名字加一斜杠,
                        // 不然最后压缩包中就不能保留原来的文件结构,即：所有文件都跑到压缩包根目录下了
                        compress(file, zos, name + "/" + file.getName(), KeepDirStructure);
                    } else {
                        compress(file, zos, file.getName(), KeepDirStructure);
                    }

                }
            }
        }
    }

    /**
     * 获得UUID
     *
     * @return String UUID
     */
    public String getUUID() {
        Date date = new Date();
        DateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
        String time = format.format(date);
        //获取uuid
        String uuid = UUID.randomUUID().toString();
        return idPrefix + '_' + time + '_' + uuid.replaceAll("-", "");
    }

    public static void createZip(String sourcePath, String zipPath) {
        FileOutputStream fos = null;
        ZipOutputStream zos = null;
        try {
            fos = new FileOutputStream(zipPath);
            zos = new ZipOutputStream(fos);
            writeZip(new File(sourcePath), "", zos);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                if (zos != null) {
                    zos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }
    private static void writeZip(File file, String parentPath, ZipOutputStream zos) {
        if(file.exists()){
            if(file.isDirectory()){//处理文件夹
                parentPath+=file.getName()+File.separator;
                File [] files=file.listFiles();
                for(File f:files){
                    writeZip(f, parentPath, zos);
                }
            }else{
                FileInputStream fis=null;
                DataInputStream dis=null;
                try {
                    fis=new FileInputStream(file);
                    dis=new DataInputStream(new BufferedInputStream(fis));
                    ZipEntry ze = new ZipEntry(parentPath + file.getName());
                    zos.putNextEntry(ze);
                    byte [] content=new byte[1024];
                    int len;
                    while((len=fis.read(content))!=-1){
                        zos.write(content,0,len);
                        zos.flush();
                    }


                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }finally{
                    try {
                        if(dis!=null){
                            dis.close();
                        }
                    }catch(IOException e){
                        e.printStackTrace();
                    }
                }
            }
        }
    }



 /* zip解压
 * @param srcFile           zip源文件
 * @param destDirPath       解压后的目标文件夹
 * @throws RuntimeException 解压失败会抛出运行时异常
 */

    public String unZip(File srcFile) throws RuntimeException {

        String dirPath = "";
        String destDirPath = "E://fileupload";
        long start = System.currentTimeMillis();
        // 判断源文件是否存在
        if (!srcFile.exists()) {
            throw new RuntimeException(srcFile.getPath() + "所指文件不存在");
        }
        // 开始解压
        ZipFile zipFile = null;
        try {
            zipFile = new ZipFile(srcFile);
            Enumeration<?> entries = zipFile.entries();
            while (entries.hasMoreElements()) {
                ZipEntry entry = (ZipEntry) entries.nextElement();
                System.out.println("解压" + entry.getName());

                // 如果是文件夹，就创建个文件夹
                if (entry.isDirectory()) {
                    dirPath = destDirPath + "/" + entry.getName();
                    File dir = new File(dirPath);
                    dir.mkdirs();
                } else {
                    // 如果是文件，就先创建一个文件，然后用io流把内容copy过去
                    dirPath = destDirPath + "/" + entry.getName();
                    File targetFile = new File(dirPath);
                    // 保证这个文件的父文件夹必须要存在
                    if(!targetFile.getParentFile().exists()){
                        targetFile.getParentFile().mkdirs();
                    }
                    targetFile.createNewFile();
                    // 将压缩文件内容写入到这个文件中
                    InputStream is = zipFile.getInputStream(entry);
                    FileOutputStream fos = new FileOutputStream(targetFile);
                    int len;
                    byte[] buf = new byte[BUFFER_SIZE];
                    while ((len = is.read(buf)) != -1) {
                        fos.write(buf, 0, len);
                    }
                    // 关流顺序，先打开的后关闭
                    fos.close();
                    is.close();
                }
            }

            long end = System.currentTimeMillis();
            System.out.println("解压完成，耗时：" + (end - start) +" ms");
        } catch (Exception e) {
            throw new RuntimeException("unzip error from ZipUtils", e);
        } finally {
            if(zipFile != null){
                try {
                    zipFile.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return dirPath;
    }


    public R unzipfile(MultipartFile multipartFile) {
        try {
            String unZipRealPath = "picture/";
            File unZipFile = new File(unZipRealPath);
            if (!unZipFile.exists()) {
                unZipFile.mkdirs();
            }
            //ZipInputStream用来读取压缩文件的输入流
            ZipInputStream zipInputStream = new ZipInputStream(multipartFile.getInputStream(), Charset.forName("GBK"));
            //压缩文档中每一个项为一个zipEntry对象，可以通过getNextEntry方法获得，zipEntry可以是文件，也可以是路径，比如abc/test/路径下
            ZipEntry zipEntry;
            while ((zipEntry = zipInputStream.getNextEntry()) != null) {
                if (zipEntry.getSize() > 200 * 1024) {
                    continue;
                }
                String zipEntryName = zipEntry.getName();
                //将目录中的1个或者多个\置换为/，因为在windows目录下，以\或者\\为文件目录分隔符，linux却是/
                String outPath = unZipRealPath + zipEntryName;
                //判断所要添加的文件所在路径或者
                // 所要添加的路径是否存在,不存在则创建文件路径
                File file = new File(outPath.substring(0, outPath.lastIndexOf('/')));
                if (!file.exists()) {
                    file.mkdirs();
                }
                //判断文件全路径是否为文件夹,如果是,在上面三行已经创建,不需要解压
                if (new File(outPath).isDirectory()) {
                    continue;
                }
                OutputStream outputStream = new FileOutputStream(outPath);
                byte[] bytes = new byte[BUFFER_SIZE];
                int len;
                //当read的返回值为-1，表示碰到当前项的结尾，而不是碰到zip文件的末尾
                while ((len = zipInputStream.read(bytes)) > 0) {
                    outputStream.write(bytes, 0, len);
                }
                outputStream.close();
                //必须调用closeEntry()方法来读入下一项
                zipInputStream.closeEntry();
            }
            zipInputStream.close();
            R ret = new R();
            ret.put("path", unZipRealPath);
            return ret;
        } catch (IOException e) {
            e.printStackTrace();
            return R.error(e.getMessage());
        }
    }
}