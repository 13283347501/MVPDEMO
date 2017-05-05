package com.berchina.zxlib.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by zx on 2017/3/14 8:23
 * 项目名称：ZxLib
 * 类描述：CollectionUtils 空判断
 * 备注
 */
public class CollectionUtils {

    private CollectionUtils() {
    }

    public static <T> boolean isNullList(List<T> list) {
        if (null == list || list.size() == 0) {
            return true;
        }
        return false;
    }


    public static String convertToString(List<String> StringList) {
        if (StringList.isEmpty()) {
            return null;
        }
        StringBuilder sbResult = new StringBuilder();
        for (String str : StringList) {
            sbResult.append(str).append(',');
        }
        String result = sbResult.toString();
        return result.substring(0, result.length() - 1);
    }

    public static List<String> string2List(String arg, String expression) {
        List<String> strList = new ArrayList<String>();
        String[] strArr = arg.split(expression);
        for (String str : strArr) {
            strList.add(str);
        }
        return strList;
    }

    public static List<String> array2List(String[] array) {
        List<String> list = new ArrayList<String>();
        for (String str : array) {
            list.add(str);
        }
        return list;
    }

    public static <T> List<T> convertArray2List(T t) {
        return Arrays.asList(t);
    }

    public static Object[] convertList2Array(List list) {
        return list.toArray();
    }

    public static String[] convertList2StringArray(List<String> stringList) {
        String[] stringArray = new String[stringList.size()];
        for (int i = 0; i < stringList.size(); i++) {
            stringArray[i] = stringList.get(i);
        }
        return stringArray;
    }
}

