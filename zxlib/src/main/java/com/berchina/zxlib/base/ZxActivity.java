package com.berchina.zxlib.base;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;

import com.berchina.zxlib.R;
import com.berchina.zxlib.utils.sp.ZxSharedPreferences;

import java.io.Serializable;

import cn.bingoogolapple.swipebacklayout.BGASwipeBackHelper;

/**
 * Created by zx on 2017/3/10 8:05
 * 项目名称：ZxLib
 * 类描述：基类Activity
 * 备注
 */
public abstract class ZxActivity extends FragmentActivity implements IActivity, BGASwipeBackHelper.Delegate {


    protected Class<? extends ZxActivity> TAG = getClass();

    protected ZxApplication application;
    protected Context context;
    protected ZxSharedPreferences preferences;//配置文件

    protected BGASwipeBackHelper mSwipeBackHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        initSwipeBackFinish();
        super.onCreate(savedInstanceState);
        initBaseGlobal();
        bindViewEvent();
        AppManager.getAppManager().addActivity(this);
    }

    /**
     * 初始化全局公用组件
     **/
    private void initBaseGlobal() {
        application = (ZxApplication) getApplication();
        context = application.getContext();
        preferences = ZxSharedPreferences.getInstance(this);
    }

    /**
     * 初始化滑动返回。在 super.onCreate(savedInstanceState) 之前调用该方法
     */
    private void initSwipeBackFinish() {
        mSwipeBackHelper = new BGASwipeBackHelper(this, this);
        // 「必须在 Application 的 onCreate 方法中执行 BGASwipeBackManager.getInstance().init(this) 来初始化滑动返回」
        // 下面几项可以不配置，这里只是为了讲述接口用法。
        // 设置滑动返回是否可用。默认值为 true
        mSwipeBackHelper.setSwipeBackEnable(true);
        // 设置是否仅仅跟踪左侧边缘的滑动返回。默认值为 true
        mSwipeBackHelper.setIsOnlyTrackingLeftEdge(true);
        // 设置是否是微信滑动返回样式。默认值为 true
        mSwipeBackHelper.setIsWeChatStyle(true);
        // 设置阴影资源 id。默认值为 R.drawable.bga_sbl_shadow
        mSwipeBackHelper.setShadowResId(R.drawable.bga_sbl_shadow);
        // 设置是否显示滑动返回的阴影效果。默认值为 true
        mSwipeBackHelper.setIsNeedShowShadow(true);
        // 设置阴影区域的透明度是否根据滑动的距离渐变。默认值为 true
        mSwipeBackHelper.setIsShadowAlphaGradient(true);
        // 设置触发释放后自动滑动返回的阈值，默认值为 0.3f
        mSwipeBackHelper.setSwipeBackThreshold(0.3f);
    }

    /**
     * 是否支持滑动返回。这里在父类中默认返回 true 来支持滑动返回，如果某个界面不想支持滑动返回则重写该方法返回 false 即可
     *
     * @return 是否支持滑动返回  true为支持  默认支持
     */
    @Override
    public boolean isSupportSwipeBack() {
        return true;
    }

    /**
     * 正在滑动返回
     *
     * @param slideOffset 从 0 到 1
     */
    @Override
    public void onSwipeBackLayoutSlide(float slideOffset) {
    }

    /**
     * 没达到滑动返回的阈值，取消滑动返回动作，回到默认状态
     */
    @Override
    public void onSwipeBackLayoutCancel() {
    }

    /**
     * 滑动返回执行完毕，销毁当前 Activity
     */
    @Override
    public void onSwipeBackLayoutExecuted() {
        mSwipeBackHelper.swipeBackward();
    }

    @Override
    public void onBackPressed() {
        // 正在滑动返回的时候取消返回按钮事件
        if (mSwipeBackHelper.isSliding()) {
            return;
        }
        mSwipeBackHelper.backward();
    }

    @Override
    public void getIntentData() {
    }

    @Override
    public void loadData() {
    }


    @Override
    public void bindViewEvent() {
    }

    @Override
    public void showLoadingView() {
    }

    @Override
    public void dismissLoadingView() {
    }

    @Override
    public void showEmptyDataView() {
    }

    @Override
    public void showNetErrorView() {
    }

    @Override
    public boolean processBackPressed() {
        return false;
    }

    protected void doReturnBack() {
        int count = getSupportFragmentManager().getBackStackEntryCount();
        if (count <= 1) {
            finish();
        } else {
            getSupportFragmentManager().popBackStackImmediate();
        }
    }

    /**
     * 跳转到对应的Activity
     */
    protected <T> void showActivity(Class<T> activityCls) {
        showActivity(activityCls, null);
    }

    protected <T> void showActivity(Class<T> activityCls, Bundle extras) {
        Intent intent = new Intent(this, activityCls);
        if (null == extras) {
            startActivity(intent);
        } else {
            intent.putExtras(extras);
            startActivity(intent);
        }
    }

    /**
     * 跳转到对应的Activity返回的时候可以接受到结果
     *
     * @param activityCls 对应的Activity.class
     * @param requestCode 请求码
     */
    protected <T> void showActivityForResult(Class<T> activityCls, int requestCode) {
        showActivityForResult(activityCls, null, requestCode);
    }

    protected <T> void showActivityForResult(Class<T> activityCls, Bundle extras, int requestCode) {
        Intent intent = new Intent(this, activityCls);
        if (null == extras) {
            startActivityForResult(intent, requestCode);
        } else {
            intent.putExtras(extras);
            startActivityForResult(intent, requestCode);
        }
    }

    /**
     * 跳转到对应的Activity by Flags的过滤
     *
     * @param activityCls
     * @param flags
     */
    protected <T> void showActivityByFlags(Class<T> activityCls, int flags) {
        Intent intent = new Intent(this, activityCls);
        intent.setFlags(flags);
        intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        startActivity(intent);
    }

    protected <T> void showActivityByFlags(Class<T> activityCls, Bundle extras, int flags) {
        Intent intent = new Intent(this, activityCls);
        intent.setFlags(flags);
        intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        intent.putExtras(extras);
        startActivity(intent);
    }

    protected <T> void showActivityByClearTop(Class<T> activityCls) {
        Intent intent = new Intent(this, activityCls);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        startActivity(intent);
    }

    protected <T> void showActivityByClearTopResult(Class<T> activityCls, int result) {
        Intent intent = new Intent(this, activityCls);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        setResult(result, intent);
        startActivity(intent);
    }

    protected <T> void showActivityByClearTop(Class<T> activityCls, Bundle extras) {
        Intent intent = new Intent(this, activityCls);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        intent.putExtras(extras);
        startActivity(intent);
    }

    /**
     * 根据resId获取字符串
     **/
    public String getResString(int resId) {
        String text = this.getResources().getString(resId);
        return text;
    }

    /**
     * 得到输入框里面的内容
     **/
    public String getText(TextView textView) {
        String inputText = textView.getText().toString().trim();
        return inputText;
    }

    public int getResColor(int colorResId) {
        return getResources().getColor(colorResId);
    }

    /**
     * 获取intent的serializable数据
     *
     * @param name
     * @return serializable
     */
    @SuppressWarnings("unchecked")
    protected <V extends Serializable> V getSerializableExtra(final String name) {
        return (V) getIntent().getSerializableExtra(name);
    }

    /**
     * Get intent extra
     *
     * @param name
     * @return int
     */
    protected int getIntExtra(final String name) {
        return getIntent().getIntExtra(name, -1);
    }

    /**
     * Get intent extra
     *
     * @param name
     * @return string
     */
    protected String getStringExtra(final String name) {
        return getIntent().getStringExtra(name);
    }

    /**
     * Get intent extra
     *
     * @param name
     * @return string array
     */
    protected String[] getStringArrayExtra(final String name) {
        return getIntent().getStringArrayExtra(name);
    }

    /**
     * Hide keyboard current focus
     */
    protected void hideKeyboardForCurrentFocus() {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        if (getCurrentFocus() != null) {
            imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        }
        imm.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0);
    }

    /**
     * 在此view的焦点上弹出键盘
     *
     * @param view
     */
    protected void showKeyboardAtView(View view) {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(view, InputMethodManager.SHOW_IMPLICIT);
    }

    /**
     * 关闭软键盘
     */
    protected void forceShowKeyboard() {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0);

    }

    /**
     * 退出全屏
     */
    protected void exitFullScreen() {
        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN);
    }
}
