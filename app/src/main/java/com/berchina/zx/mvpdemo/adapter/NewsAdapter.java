package com.berchina.zx.mvpdemo.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.berchina.zx.mvpdemo.R;
import com.berchina.zx.mvpdemo.bean.NewsBean;
import com.berchina.zxlib.base.ZxAdapter;
import com.berchina.zxlib.utils.glide.GlideUtils;
import com.berchina.zxlib.utils.time.DatetimeUtil;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by zx on 2017/5/5 11:25
 * 项目名称：FristMVP
 * 类描述：
 * 备注
 */
public class NewsAdapter extends ZxAdapter<NewsBean> {
    public NewsAdapter(Activity context, List<NewsBean> datas) {
        super(context, datas);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.item, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        NewsBean mainBean = datas.get(position);
        if (mainBean.images != null && mainBean.images.size() > 0)
            GlideUtils.loadImageView(context, mainBean.images.get(0), holder.categoryItemImg);
        holder.categoryItemAuthor.setText(mainBean.who);
        holder.categoryItemDesc.setText(mainBean.desc);
        holder.categoryItemSrc.setText(mainBean.source);
        holder.categoryItemTime.setText(DatetimeUtil.dateFormat(mainBean.createdAt));

        return convertView;
    }



    static class ViewHolder {
        @Bind(R.id.category_item_img)
        ImageView categoryItemImg;
        @Bind(R.id.category_item_desc)
        TextView categoryItemDesc;
        @Bind(R.id.category_item_author)
        TextView categoryItemAuthor;
        @Bind(R.id.category_item_src)
        TextView categoryItemSrc;
        @Bind(R.id.category_item_time)
        TextView categoryItemTime;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
