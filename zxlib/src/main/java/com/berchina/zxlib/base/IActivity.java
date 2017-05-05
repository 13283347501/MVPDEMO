package com.berchina.zxlib.base;

/**
 * Created by zx on 2017/3/15 14:03
 * 项目名称：ZxLib
 * 类描述：接口
 * 备注
 */
public interface IActivity {

    /**
     * 获取从上一个页面传入的参数信息
     */
    public void getIntentData();

    /**
     * 加载数据
     */
    public void loadData();

    /**
     * 绑定View的触发事件
     */
    public void bindViewEvent();

    /**
     * 显示加载界面
     */
    public void showLoadingView();

    /**
     * 隐藏加载界面
     */
    public void dismissLoadingView();

    /**
     * 显示无数据的界面
     */
    public void showEmptyDataView();

    /**
     * 显示网络错误的界面
     */
    public void showNetErrorView();

    /**
     * 返回按钮的处理
     *
     * @return 如果返回为true，代表back按钮事件已经处理并且留在当前的界面
     */
    public boolean processBackPressed();
}
