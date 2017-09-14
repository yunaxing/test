package com.qkxmall.mall.provider;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.util.Log;

/**
 * Created by yuna on 2017/9/8.
 */

public class DataOper {

    private static final String TAG = "IRDADataOper";

    private static Uri uri = Uri.parse(DBHelper.VISIT_DB_URL + "/"
            + ImageCollectTable.TABLE_NAME);

    /**
 22.     * 获取最近搜索
 23.     *
 24.     * @param resolver
 25.     * @return
 26.     */
    /**
 28.     * 新增最近搜索
 29.     *
 30.     */
    public static void insertCollectTable(ContentResolver resolver, String name){
               if(name != null){
                    Log.v("dataoper123","inserter===1==="+name);
//                         Log.v("dataoper123","inserter===2==="+item.getKey());
                                  ContentValues values = new ContentValues();

                                 values.put(ImageCollectTable.NAME, name);
                                 values.put(ImageCollectTable.KEY, "0");

                              Uri uriInsert = resolver.insert(uri, values);
                                  System.out.println("llll--Uri:"+uri);

                          }

                    }

    public static void updateCollectTable(ContentResolver resolver, String name, String key){

      String[] selectionArgs = new String[1];
        selectionArgs[0] = String.valueOf(name);
        ContentValues cv = new ContentValues();
     cv.put(ImageCollectTable.KEY, key);
        resolver.update(uri, cv, ImageCollectTable.NAME +"=?" , selectionArgs);
    }

    /**
 60.     * 检查是不是存在
 61.     *
 62.     */

    public static boolean checkExistID(ContentResolver resolver, String name){
      String[] selectionArgs = new String[1];
        selectionArgs[0] = String.valueOf(name);
       Cursor cursor = resolver.query(uri, null,
                ImageCollectTable.NAME+"=?", selectionArgs, null);
       Log.v("dataoper123","dataoper===3==="+name);
        Log.v("dataoper123","dataoper===4==="+cursor);
        if (cursor != null) {
            if(cursor.getCount()>0){

                return true;
            } else
                return false;

        }

        if (cursor != null)
            cursor.close();
        return false;

    }
    public static void operateTable(ContentResolver resolver, String name){
        if(checkExistID(resolver,name)){
            String key = queryKey(resolver,name);
            int tempkey = Integer.parseInt(key)+1;
            updateCollectTable(resolver,name,tempkey+"");
        }else {
            insertCollectTable(resolver,name);
        }
    }

  public static void deleItem(ContentResolver resolver, String name){

        String[] selectionArgs = new String[1];
       selectionArgs[0] = String.valueOf(name);

       resolver.delete(uri,
                ImageCollectTable.NAME+"=?", selectionArgs);

    }

            public static String queryKey(ContentResolver resolver, String name){

                String[] selectionArgs = new String[1];
                String key = "";
                selectionArgs[0] = String.valueOf(name);
        Cursor cursor = resolver.query(uri, null,
                           ImageCollectTable.NAME+"=?", selectionArgs, null);
                Log.v("key***","name=="+name);
                Log.v("key***","cursor=="+cursor);
        //        Log.v("key***","cursor.moveToNext()=="+cursor.moveToNext());
                Log.v("key***","cursor.getColumnCount()()=="+cursor.getColumnCount());
                while (cursor != null  && cursor.moveToNext()) {
                        int columnName = cursor.getColumnIndex(ImageCollectTable.KEY);

                        key = cursor.getInt(columnName)+"";
                        Log.v("key***","key=="+key);


                    }

                if (cursor != null)
                        cursor.close();
                return key;

           }

    public static String queryName(ContentResolver resolver){

        Cursor cursor = resolver.query(uri, null,
                null, null, "key desc");

        Log.v("key***","cursor=="+cursor);
        String name ="22";
        //        Log.v("key***","cursor.moveToNext()=="+cursor.moveToNext());
        Log.v("key***","cursor.getColumnCount()()=="+cursor.getColumnCount());
        while (cursor != null  && cursor.moveToNext()) {
            int columnName = cursor.getColumnIndex(ImageCollectTable.NAME);

//            name = cursor.getString(columnName)+"";
            Log.v("key***","key=="+name);
            return cursor.getString(columnName)+"";


        }

        if (cursor != null)
            cursor.close();
        return name;

    }

}
