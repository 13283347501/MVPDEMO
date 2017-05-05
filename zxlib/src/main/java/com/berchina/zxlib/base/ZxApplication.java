package com.berchina.zxlib.base;

import android.app.Application;
import android.content.Context;


/**
 * Created by zx on 2017/3/15 14:03
 * 项目名称：ZxLib
 * 类描述：Application
 * 备注
 */
public class ZxApplication extends Application {


    private Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();

    }

    public Context getContext() {
        return context;
    }
}
