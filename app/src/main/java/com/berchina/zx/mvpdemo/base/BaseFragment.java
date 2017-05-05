package com.berchina.zx.mvpdemo.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.berchina.zxlib.base.ZxFragment;
import com.berchina.zxlib.widget.LoadingDialog;

import butterknife.ButterKnife;


/**
 * Created by zx on 2017/5/5 9:12
 * 项目名称：MVPDEMO
 * 类描述：
 * 备注
 */
public abstract class BaseFragment extends ZxFragment {


    private LoadingDialog progressDialog;
    protected boolean canShow;

    /**
     * 获取布局ID
     */
    protected abstract int getContentViewLayoutID();

    /**
     * 界面初始化
     */
    protected abstract void init();


    @Override
    public void onResume() {
        super.onResume();
        canShow=true;
    }

    @Override
    public void onPause() {
        super.onPause();
        canShow=false;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (getContentViewLayoutID() != 0) {
            return inflater.inflate(getContentViewLayoutID(), container, false);
        } else {
            return super.onCreateView(inflater, container, savedInstanceState);
        }
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        init();
    }

    /**
     * 显示加载对话框
     **/
    public void showProgressDialog() {
        if (progressDialog == null) {
            progressDialog = new LoadingDialog(getActivity());
        }
        if ( progressDialog.isShowing())
            progressDialog.dismiss();
        progressDialog.show();
        progressDialog.startAnimation();
    }

    /**
     * 隐藏对话框
     **/
    public void dismissDialog() {
        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.stopAnimation();
            progressDialog.dismiss();
            progressDialog = null;
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
