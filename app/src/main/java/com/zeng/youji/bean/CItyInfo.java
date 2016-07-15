package com.zeng.youji.bean;

import android.widget.ListView;

import java.util.List;

/**
 * Created by Administrator on 16-7-15.
 */
public class CItyInfo {

    private int id;
    private String name_zh_cn;
    private String name_en;
    private int poi_count;
    private int plans_count;
    private int articles_count;
    private int contents_count;
    private int destination_trips_count;
    private boolean locked;
    private boolean wiki_app_publish;
    private long updated_at;
    private String image_url;
    private int guides_count;
    private List<WeatherInfo> weather;
    private List<DDescription> destination_contents;
    private Intro intro;

    public int getId() {
        return id;
    }

    public String getName_zh_cn() {
        return name_zh_cn;
    }

    public String getName_en() {
        return name_en;
    }

    public int getPlans_count() {
        return plans_count;
    }

    public int getArticles_count() {
        return articles_count;
    }

    public int getContents_count() {
        return contents_count;
    }

    public int getDestination_trips_count() {
        return destination_trips_count;
    }

    public boolean isLocked() {
        return locked;
    }

    public boolean isWiki_app_publish() {
        return wiki_app_publish;
    }

    public long getUpdated_at() {
        return updated_at;
    }

    public String getImage_url() {
        return image_url;
    }

    public int getGuides_count() {
        return guides_count;
    }

    public List<WeatherInfo> getWeather() {
        return weather;
    }

    public List<DDescription> getDestination_contents() {
        return destination_contents;
    }

    public Intro getIntro() {
        return intro;
    }

    public int getPoi_count() {
        return poi_count;
    }

    public static class WeatherInfo {
        private int min_temperature;
        private int max_temperature;
        private int rain_days;

        public int getMin_temperature() {
            return min_temperature;
        }

        public int getMax_temperature() {
            return max_temperature;
        }

        public int getRain_days() {
            return rain_days;
        }
    }

    public static class DDescription {
        private String name;
        private String description_html;

        public String getName() {
            return name;
        }

        public String getDescription_html() {
            return description_html;
        }
    }

    public static class Intro {
        private List<Note> notes;

        public List<Note> getNotes() {
            return notes;
        }

        public static class Note {
            private String description;
            private String photo_url;

            public String getDescription() {
                return description;
            }

            public String getPhoto_url() {
                return photo_url;
            }
        }

    }

}
