package com.example.month;

import java.util.List;

public class ListBean {

    private List<ItemsBean> items;

    public List<ItemsBean> getItems() {
        return items;
    }

    public void setItems(List<ItemsBean> items) {
        this.items = items;
    }

    public static class ItemsBean {
        /**
         * title : 我是测试数据001
         * image : https://img.huxiucdn.com/article/cover/201809/06/101957946778.jpg?imageView2/1/w/400/h/225/|imageMogr2/strip/interlace/1/quality/85/format/jpg
         * desc : 我是描述信息我是描述信息我是描述信息我是描述信息我是描述信息我是描述信息我是描述信息我是描述信息我是描述信息我是描述信息我是描述信息
         */

        private String title;
        private String image;
        private String desc;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }
    }
}
