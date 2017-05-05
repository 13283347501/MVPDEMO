package com.berchina.zx.mvpdemo.adapter;

import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.berchina.zx.mvpdemo.R;
import com.berchina.zx.mvpdemo.bean.NewsBean;
import com.berchina.zxlib.base.ZxAdapter;
import com.berchina.zxlib.utils.glide.GlideUtils;
import com.berchina.zxlib.utils.ui.ScreenUtils;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by zx on 2017/5/5 15:35
 * 项目名称：MVPDEMO
 * 类描述：
 * 备注
 */
public class GrilAdapter extends ZxAdapter<NewsBean> {


    public GrilAdapter(Activity context, List<NewsBean> datas) {
        super(context, datas);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.item_gril, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        NewsBean mainBean = datas.get(position);
        int width = (ScreenUtils.getScreenWidth(context)) / 2;
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(width, width);
        holder.iv.setLayoutParams(layoutParams);
        if (!TextUtils.isEmpty(mainBean.url))
            GlideUtils.loadImageView(context, mainBean.url, holder.iv);
        return convertView;
    }

    static class ViewHolder {
        @Bind(R.id.iv)
        ImageView iv;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
