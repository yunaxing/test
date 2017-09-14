package com.qkxmall.mall.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by yuna on 2017/8/7.
 */

public class DateUtil {

    public static String getCurrentDate(){
        Date d = new Date();
        System.out.println(d);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
//        String dateNowStr = sdf.format(d);
//        System.out.println("格式化后的日期：" + dateNowStr);
//        String date = dateNowStr.split(" ")[0].replace("-","");
        return sdf.format(new Date());
    }

}
