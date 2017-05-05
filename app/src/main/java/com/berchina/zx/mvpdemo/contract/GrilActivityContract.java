package com.berchina.zx.mvpdemo.contract;

import com.berchina.zx.mvpdemo.base.BasePresenter;
import com.berchina.zx.mvpdemo.base.BaseView;
import com.berchina.zx.mvpdemo.bean.NewsBean;

import java.util.List;

/**
 * Created by zx on 2017/5/5 15:27
 * 项目名称：MVPDEMO
 * 类描述：
 * 备注
 */
public interface GrilActivityContract {
    interface IGrilActivityView extends BaseView {
        void isShowDialog(boolean isShow);

        void setAdapter(List<NewsBean> results);

        void addData(List<NewsBean> results);

        void stopRefreShing();
    }

    interface IGrilActivityPresenter extends BasePresenter {
        void loadData(boolean isRefresh);
    }
}

