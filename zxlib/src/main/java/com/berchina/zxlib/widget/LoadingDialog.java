package com.berchina.zxlib.widget;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.berchina.zxlib.R;

/**
 * Created by zx on 2017/5/5 13:44
 * 项目名称：MVPDEMO
 * 类描述：
 * 备注
 */
public class LoadingDialog extends Dialog {
    private AnimationDrawable mAnimationDrawable;

    public LoadingDialog(Context context) {
        this(context,R.style.LoadingDialog);
    }

    public LoadingDialog(Context context, int themeResId) {
        super(context, themeResId);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LayoutInflater inflater = LayoutInflater.from(getContext());
        View v = inflater.inflate(R.layout.loading_dialog, null);// 得到加载view
        RelativeLayout layout = (RelativeLayout) v.findViewById(R.id.dialog_view);// 加载布局
        ImageView iv = (ImageView) v.findViewById(R.id.iv_pic);// 提示文字
        mAnimationDrawable = (AnimationDrawable) iv.getBackground();
        this.setCancelable(true);// 不可以用"返回键"取消
        setContentView(layout);// 设置布局
    }

    /**
     * 开始动画
     */
    public void startAnimation() {
        if (mAnimationDrawable != null) {
            mAnimationDrawable.start();
        }
    }

    /**
     * 结束动画
     */
    public void stopAnimation() {
        if (mAnimationDrawable != null && mAnimationDrawable.isRunning()) {
            mAnimationDrawable.stop();
        }
    }
}
