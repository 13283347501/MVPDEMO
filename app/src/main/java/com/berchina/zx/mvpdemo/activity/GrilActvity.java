package com.berchina.zx.mvpdemo.activity;

import android.os.Bundle;
import android.widget.GridView;

import com.berchina.zx.mvpdemo.R;
import com.berchina.zx.mvpdemo.adapter.GrilAdapter;
import com.berchina.zx.mvpdemo.base.BaseActivity;
import com.berchina.zx.mvpdemo.bean.NewsBean;
import com.berchina.zx.mvpdemo.contract.GrilActivityContract;
import com.berchina.zx.mvpdemo.presenter.GrilActivityPresenter;
import com.berchina.zxlib.widget.ZxSwipeRefreshLayout;

import java.util.List;

import butterknife.Bind;

/**
 * Created by zx on 2017/5/5 15:23
 * 项目名称：MVPDEMO
 * 类描述：
 * 备注
 */
public class GrilActvity extends BaseActivity implements ZxSwipeRefreshLayout.OnPullRefreshListener,
        GrilActivityContract.IGrilActivityView {
    @Bind(R.id.gv)
    GridView gv;
    @Bind(R.id.zsrl)
    ZxSwipeRefreshLayout zsrl;

    GrilActivityPresenter mGrilActivityPresenter;
    GrilAdapter mAdapter;

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_gril;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        zsrl.setPullRefreshListener(this);
        mGrilActivityPresenter = new GrilActivityPresenter(this);
        mGrilActivityPresenter.loadData(true);
    }

    @Override
    public void isRefresh(boolean isRefresh) {
        mGrilActivityPresenter.loadData(isRefresh);
    }

    @Override
    public void slideDistance(int distance) {

    }

    @Override
    public void onPullEnable(boolean enable) {

    }


    @Override
    public void isShowDialog(boolean isShow) {
        if (isShow)
            showProgressDialog();
        else
            dismissDialog();
    }

    @Override
    public void setAdapter(List<NewsBean> results) {
        if (canShow) {
            mAdapter = new GrilAdapter(this, results);
            gv.setAdapter(mAdapter);
        }
    }


    @Override
    public void addData(List<NewsBean> results) {
        if (canShow)
            mAdapter.addData(results);
    }


    @Override
    public void stopRefreShing() {
        if (canShow)
            zsrl.setRefreShing(false);
    }
}
