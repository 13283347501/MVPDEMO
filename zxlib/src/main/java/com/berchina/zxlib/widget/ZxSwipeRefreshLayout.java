package com.berchina.zxlib.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.Gravity;
import android.widget.LinearLayout;

/**
 * Created by zx on 2017/3/23 16:24
 * 项目名称：ZxLib
 * 类描述：下拉刷新上拉加载
 * 备注
 */
public class ZxSwipeRefreshLayout extends SuperSwipeRefreshLayout {
    //监听者
    private OnPullRefreshListener mOnPullRefreshListener;
    //正在刷新
    private boolean mIsRefresh = false;
    //加载更多
    private boolean mIsLoadMore = false;


    public ZxSwipeRefreshLayout(Context context) {
        this(context, null);
    }

    public ZxSwipeRefreshLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }


    private void init() {
        //头布局
        final HeadView headView = new HeadView(getContext());
        setHeaderView(headView);

        //底布局
        final FootView footView = new FootView(getContext());
        LinearLayout linearLayout = new LinearLayout(getContext());
        linearLayout.setGravity(Gravity.CENTER);
        linearLayout.addView(footView);
        setFooterView(linearLayout);

        //设置内部空间不随下拉动  false 不动
//        setTargetScrollWithLayout(false);

        //刷新
        setOnPullRefreshListener(new SuperSwipeRefreshLayout.OnPullRefreshListener() {
            @Override
            public void onRefresh() {
                mOnPullRefreshListener.isRefresh(true);
                headView.startAnimation();
                headView.setMTipText("正在刷新...");
                mIsRefresh = true;
            }

            @Override
            public void onPullDistance(int distance) {
                mOnPullRefreshListener.slideDistance(distance);
                if (distance == 0) {
                    mIsRefresh = false;
                    headView.stopAnimation();
                }

            }

            @Override
            public void onPullEnable(boolean enable) {
                mOnPullRefreshListener.onPullEnable(enable);
                //刷新过程中
                if (!mIsRefresh) {
                    if (enable) {
                        if (!"松开立即刷新...".equals(headView.mTipTextView.getText().toString()))
                            headView.setMTipText("松开立即刷新...");
                    } else {
                        if (!"下拉即可刷新...".equals(headView.mTipTextView.getText().toString()))
                            headView.setMTipText("下拉即可刷新...");
                    }
                }
            }
        });

        //加载更多
        setOnPushLoadMoreListener(new OnPushLoadMoreListener() {
            @Override
            public void onLoadMore() {
                mOnPullRefreshListener.isRefresh(false);
                mIsLoadMore = true;
                footView.startAnimation();
            }

            @Override
            public void onPushDistance(int distance) {
                mOnPullRefreshListener.slideDistance(distance);
                if (distance == 0) {
                    mIsLoadMore = false;
                    footView.stopAnimation();
                }

            }

            @Override
            public void onPushEnable(boolean enable) {
                mOnPullRefreshListener.onPullEnable(enable);
            }
        });
    }

    /**
     * 设置停止刷新
     *
     * @param isRefreshing 是否正在刷新 false 为停止
     */
    public void setRefreShing(boolean isRefreshing) {
        setRefreshing(isRefreshing);
        setLoadMore(isRefreshing);
    }

    /**
     * 设置监听
     *
     * @param onPullRefreshListener onPullRefreshListener
     */
    public void setPullRefreshListener(OnPullRefreshListener onPullRefreshListener) {
        this.mOnPullRefreshListener = onPullRefreshListener;
    }

    /**
     * 定义监听接口
     */
    public interface OnPullRefreshListener {
        //是否为刷新
        void isRefresh(boolean isRefresh);

        //滑动距离
        void slideDistance(int distance);

        // 下拉过程中，下拉的距离是否足够出发刷新
        void onPullEnable(boolean enable);
    }
}
