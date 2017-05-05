package com.berchina.zx.mvpdemo.http;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.cache.CacheMode;
import com.lzy.okgo.callback.AbsCallback;

/**
 * Created by zx on 2017/5/5 14:22
 * 项目名称：MVPDEMO
 * 类描述：
 * 备注
 */
public class HttpClient {


    public static void get(String url, String tag, AbsCallback callback) {
        get(url, tag, "cacheKey", CacheMode.DEFAULT, callback);
    }

    public static void get(String url, String tag, String cacheKey, CacheMode cacheMode, AbsCallback callback) {
        OkGo.get(url)
                .tag(tag)
                .cacheKey(cacheKey)
                .cacheMode(cacheMode)
                .execute(callback);
    }


}
