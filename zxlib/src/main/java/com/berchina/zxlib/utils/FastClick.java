package com.berchina.zxlib.utils;

/**
 * Created by zx on 2017/3/12 8:10
 * 项目名称：ZxLib
 * 类描述：防快速点击
 * 备注
 */
public class FastClick {
    private static long lastClickTime;
    private static long threeTime;

    public synchronized static boolean isFastClick() {
        long time = System.currentTimeMillis();
        if (time - lastClickTime < 500) {
            return true;
        }
        lastClickTime = time;
        return false;
    }


    public synchronized static boolean isThreeM() {
        long time = System.currentTimeMillis();
        if (time - threeTime < 1000 * 60) {
            return true;
        }
        threeTime = time;
        return false;
    }
}
