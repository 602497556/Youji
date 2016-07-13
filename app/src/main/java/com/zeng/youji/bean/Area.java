package com.zeng.youji.bean;

import java.util.List;

/**
 * Created by Administrator on 16-7-13.
 */
public class Area {

    private string category;
    private List<Destination> destinationList;


    public string getCategory() {
        return category;
    }

    public List<Destination> getDestinationList() {
        return destinationList;
    }

    public static class Destination {
        private int id;
        private string name_zh_cn;
        private string name_en;
        private int poi_count;
        private double lat;
        private double lng;
        private string image_url;
        private long updated_at;

        public int getId() {
            return id;
        }

        public string getName_zh_cn() {
            return name_zh_cn;
        }

        public long getUpdated_at() {
            return updated_at;
        }

        public string getImage_url() {
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

        public string getName_en() {
            return name_en;
        }

    }

}






