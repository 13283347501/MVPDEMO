package com.berchina.zxlib.utils.ui;

import android.content.Context;
import android.view.Gravity;
import android.widget.Toast;

/**
 * Created by zx on 2017/3/21 16:36
 * 项目名称：ZxLib
 * 类描述： Toast显示工具类
 * 备注
 */
public class ToastUtils {
    private static Context mContext;
    private static Toast toast;

    public static void show(Context context, int resId) {
        if (context != null)
            show(context, context.getResources().getText(resId), Toast.LENGTH_SHORT);
    }

    public static void show(Context context, int resId, int duration) {
        if (context != null)
            show(context, context.getResources().getText(resId), duration);
    }

    public static void show(Context context, CharSequence text) {
        show(context, text, Toast.LENGTH_SHORT);
    }

    public static void show(Context context, CharSequence text, int duration) {
        if (context != null) {
            if (mContext == context) {
                toast.setText(text);
                toast.show();
            } else {
                mContext = context;
                toast = Toast.makeText(context, text, duration);
                toast.show();
            }

        }
    }

    public static void show(Context context, int resId, Object... args) {
        show(context, String.format(context.getResources().getString(resId), args), Toast.LENGTH_SHORT);
    }

    public static void show(Context context, String format, Object... args) {
        show(context, String.format(format, args), Toast.LENGTH_SHORT);
    }

    public static void show(Context context, int resId, int duration, Object... args) {
        show(context, String.format(context.getResources().getString(resId), args), duration);
    }

    public static void show(Context context, String format, int duration, Object... args) {
        show(context, String.format(format, args), duration);
    }

    public static void show(Context context, CharSequence text, int duration, int y) {
        Toast toast = Toast.makeText(context, text, duration);
        toast.setGravity(Gravity.BOTTOM, 0, y);
        toast.show();
    }
}

