package com.berchina.zx.mvpdemo.bean;

import java.util.List;

/**
 * Created by zx on 2017/5/5 11:12
 * 项目名称：MVPDEMO
 * 类描述：
 * 备注
 */
public class NewsBean {
    /**
     * _id : 58eeee54421aa9544b773fcf
     * createdAt : 2017-04-13T11:19:48.659Z
     * desc : 又一个 Swift 动画库，但是这个有些不一样。
     * images : ["http://img.gank.io/c9a91dbf-cc01-4281-a508-b53253f54d54"]
     * publishedAt : 2017-04-13T11:36:04.435Z
     * source : chrome
     * type : iOS
     * url : https://github.com/lkzhao/YetAnotherAnimationLibrary
     * used : true
     * who : ik
     */

    public String _id;
    public String createdAt;
    public String desc;
    public String publishedAt;
    public String source;
    public String type;
    public String url;
    public boolean used;
    public String who;
    public List<String> images;

    @Override
    public String toString() {
        return "NewsBean{" +
                "_id='" + _id + '\'' +
                ", createdAt='" + createdAt + '\'' +
                ", desc='" + desc + '\'' +
                ", publishedAt='" + publishedAt + '\'' +
                ", source='" + source + '\'' +
                ", type='" + type + '\'' +
                ", url='" + url + '\'' +
                ", used=" + used +
                ", who='" + who + '\'' +
                ", images=" + images +
                '}';
    }
}
