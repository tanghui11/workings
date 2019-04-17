package com.hxy.nzxy.stexam.common.utils;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;

/**
 * 2018-08-24
 * 创建人：lzl
 */
public class PicUtil {
    /**
     * 判断相片背景是否是白色或蓝色：1是：0否
     * @return
     */
    public static int isRGB( File file)
    {
        int rult=0;

        int[] rgb = new int[3];
        BufferedImage bi = null;
        try {
            /**
             * 用ImageIO将图片读入到缓冲中
             */
            bi = ImageIO.read(file);
        } catch (Exception e) {
            e.printStackTrace();
        }
        /**
         * 得到图片的长宽
         */
        int width = bi.getWidth();
        int height = bi.getHeight();
        int minx = bi.getMinX();
        int miny = bi.getMinY();
        System.out.println("正在处理："+file.getName());
        /**
         * 这里是遍历图片的像素，因为要处理图片的背色，所以要把指定像素上的颜色换成目标颜色
         * 这里 是一个二层循环，遍历长和宽上的每个像素
         */
        for (int i = minx; i < width; i++) {
            for (int j = miny; j < height; j++) {
                // System.out.print(bi.getRGB(jw, ih));
                /**
                 * 得到指定像素（i,j)上的RGB值，
                 */
                int pixel = bi.getRGB(i, j);
                /**
                 * 分别进行位操作得到 r g b上的值
                 */
                rgb[0] = (pixel & 0xff0000) >> 16;
                rgb[1] = (pixel & 0xff00) >> 8;
                rgb[2] = (pixel & 0xff);
                /**
                 * 进行换色操作，，就判断图片中rgb值是否在蓝色范围的像素
                 */
                if(rgb[0]<155&&rgb[0]>0 && rgb[1]<256&&rgb[1]>105 && rgb[2]<256&&rgb[2]>105 ){
                    rult=1;
                    bi.setRGB(i, j, 0xffffff);
                }

            }
        }
        System.out.println("\t处理完毕："+file.getName());
        System.out.println();
        /**
         * 将缓冲对象保存到新文件中
         */
        try {
            FileOutputStream ops = new FileOutputStream(new File("H://0183/test.jpg"));
            ImageIO.write(bi,"jpg", ops);
            ops.flush();
            ops.close();
        }catch (FileNotFoundException e)
        {

        } catch (IOException e) {
            e.printStackTrace();
        }


        return rult;
    }

    public static void main(String[] args)
    {
        File file= new File("H://0183/lzl.jpg");
       System.out.println( PicUtil.isRGB(file));
    }
}
