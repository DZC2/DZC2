package com.example.month.bean;

import com.stx.xhb.xbanner.entity.SimpleBannerInfo;

/**
 * author:AbnerMing
 * date:2019/4/2
 */
public class ShopDetailsPicTrueBean extends SimpleBannerInfo {
    private String imageurl;

    public String getImageurl() {
        return imageurl;
    }

    public void setImageurl(String imageurl) {
        this.imageurl = imageurl;
    }

    @Override
    public Object getXBannerUrl() {
        return null;
    }
}
