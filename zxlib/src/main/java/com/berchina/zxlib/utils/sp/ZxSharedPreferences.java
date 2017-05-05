package com.berchina.zxlib.utils.sp;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

/**
 * Created by zx on 2017/3/14 8:23
 * 项目名称：ZxLib
 * 类描述：SharedPreferences
 * 备注
 */
public class ZxSharedPreferences {

    public static final String WIDTH = "width";
    public static final String HEIGHT = "height";

    private static final String PREFERENCE_NAME = "ZX_MOBILE";

    public static ZxSharedPreferences instance;
    public SharedPreferences sharedPreferences;

    private ZxSharedPreferences(Context context) {
        sharedPreferences = context.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE);
    }

    /**
     * Single instance.
     */
    public static ZxSharedPreferences getInstance(Context context) {
        if (instance == null) {
            instance = new ZxSharedPreferences(context);
        }
        return instance;
    }

    public void putValue(String key, Object value) {
        Editor editor = sharedPreferences.edit();
        if (value == null || value instanceof String) {
            editor.putString(key, (String) value);
        } else if (value instanceof Boolean) {
            editor.putBoolean(key, (Boolean) value);
        } else if (value instanceof Integer) {
            editor.putInt(key, (Integer) value);
        }
        editor.commit();
    }

    public <T extends Object> Object getValue(String key, Class<T> cls) {
        if (cls.equals(String.class)) {
            String returnValue = sharedPreferences.getString(key, "");
            return returnValue;
        } else if (cls.equals(Boolean.class)) {
            return sharedPreferences.getBoolean(key, false);
        } else if (cls.equals(Integer.class)) {
            return sharedPreferences.getInt(key, 0);
        }
        return null;
    }

    public String getStringValue(String key) {
        String value = (String) getValue(key, String.class);
        return value;
    }

    public String getStringValue(String key, String defaultValue) {
        String value = sharedPreferences.getString(key, defaultValue);
        return value;
    }

    public int getWidth() {
        return (Integer) getValue(WIDTH, Integer.class);
    }

    public int getHeight() {
        return (Integer) getValue(HEIGHT, Integer.class);
    }

    /**
     * 清除配置文件
     */
    public void clearPreferences() {
        if (sharedPreferences != null) {
            Editor editor = sharedPreferences.edit();
            editor.clear();
            editor.commit();
        }
    }
}
