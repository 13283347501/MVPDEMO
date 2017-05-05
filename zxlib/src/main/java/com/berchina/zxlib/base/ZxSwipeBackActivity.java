
/*package com.berchina.zxlib.base;

import android.view.View;

import com.komi.slider.ISlider;
import com.komi.slider.SliderConfig;
import com.komi.slider.SliderUtils;


public class ZxSwipeBackActivity extends ZxActivity  {

    protected ISlider iSlider;
    protected SliderConfig mConfig;

    @Override
    public void setContentView(View view) {
        super.setContentView(view);
        attachSlideUi();
    }

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
        attachSlideUi();
    }

    private void attachSlideUi() {
        iSlider = SliderUtils.attachActivity(this, mConfig);
        if (mConfig == null) {
            mConfig = iSlider.getConfig();
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        iSlider.slideExit();
    }

    @Override
    public void setDataToView() {

    }

    @Override
    public void initView() {

    }
}*/
