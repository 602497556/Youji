package com.zeng.youji.bean;

import java.util.List;

/**
 * Created by Administrator on 16-7-17.
 */
public class Trip {

    private long id;
    private String name;
    private int photos_count;
    private String start_date;
    private String end_date;
    private int level;
    private List<TripDay> trip_days;

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

    public int getLevel() {
        return level;
    }

    public List<TripDay> getTrip_days() {
        return trip_days;
    }

    public static class TripDay {
        private long id;
        private int day;
        private String trip_date;
        private Destination destination;
        private List<Node> nodes;

        public long getId() {
            return id;
        }

        public int getDay() {
            return day;
        }

        public String getTrip_date(){
            return trip_date;
        }

        public Destination getDestination() {
            return destination;
        }

        public List<Node> getNodes() {
            return nodes;
        }

        public static class Destination{
            private int id;
            private String name_zh_cn;

            public int getId() {
                return id;
            }

            public String getName_zh_cn() {
                return name_zh_cn;
            }
        }

        public static class Node {
            private long id;
            private List<Note> notes;

            public long getId() {
                return id;
            }

            public List<Note> getNotes() {
                return notes;
            }

            public static class Note {
                private long id;
                private String description;
                private Photo photo;
                private String trip_date_note;
                private int day_note;

                public long getId() {
                    return id;
                }

                public String getDescription() {
                    return description;
                }

                public Photo getPhoto() {
                    return photo;
                }

                public String getTrip_date_note() {
                    return trip_date_note;
                }

                public void setTrip_date_note(String trip_date_note) {
                    this.trip_date_note = trip_date_note;
                }

                public int getDay_note() {
                    return day_note;
                }

                public void setDay_note(int day_note) {
                    this.day_note = day_note;
                }

                public static class Photo{
                    private String url;

                    public String getUrl() {
                        return url;
                    }
                }
            }
        }
    }
}
