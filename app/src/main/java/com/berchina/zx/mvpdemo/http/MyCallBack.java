package com.berchina.zx.mvpdemo.http;

import android.text.TextUtils;

import com.berchina.zxlib.utils.empty.NotNull;
import com.berchina.zxlib.utils.json.JsonUtils;
import com.google.gson.reflect.TypeToken;
import com.lzy.okgo.callback.StringCallback;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by zx on 2017/5/5 11:22
 * 项目名称：MVPDEMO
 * 类描述：
 * 备注
 */
public abstract class MyCallBack<T> extends StringCallback {
    String result;

    @Override
    public void onSuccess(String s, Call call, Response response) {
        this.result = s;
        resultOK(s);
    }

    @Override
    public void onCacheSuccess(String s, Call call) {
        super.onCacheSuccess(s, call);
        this.result=s;
        resultOK(s);
    }

    @Override
    public void onError(Call call, Response response, Exception e) {
        super.onError(call, response, e);
        resultError();
    }

    @Override
    public void onCacheError(Call call, Exception e) {
        super.onCacheError(call, e);
        resultError();
    }

    public abstract void resultOK(String s);

    public abstract void resultError();

    protected T getEntity(TypeToken<T> typeToken) {
        return JsonUtils.jsonResult(result, typeToken);
    }

    protected <T> List<T> getList(String arrName, TypeToken<ArrayList<T>> typeToken) {
        if (!TextUtils.isEmpty(arrName)) {
            JSONArray jsonArray = JsonUtils.getJSONArray(result, arrName, null);
            if (NotNull.isNotNull(jsonArray)) {
                return JsonUtils.jsonResult(jsonArray.toString(), typeToken);
            }
        }
        return Collections.emptyList();
    }

    protected String getString(String key) {
        return JsonUtils.getString(result, key);
    }
}
