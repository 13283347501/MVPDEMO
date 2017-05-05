package com.berchina.zxlib.utils.assethelp;

import android.content.Context;


import com.berchina.zxlib.utils.log.LogUtils;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by zx on 2017/3/20 16:13
 * 项目名称：ZxLib
 * 类描述：Assets帮助类
 * 备注
 */
public class AssetsHelper {

    private AssetsHelper() {
        throw new AssertionError();
    }

    /**
     * 读取 assets 文件
     *
     * @param context
     * @param fileName
     * @return
     */
    public static String readData(Context context, String fileName) {
        InputStream inStream = null;
        String data = null;
        try {
            inStream = context.getAssets().open(fileName);     //打开assets目录中的文本文件
            byte[] bytes = new byte[inStream.available()];  //inStream.available()为文件中的总byte数
            inStream.read(bytes);
            inStream.close();
            data = new String(bytes, "utf-8");        //将bytes转为utf-8字符串
        } catch (IOException e) {
            LogUtils.e("IOException", e.toString());
            e.printStackTrace();
        }
        return data;
    }
}
