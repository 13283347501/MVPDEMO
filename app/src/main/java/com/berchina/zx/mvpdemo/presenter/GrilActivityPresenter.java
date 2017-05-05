package com.berchina.zx.mvpdemo.presenter;

import com.berchina.zx.mvpdemo.bean.NewsBean;
import com.berchina.zx.mvpdemo.contract.GrilActivityContract;
import com.berchina.zx.mvpdemo.http.HttpClient;
import com.berchina.zx.mvpdemo.http.MyCallBack;
import com.google.gson.reflect.TypeToken;
import com.lzy.okgo.request.BaseRequest;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zx on 2017/5/5 15:31
 * 项目名称：MVPDEMO
 * 类描述：
 * 备注
 */
public class GrilActivityPresenter implements GrilActivityContract.IGrilActivityPresenter {
    private int page;
    private List<NewsBean> mBeanList;
    private GrilActivityContract.IGrilActivityView mIGrilActivityView;

    public GrilActivityPresenter(GrilActivityContract.IGrilActivityView mIGrilActivityView) {
        this.mIGrilActivityView = mIGrilActivityView;
    }

    @Override
    public void loadData(final boolean isRefresh) {
        if (isRefresh) {
            page = 1;
        }

        HttpClient.get("http://gank.io/api/data/福利" + "/10/" + page,
                "NewsFragment", new MyCallBack<NewsBean>() {
                    @Override
                    public void onBefore(BaseRequest request) {
                        super.onBefore(request);
                        mIGrilActivityView.isShowDialog(true);
                    }

                    @Override
                    public void resultOK(String s) {
                        List<NewsBean> list = getList("results", new TypeToken<ArrayList<NewsBean>>() {
                        });
                        if (isRefresh) {
                            mBeanList = list;
                            page = 2;
                            mIGrilActivityView.setAdapter(mBeanList);
                        } else {
                            page++;
                            mBeanList.addAll(list);
                            mIGrilActivityView.addData(mBeanList);
                        }
                        mIGrilActivityView.stopRefreShing();
                        mIGrilActivityView.isShowDialog(false);
                    }

                    @Override
                    public void resultError() {
                        mIGrilActivityView.stopRefreShing();
                        mIGrilActivityView.isShowDialog(false);
                    }
                });
    }
}
