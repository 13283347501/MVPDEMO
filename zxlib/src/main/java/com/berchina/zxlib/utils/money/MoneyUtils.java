package com.berchina.zxlib.utils.money;

import android.text.TextUtils;

import java.math.BigDecimal;
import java.text.DecimalFormat;

/**
 * Created by zx on 2017/3/27 9:02
 * 项目名称：ZxLib
 * 类描述：金钱格式化
 * 备注
 */
public class MoneyUtils {
    private static final String MONEY_REGULAR = "\\d+(\\.\\d+)?";

    /**
     * 格式化金额为两位小数点
     *
     * @param val 金钱值 String
     * @return 格式化后
     */
    public static String moneyFormat(String val) {
        if (!TextUtils.isEmpty(val) && val.matches(MONEY_REGULAR)) {
            return "¥" + moneyFormat(Double.parseDouble(val));
        }
        return "¥0.00";
    }

    /**
     * 格式化金额为两位小数点
     *
     * @param val 金钱值 double
     * @return 格式化后
     */
    public static String moneyFormat(double val) {
        DecimalFormat df = new DecimalFormat("#.00");
        String res = df.format(val);
        if (".00".equals(res)) {
            res = "0.00";
        } else if (Double.parseDouble(res) < 1.00 && Double.parseDouble(res) > 0.00) {
            res = 0 + res;
        }
        return res;
    }

    /**
     *double保留1位
     */
    public static String doubleFormat(double val){
        BigDecimal mData = new BigDecimal(val).setScale(1, BigDecimal.ROUND_HALF_UP);
        return String.valueOf(mData);
    }
    /**
     *String说保留1位
     */
    public static String tringFormat(String val){
        BigDecimal mData = new BigDecimal(val).setScale(1, BigDecimal.ROUND_HALF_UP);
        return String.valueOf(mData);
    }
}
