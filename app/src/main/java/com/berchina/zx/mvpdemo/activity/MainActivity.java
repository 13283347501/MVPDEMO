package com.berchina.zx.mvpdemo.activity;


import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.view.View;

import com.berchina.zx.mvpdemo.R;
import com.berchina.zx.mvpdemo.adapter.MyPagerAdapter;
import com.berchina.zx.mvpdemo.base.BaseActivity;
import com.berchina.zx.mvpdemo.fragment.NewsFragment;
import com.berchina.zx.mvpdemo.widget.CircleImageView;
import com.berchina.zxlib.utils.FastClick;
import com.flyco.tablayout.SlidingTabLayout;

import java.util.ArrayList;

import butterknife.Bind;

public class MainActivity extends BaseActivity {

    @Bind(R.id.main_vp)
    ViewPager mainVp;
    @Bind(R.id.main_nv)
    NavigationView mainNv;
    @Bind(R.id.main_dl)
    DrawerLayout mainDl;
    @Bind(R.id.main_stl)
    SlidingTabLayout mainStl;

    // 保存用户按返回键的时间
    private long mExitTime = 0;

    private ArrayList<Fragment> mFragments = new ArrayList<>();

    private final String[] mTitles = {"App", "Android", "iOS", "前端", "瞎推荐", "拓展资源"};
    private MyPagerAdapter mAdapter;

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        setViewPager();
        initDrawerLayout();
    }

    /**
     * 设置viewpager
     */
    private void setViewPager() {
        for (int i = 0; i < mTitles.length; i++) {
            mFragments.add(NewsFragment.newInstance(mTitles[i]));
        }
        mAdapter = new MyPagerAdapter(mFragments, mTitles, getSupportFragmentManager());
        mainVp.setAdapter(mAdapter);
        mainStl.setViewPager(mainVp);
    }

    /**
     * 侧滑布局设置
     */
    private void initDrawerLayout() {
        mainNv.inflateHeaderView(R.layout.layout_main_nav);
        View headerView = mainNv.getHeaderView(0);
        CircleImageView civ = (CircleImageView) headerView.findViewById(R.id.civ_head);
        civ.setImageResource(R.mipmap.ic_launcher);
        headerView.findViewById(R.id.ll_girl).setOnClickListener(new MyLister());
        headerView.findViewById(R.id.ll_history).setOnClickListener(new MyLister());
    }


    /**
     * 点击监听
     */
    class MyLister implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            if (FastClick.isFastClick())
                return;
            switch (v.getId()) {
                case R.id.ll_girl:
                    showActivity(GrilActvity.class);
                    break;

                case R.id.ll_history:
                    showActivity(HistoryActivity.class);
                    break;
            }
            mainDl.closeDrawer(GravityCompat.START);


        }
    }

    @Override
    public void onBackPressed() {
        if ((System.currentTimeMillis() - mExitTime) > 2000) {
            showToast("再按一次退出程序哦~");
            mExitTime = System.currentTimeMillis();
        } else {
            finish();
        }
    }

}
