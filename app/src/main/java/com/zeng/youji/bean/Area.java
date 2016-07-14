package com.zeng.youji.bean;

import java.util.List;

/**
 * Created by Administrator on 16-7-13.
 */
public class Area {

    private String category;
    private List<Destination> destinations;


    public String getCategory() {
        return category;
    }

    public List<Destination> getDestinations() {
        return destinations;
    }

    public static class Destination {
        private int id;
        private String name_zh_cn;
        private String name_en;
        private int poi_count;
        private double lat;
        private double lng;
        private String image_url;
        private long updated_at;

        public int getId() {
            return id;
        }

        public String getName_zh_cn() {
            return name_zh_cn;
        }

        public long getUpdated_at() {
            return updated_at;
        }

        public String getImage_url() {
            return image_url;
        }

        public double getLng() {
            return lng;
        }

        public double getLat() {
            return lat;
        }

        public int getPoi_count() {
            return poi_count;
        }

        public String getName_en() {
            return name_en;
        }

    }

}






