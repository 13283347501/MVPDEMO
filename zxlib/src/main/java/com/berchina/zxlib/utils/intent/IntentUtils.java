package com.berchina.zxlib.utils.intent;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.media.AudioManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;

import java.util.List;

/**
 * Created by zx on 2017/3/18 11:18
 * 项目名称：ZxLib
 * 类描述：活动跳转工具类
 * 备注
 */
public class IntentUtils {

    private IntentUtils() {
    }

    /**
     * 跳转到对应的Activity
     */
    public static <T> void showActivity(Activity activity, Class<T> activityCls) {
        showActivity(activity, activityCls, null);
    }

    public static <T> void showActivity(Activity activity, Class<T> activityCls, Bundle extras) {
        Intent intent = new Intent(activity, activityCls);
        if (null == extras) {
            activity.startActivity(intent);
        } else {
            intent.putExtras(extras);
            activity.startActivity(intent);
        }
    }

    /**
     * 跳转到对应的Activity返回的时候可以接受到结果
     *
     * @param activityCls 对应的Activity.class
     * @param requestCode 请求码
     */
    public static <T> void showActivityForResult(Activity activity, Class<T> activityCls, int requestCode) {
        showActivityForResult(activity, activityCls, null, requestCode);
    }

    public static <T> void showActivityForResult(Activity activity, Class<T> activityCls, Bundle extras, int requestCode) {
        Intent intent = new Intent(activity, activityCls);
        if (null == extras) {
            activity.startActivityForResult(intent, requestCode);
        } else {
            intent.putExtras(extras);
            activity.startActivityForResult(intent, requestCode);
        }
    }

    /**
     * 跳转到对应的Activity by Flags的过滤
     *
     * @param activityCls
     * @param flags
     */
    public static <T> void showActivityByFlags(Activity activity, Class<T> activityCls, int flags) {
        Intent intent = new Intent(activity, activityCls);
        intent.setFlags(flags);
        activity.startActivity(intent);
    }

    /**
     * 跳转到网络设置界面
     *
     * @param context 上下文对象
     */
    public static void showNetworkSetting(Context context) {
        Intent intent = new Intent(Settings.ACTION_DATA_ROAMING_SETTINGS);
        context.startActivity(intent);
    }

    /**
     * 跳转到拨打电话界面
     *
     * @param context
     * @param phoneNumber 传入的电话号码
     */
    public static void intent2Call(Context context, String phoneNumber) {
        Intent callIntent = new Intent(Intent.ACTION_DIAL,
                Uri.parse("tel:" + phoneNumber));
        callIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(callIntent);
    }

    /**
     * 跳转到浏览器界面
     *
     * @param context
     * @param url
     */
    public static void intent2Browser(Context context, String url) {
        if (!(url.startsWith("http://") || url.startsWith("https://"))) {
            url = "http://" + url;
        }
        Uri uri = Uri.parse(url);
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    public static boolean isSystemRinger(Context context) {
        AudioManager manager = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
        return manager.getRingerMode() == AudioManager.RINGER_MODE_NORMAL;
    }

    public static boolean isIntentSafe(Activity activity, Uri uri) {
        Intent mapCall = new Intent(Intent.ACTION_VIEW, uri);
        PackageManager packageManager = activity.getPackageManager();
        List<ResolveInfo> activities = packageManager.queryIntentActivities(mapCall, 0);
        return activities.size() > 0;
    }

    public static boolean isIntentSafe(Activity activity, Intent intent) {
        PackageManager packageManager = activity.getPackageManager();
        List<ResolveInfo> activities = packageManager.queryIntentActivities(intent, 0);
        return activities.size() > 0;
    }


    public static boolean isGooglePlaySafe(Activity activity) {
        Uri uri = Uri.parse("http://play.google.com/store/apps/details?id=com.google.android.gms");
        Intent mapCall = new Intent(Intent.ACTION_VIEW, uri);
        mapCall.addFlags(Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
        mapCall.setPackage("com.android.vending");
        PackageManager packageManager = activity.getPackageManager();
        List<ResolveInfo> activities = packageManager.queryIntentActivities(mapCall, 0);
        return activities.size() > 0;
    }
}
