package com.berchina.zxlib.widget;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.berchina.zxlib.R;
import com.berchina.zxlib.utils.jump.JumpingBeans;

/**
 * Created by zx on 2017/3/24 9:28
 * 项目名称：ZxLib
 * 类描述：头布局
 * 备注
 */
public class HeadView extends RelativeLayout {

    public TextView mTipTextView;
    private ImageView mImageView;
    private AnimationDrawable mAnimationDrawable;

    public HeadView(Context context) {
        this(context, null);

    }

    public HeadView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = inflater.inflate(R.layout.head_view, this);

        mTipTextView = (TextView) v.findViewById(R.id.tv_name);
        mImageView = (ImageView) v.findViewById(R.id.iv_pic);
        mAnimationDrawable = (AnimationDrawable) mImageView.getBackground();
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

    /**
     * 设置文字显示
     *
     * @param text
     */
    public void setMTipText(String text) {
        if (!TextUtils.isEmpty(text)) {
            mTipTextView.setText(text);
            JumpingBeans.with(mTipTextView).appendJumpingDots().build();
        }
    }

}
