package com.berchina.zxlib.base;

import android.app.Activity;
import android.support.v4.app.Fragment;

/**
 * Created by zx on 2017/3/16 11:03
 * 项目名称：ZxLib
 * 类描述：基类Fragment
 * 备注
 */
public class ZxFragment extends Fragment {

	private static final String LOG_TAG = ZxFragment.class.getName();

	public Activity getContext() {
		return getActivity();
	}
}
