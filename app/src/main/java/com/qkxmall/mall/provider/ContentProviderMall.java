package com.qkxmall.mall.provider;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.util.Log;

/**
 * Created by yuna on 2017/9/8.
 */

public class ContentProviderMall extends ContentProvider {

    private DBHelper mHelper;
    private Context context;
    public static final int SEARCH_CODE = 1;
    public static final int SEARCH_CATEID = 2;


    public static UriMatcher matcher = new UriMatcher(UriMatcher.NO_MATCH);

    static {
        matcher.addURI(DBHelper.DATA_BASE_AUTHORITY, ImageCollectTable.TABLE_NAME,
                SEARCH_CODE);

    }

    @Override
    public boolean onCreate() {
        context = getContext();
        mHelper = DBHelper.getInstance(context);
        SQLiteDatabase db = mHelper.getWritableDatabase();
        db.close();


        return true;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {
        SQLiteDatabase db = mHelper.getReadableDatabase();
        Cursor cursor = null;
        int macherCode = matcher.match(uri);
        try {

            Log.v("dataoper123", "dataoper===1");
            switch (macherCode) {
                case SEARCH_CODE:
                    Log.v("dataoper123", "dataoper===2");
                    cursor = db.query(ImageCollectTable.TABLE_NAME, projection, selection, selectionArgs, null, null, sortOrder);
                    break;
                case SEARCH_CATEID:
                    Log.v("dataoper123", "dataoper===2");
                    cursor = db.query(ImageCollectTable.TABLE_NAME, projection, selection, selectionArgs, null, null, "ASC");
                    break;
                default:
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cursor;
    }

    @Override
    public String getType(Uri uri) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        Uri result = null;
        long rowID = 0;
        int matcherCode = matcher.match(uri);
        SQLiteDatabase db = mHelper.getWritableDatabase();
        // 由于此时的values中具体是否含有数据是不确定的，所以此时需要在第二个参数中添加表中的非主键的一列
        try {
            Log.v("dataoper123", "inserter===2===");
            switch (matcherCode) {
                case SEARCH_CODE: // channel
                    Log.v("dataoper123", "inserter===3===");
                    rowID = db.insert(ImageCollectTable.TABLE_NAME, ImageCollectTable.NAME,
                            values);
                    break;
                default:
                    break;

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (rowID > 0) {
            result = Uri.parse(uri + "/" + rowID);
        }
        Log.v("dataoper123", "inserter===4===" + result);
        return result;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        SQLiteDatabase db = mHelper.getWritableDatabase();
        int macherCode = matcher.match(uri);
        int deletResult = -1;
        try {
            switch (macherCode) {
                case SEARCH_CODE: // channel
                    deletResult = db.delete(ImageCollectTable.TABLE_NAME, selection,
                            selectionArgs);
                    break;

                default:
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        // db.close();
        return deletResult;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {
        SQLiteDatabase db = mHelper.getWritableDatabase();
        int matcherCode = matcher.match(uri);
        int updateResult = -1;
        switch (matcherCode) {
            case SEARCH_CODE:
                try {
                    updateResult = db.update(ImageCollectTable.TABLE_NAME, values, selection,
                            selectionArgs);

                    getContext().getContentResolver().notifyChange(uri, null);
                } catch (Exception e) {
                    e.printStackTrace();
                }

                break;

            default:
                break;
        }
        // db.close();
        return updateResult;
    }

    @Override
    public int bulkInsert(Uri uri, ContentValues[] values) {
        SQLiteDatabase db = mHelper.getWritableDatabase();
        int numValues = 0;
        db.beginTransaction(); // 开始事务
        try {
            // 数据库操作
            numValues = values.length;
            for (int i = 0; i < numValues; i++) {
                insert(uri, values[i]);
            }
            db.setTransactionSuccessful(); // 别忘了这句 Commit
        } finally {
            db.endTransaction(); // 结束事务
        }
        return numValues;
    }
}

