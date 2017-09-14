package com.qkxmall.mall;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @author Anthony
 *         createTime 2016/9/7.
 * @version 1.0
 */
public class BitmapUtils {
/*=============SD卡相关===============*/
    /**
     * 判断SDCard是否可用
     *
     * @return
     */
    public static boolean isSDCardEnable()
    {
        return Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED);

    }

    /**
     * 获取SD卡路径
     *
     * @return
     */
    public static String getSDCardPath()
    {
        return Environment.getExternalStorageDirectory().getAbsolutePath()
                + File.separator;
    }
    public static String bitmapToFile(Context context, Bitmap image) {
        String dir = "";
        if(context.getExternalCacheDir() != null){
            dir = context.getExternalCacheDir().getAbsolutePath()+"/cache/";
        } else if(isSDCardEnable()){
            dir = getSDCardPath()+"/"+context.getPackageName()+"/cache/";
        }
//        String filePath = context.getExternalCacheDir().getAbsolutePath() + "/"
//                + System.currentTimeMillis();

        File dirFile = new File(dir);
        if(!dirFile.getParentFile().getParentFile().exists()){
            dirFile.getParentFile().getParentFile().mkdir();
        }
        if(!dirFile.getParentFile().exists()){
            dirFile.getParentFile().mkdir();
        }
        if(!dirFile.exists()){
            dirFile.mkdir();
        }
        String fileName = System.currentTimeMillis()+".jpg";
        String filePath = dir+fileName;

        File file = new File(filePath);
        if (file.exists()) {
            file.delete();
        }
        try {
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        image.compress(Bitmap.CompressFormat.JPEG, 100, baos);// 质量压缩方法，这里100表示不压缩，把压缩后的数据存放到baos中
        int options = 100;
        while (baos.toByteArray().length / 1024 > 300) { // 循环判断如果压缩后图片是否大于300kb,大于继续压缩
            baos.reset();// 重置baos即清空baos
            image.compress(Bitmap.CompressFormat.JPEG, options, baos);// 这里压缩options%，把压缩后的数据存放到baos中
            if (options <= 10) {
                options = options / 2 + 1;
            } else {
                options -= 10;// 每次都减少10
            }
        }
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            byte[] result = baos.toByteArray();
            fileOutputStream.write(result);
            fileOutputStream.flush();
            fileOutputStream.close();

        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            baos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return filePath;// 把压缩后的数据baos存放到ByteArrayInputStream中
    }
    @SuppressWarnings("deprecation")
    public static Bitmap compressImageFromFile(Context context, String srcPath,
                                               int inSampleSize) {
        BitmapFactory.Options newOpts = new BitmapFactory.Options();

        newOpts.inJustDecodeBounds = false;
        newOpts.inSampleSize = inSampleSize;// 设置采样率

        newOpts.inPreferredConfig = Bitmap.Config.RGB_565;// 该模式是默认的,可不设
        newOpts.inPurgeable = true;// 同时设置才会有效
        newOpts.inInputShareable = true;// 。当系统内存不够时候图片自动被回收

        Bitmap bitmap = BitmapFactory.decodeFile(srcPath, newOpts);
        return bitmap;
    }

    @SuppressWarnings("deprecation")
    public static Bitmap compressImageFromFile(Context context, String srcPath,
                                               float newWidth, float newHeight) {
        BitmapFactory.Options newOpts = new BitmapFactory.Options();
        newOpts.inJustDecodeBounds = true;// 只读边,不读内容
        BitmapFactory.decodeFile(srcPath, newOpts);

        newOpts.inJustDecodeBounds = false;
        int w = newOpts.outWidth;
        int h = newOpts.outHeight;

        float hh = newHeight;
        float ww = newWidth;
        int be = 1;
        if (ww > 0 && w > h && w > ww) {
            be = (int) (newOpts.outWidth / ww);
        } else if (hh > 0 && w < h && h > hh) {
            be = (int) (newOpts.outHeight / hh);
        }
        if (be <= 0)
            be = 1;
        newOpts.inSampleSize = be;// 设置采样率

        newOpts.inPreferredConfig = Bitmap.Config.RGB_565;// 该模式是默认的,可不设
        newOpts.inPurgeable = true;// 同时设置才会有效
        newOpts.inInputShareable = true;// 。当系统内存不够时候图片自动被回收

        Bitmap bitmap = BitmapFactory.decodeFile(srcPath, newOpts);
        // return compressBmpFromBmp(bitmap);//原来的方法调用了这个方法企图进行二次压缩
        // 其实是无效的,大家尽管尝试
        return bitmap;
    }
}
