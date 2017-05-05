package com.berchina.zx.mvpdemo.presenter;

import com.berchina.zx.mvpdemo.bean.NewsBean;
import com.berchina.zx.mvpdemo.contract.NewsFragmentContract;
import com.berchina.zx.mvpdemo.http.HttpClient;
import com.berchina.zx.mvpdemo.http.MyCallBack;
import com.google.gson.reflect.TypeToken;
import com.lzy.okgo.request.BaseRequest;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zx on 2017/5/5 11:07
 * 项目名称：MVPDEMO
 * 类描述：
 * 备注
 */
public class NewsFragmentPresenter implements NewsFragmentContract.INewsFragmentPresenter {
    NewsFragmentContract.INewsFragmentView mINewsFragmentView;
    private int page;

    private List<NewsBean> mBeanList;

    public NewsFragmentPresenter(NewsFragmentContract.INewsFragmentView mINewsFragmentView) {
        this.mINewsFragmentView = mINewsFragmentView;
    }

    @Override
    public void loadData(final boolean isRefresh) {
        if (isRefresh) {
            page = 1;
        }
        HttpClient.get("http://gank.io/api/data/" + mINewsFragmentView.getFragmentName() + "/10/" + page,
                "NewsFragment", new MyCallBack<NewsBean>() {
                    @Override
                    public void onBefore(BaseRequest request) {
                        super.onBefore(request);
                        mINewsFragmentView.isShowDialog(true);
                    }

                    @Override
                    public void resultOK(String s) {
                        List<NewsBean> list = getList("results", new TypeToken<ArrayList<NewsBean>>() {
                        });
                        if (isRefresh) {
                            mBeanList = list;
                            page = 2;
                            mINewsFragmentView.setAdapter(mBeanList);
                        } else {
                            page++;
                            mBeanList.addAll(list);
                            mINewsFragmentView.addData(mBeanList);
                        }
                        mINewsFragmentView.stopRefreShing();
                        mINewsFragmentView.isShowDialog(false);
                    }

                    @Override
                    public void resultError() {
                        mINewsFragmentView.stopRefreShing();
                        mINewsFragmentView.isShowDialog(false);
                    }
                });
    }
}
