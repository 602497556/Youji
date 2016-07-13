package com.zeng.youji.bean;

/**
 * Created by Administrator on 16-7-12.
 */
public class Bean1 {

    private long id;
    private String name;
    private int photos_count;
    private String start_date;
    private String end_date;
    private int days;
    private int level;
    private int views_count;
    private int comments_count;
    private int likes_count;
    private String source;
    private String front_cover_photo_url;
    private boolean featured;
    private User user;

    public void setId(long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhotos_count(int photos_count) {
        this.photos_count = photos_count;
    }

    public void setStart_date(String start_date) {
        this.start_date = start_date;
    }

    public void setEnd_date(String end_date) {
        this.end_date = end_date;
    }

    public void setDays(int days) {
        this.days = days;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public void setViews_count(int views_count) {
        this.views_count = views_count;
    }

    public void setComments_count(int comments_count) {
        this.comments_count = comments_count;
    }

    public void setLikes_count(int likes_count) {
        this.likes_count = likes_count;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public void setFront_cover_photo_url(String front_cover_photo_url) {
        this.front_cover_photo_url = front_cover_photo_url;
    }

    public void setFeatured(boolean featured) {
        this.featured = featured;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getPhotos_count() {
        return photos_count;
    }

    public String getStart_date() {
        return start_date;
    }

    public String getEnd_date() {
        return end_date;
    }

    public int getDays() {
        return days;
    }

    public int getLevel() {
        return level;
    }

    public int getViews_count() {
        return views_count;
    }

    public int getComments_count() {
        return comments_count;
    }

    public int getLikes_count() {
        return likes_count;
    }

    public String getSource() {
        return source;
    }

    public String getFront_cover_photo_url() {
        return front_cover_photo_url;
    }

    public boolean getFeatured() {
        return featured;
    }

    public User getUser() {
        return user;
    }

    public static class User {

        private long id;
        private String name;
        private String image;

        public void setId(long id) {
            this.id = id;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public long getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public String getImage() {
            return image;
        }
    }
}
