package com.qkxmall.mall;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.os.Build;
import android.os.Environment;
import android.util.Log;
import android.widget.Toast;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.TimeZone;

/**
 * Created by Sunshine on 01/19/2016.
 */
public class Utils {

    public static int dp2px(Resources resources, float dp) {
        final float scale = resources.getDisplayMetrics().density;
        return (int) (dp * scale + 0.5f);
    }
    public static void saveCrashInfoToSDCard(final Context context, Throwable throwable) {
        StringBuilder crashInfoStringBuilder=new StringBuilder();

        //获取Crash时间
        String crashTime=getCrashTime();
        crashInfoStringBuilder.append("------------------"+"\n");
        crashInfoStringBuilder.append(crashTime+"\n");
        crashInfoStringBuilder.append("------------------"+"\n");

        //获取Crash时设备及该App的基本信息
        HashMap<String, String> hashMap=getBaseInfo(context);
        for(Map.Entry<String, String> entry:hashMap.entrySet()){
            String key=entry.getKey();
            String value=entry.getValue();
            crashInfoStringBuilder.append(key).append("=").append(value).append("\n");
        }
        crashInfoStringBuilder.append("------------------"+"\n");

        //获取导致Crash的时间
        String uncaughtException=getUncaughtException(throwable);
        crashInfoStringBuilder.append(uncaughtException + "\n");
        crashInfoStringBuilder.append("------------------" + "\n");

        System.out.println("crashInfo如下:" + "\n" + crashInfoStringBuilder.toString());
        Log.e("CrashError:",crashInfoStringBuilder.toString());

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
        Date date = new Date(System.currentTimeMillis());
        String currentTime = simpleDateFormat.format(date);

        String deviceModel = Build.MODEL;
        deviceModel = deviceModel.replace(" ","_");
        String fileName = currentTime+"_"+deviceModel+"CrashException.crash";
        String crashInformation = crashInfoStringBuilder.toString();
        String path = Environment.getExternalStorageDirectory().getPath()+"/crash/";
        File dir = new File(path);
        if (!dir.exists()){
            dir.mkdir();
        }
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(path+fileName,true);
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fileOutputStream,"utf-8");
            outputStreamWriter.write("\n");
            outputStreamWriter.write(crashInformation);
            outputStreamWriter.flush();
            outputStreamWriter.close();
            fileOutputStream.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String crashUrl = "http://www.qkxmall.com/api/android/crash.php";
        RequestParams requestParams = new RequestParams(crashUrl);
        requestParams.setMultipart(true);
        requestParams.addBodyParameter("file",new File(path+fileName));
        requestParams.addBodyParameter("name",fileName);
        x.http().post(requestParams, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Toast.makeText(context, "异常提交成功", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });


    }
    /**
     * 获取设备及该App的基本信息
     */
    public static HashMap<String, String> getBaseInfo(Context context){
        HashMap<String, String> hashMap = new HashMap<String, String>();
        PackageManager packageManager = context.getPackageManager();
        PackageInfo packageInfo = null;
        try {
            packageInfo = packageManager.getPackageInfo(context.getPackageName(), PackageManager.GET_ACTIVITIES);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        hashMap.put("versionName", packageInfo.versionName);
        hashMap.put("versionCode", packageInfo.versionCode+"");

        hashMap.put("MODEL",  Build.MODEL+"");
        hashMap.put("SDK_INT",Build.VERSION.SDK_INT+"");
        hashMap.put("RELEASE",Build.VERSION.RELEASE+"");
        hashMap.put("PRODUCT", Build.PRODUCT+"");


        return hashMap;
    }
    /**
     * 获取造成Crash的异常的具体信息
     */
    public static String getUncaughtException(Throwable throwable){
        StringWriter stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter);
        throwable.printStackTrace(printWriter);
        printWriter.close();
        String uncaughtException=stringWriter.toString();
        return uncaughtException;

    }

    /**
     * 获取Crash的时间
     */
    public static String getCrashTime(){
        String currentTime="";
        long currentTimeMillis=System.currentTimeMillis();
        System.setProperty("user.timezone", "Asia/Shanghai");
        TimeZone timeZone = TimeZone.getTimeZone("Asia/Shanghai");
        TimeZone.setDefault(timeZone);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
        Date currentDate=new Date(currentTimeMillis);
        currentTime = simpleDateFormat.format(currentDate);
        return currentTime;
    }
    /**
     *
     * @return
     */
    public static String convertDouble(String src){

        int scale = 2;//设置位数
        int roundingMode = 4;//表示四舍五入，可以选择其他舍值方式，例如去尾，等等.
        BigDecimal bd = new BigDecimal(src);
        bd = bd.setScale(scale,BigDecimal.ROUND_DOWN);
        src = bd.toString();
        return src;
    }

    public static String string2MD5(String inStr){
        MessageDigest md5 = null;
        try{
            md5 = MessageDigest.getInstance("MD5");
        }catch (Exception e){
            System.out.println(e.toString());
            e.printStackTrace();
            return "";
        }
        char[] charArray = inStr.toCharArray();
        byte[] byteArray = new byte[charArray.length];

        for (int i = 0; i < charArray.length; i++)
            byteArray[i] = (byte) charArray[i];
        byte[] md5Bytes = md5.digest(byteArray);
        StringBuffer hexValue = new StringBuffer();
        for (int i = 0; i < md5Bytes.length; i++){
            int val = ((int) md5Bytes[i]) & 0xff;
            if (val < 16)
                hexValue.append("0");
            hexValue.append(Integer.toHexString(val));
        }
        return hexValue.toString();

    }

    /**
     * 加密解密算法 执行一次加密，两次解密
     */
    public static String convertMD5(String inStr){

        char[] a = inStr.toCharArray();
        for (int i = 0; i < a.length; i++){
            a[i] = (char) (a[i] ^ 't');
        }
        String s = new String(a);
        return s;

    }

}
