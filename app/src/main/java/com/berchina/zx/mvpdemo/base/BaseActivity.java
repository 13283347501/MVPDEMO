package com.berchina.zx.mvpdemo.base;

import android.os.Bundle;
import android.support.annotation.ColorInt;
import android.support.annotation.IntRange;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;

import com.berchina.zxlib.base.ZxActivity;
import com.berchina.zxlib.utils.toolbar.statusbar.StatusBarUtil;
import com.berchina.zxlib.utils.ui.ToastUtils;
import com.berchina.zxlib.widget.LoadingDialog;

import butterknife.ButterKnife;

/**
 * Created by zx on 2017/5/4 17:40
 * 项目名称：MVPDEMO
 * 类描述：
 * 备注
 */
public abstract class BaseActivity extends ZxActivity {


    private LoadingDialog progressDialog;
    protected boolean canShow;


    @Override
    protected void onResume() {
        super.onResume();
        canShow = true;
    }

    @Override
    protected void onPause() {
        super.onPause();
        canShow = false;
    }

    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        super.setContentView(layoutResID);
        ButterKnife.bind(this);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        beforeInit();
        if (getContentViewLayoutID() != 0) {
            setContentView(getContentViewLayoutID());
            initView(savedInstanceState);
        }
    }

    /**
     * 获取布局ID
     *
     * @return 布局id
     */
    protected abstract int getContentViewLayoutID();

    /**
     * 界面初始化前期准备
     */
    protected void beforeInit() {
    }

    /**
     * 初始化布局以及View控件
     */
    protected abstract void initView(Bundle savedInstanceState);

    public void showToast(String msg) {
        ToastUtils.show(this, msg);
    }

    /**
     * 设置状态栏颜色
     *
     * @param color 颜色
     */
    protected void setStatusBarColor(@ColorInt int color) {
        setStatusBarColor(color, StatusBarUtil.DEFAULT_STATUS_BAR_ALPHA);
    }

    /**
     * 设置状态栏颜色
     *
     * @param color          颜色
     * @param statusBarAlpha 透明度
     */
    public void setStatusBarColor(@ColorInt int color, @IntRange(from = 0, to = 255) int statusBarAlpha) {
        StatusBarUtil.setColorForSwipeBack(this, color, statusBarAlpha);
    }

    /**
     * 显示加载对话框
     **/
    public void showProgressDialog() {
        if (progressDialog != null && progressDialog.isShowing())
            progressDialog.dismiss();
        if (progressDialog == null) {
            progressDialog = new LoadingDialog(this);
        }
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

}
