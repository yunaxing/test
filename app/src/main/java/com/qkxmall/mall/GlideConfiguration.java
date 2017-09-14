package com.qkxmall.mall;

import android.content.Context;

import com.bumptech.glide.Glide;
import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.load.engine.cache.DiskLruCacheFactory;
import com.bumptech.glide.load.engine.cache.MemoryCacheAdapter;
import com.bumptech.glide.module.GlideModule;

/**
 * Created by lenovo on 2016/11/29.
 */
public class GlideConfiguration implements GlideModule{

    @Override
    public void applyOptions(Context context, GlideBuilder builder) {
        //设置图片的显示格式ARGB_8888
        builder.setDecodeFormat(DecodeFormat.PREFER_RGB_565);
        builder.setMemoryCache(new MemoryCacheAdapter() {

            @Override
            public int getMaxSize() {
                return 1024*1024*20;
            }
        });
        //设置磁盘缓存目录
        String downloadDirectoryPath= context.getCacheDir().getPath();
        int cacheSize100MegaBytes = 100000000;
        builder.setDiskCache(
                new DiskLruCacheFactory(downloadDirectoryPath, cacheSize100MegaBytes)
        );
    }

    @Override
    public void registerComponents(Context context, Glide glide) {

    }
}
