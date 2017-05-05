package com.berchina.zx.mvpdemo.fragment;

import android.os.Bundle;
import android.widget.ListView;

import com.berchina.zx.mvpdemo.R;
import com.berchina.zx.mvpdemo.adapter.NewsAdapter;
import com.berchina.zx.mvpdemo.base.BaseFragment;
import com.berchina.zx.mvpdemo.bean.NewsBean;
import com.berchina.zx.mvpdemo.contract.NewsFragmentContract;
import com.berchina.zx.mvpdemo.presenter.NewsFragmentPresenter;
import com.berchina.zxlib.widget.ZxSwipeRefreshLayout;

import java.util.List;

import butterknife.Bind;

/**
 * Created by zx on 2017/5/5 10:47
 * 项目名称：MVPDEMO
 * 类描述：
 * 备注
 */
public class NewsFragment extends BaseFragment implements NewsFragmentContract.INewsFragmentView, ZxSwipeRefreshLayout.OnPullRefreshListener {

    @Bind(R.id.lv)
    ListView lv;
    @Bind(R.id.zsrl)
    ZxSwipeRefreshLayout zsrl;
    private String tabName;

    private NewsFragmentPresenter mNewsFragmentPresenter;
    private NewsAdapter mAdapter;
    private String name;

    public static NewsFragment newInstance(String tabName) {
        NewsFragment newsFragment = new NewsFragment();
        Bundle bundle = new Bundle();
        bundle.putString("name", tabName);
        newsFragment.setArguments(bundle);
        return newsFragment;
    }

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.fragment_news;
    }

    @Override
    protected void init() {
        zsrl.setPullRefreshListener(this);
        mNewsFragmentPresenter = new NewsFragmentPresenter(this);
        tabName = getArguments().getString("name");
        mNewsFragmentPresenter.loadData(true);
    }


    @Override
    public void setAdapter(List<NewsBean> results) {
        if (canShow) {
            mAdapter = new NewsAdapter(getActivity(), results);
            lv.setAdapter(mAdapter);
        }
    }

    @Override
    public void isShowDialog(boolean isShow) {
        if (isShow)
            showProgressDialog();
        else
            dismissDialog();
    }

    @Override
    public void stopRefreShing() {
        if (canShow)
            zsrl.setRefreShing(false);
    }

    @Override
    public void addData(List<NewsBean> results) {
        if (canShow)
            mAdapter.addData(results);
    }

    @Override
    public String getFragmentName() {
        return tabName;
    }

    @Override
    public void isRefresh(boolean isRefresh) {
        mNewsFragmentPresenter.loadData(isRefresh);
    }

    @Override
    public void slideDistance(int distance) {

    }

    @Override
    public void onPullEnable(boolean enable) {

    }
}
