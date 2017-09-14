package com.qkxmall.mall.provider;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by yuna on 2017/9/8.
 */

public class DBHelper extends SQLiteOpenHelper {
    /**
     * 数据库名称
     */
    public static final String DATA_BASE_NAME = "collect.db";

    /**
     * authority
     */
    public static final String DATA_BASE_AUTHORITY = "com.qkxmall.mall.model";

    public static String VISIT_DB_URL = "content://" + DATA_BASE_AUTHORITY;

    /**
     * 数据库版本号
     */
    public static int VERSION_CODE = 1;

    private static DBHelper mSingleInstance;

    public static boolean LOCATION = false;

    Context cont;

    private DBHelper(Context context) {
        super(context, DATA_BASE_NAME, null, VERSION_CODE);
        cont = context;
    }

    public synchronized static DBHelper getInstance(Context context) {
        if (mSingleInstance == null) {
            mSingleInstance = new DBHelper(context);
        }
        return mSingleInstance;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        // 最近搜索
        db.execSQL("CREATE TABLE " + ImageCollectTable.TABLE_NAME
                + " (_id INTEGER PRIMARY KEY AUTOINCREMENT,"
                + " " + ImageCollectTable.ID + " INTEGER,"
                + " " + ImageCollectTable.KEY + " INTEGER,"
                + " " + ImageCollectTable.NAME + " TEXT);");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }


}
