package com.example.test.model;

public class GameModel {
    String short_name,Title,Platform,thunburl;

    public GameModel(String short_name, String title, String platform, String thunburl) {
        this.short_name = short_name;
        Title = title;
        Platform = platform;
        this.thunburl = thunburl;
    }

    public String getShort_name() {
        return short_name;
    }

    public void setShort_name(String short_name) {
        this.short_name = short_name;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getPlatform() {
        return Platform;
    }

    public void setPlatform(String platform) {
        Platform = platform;
    }

    public String getThunburl() {
        return thunburl;
    }

    public void setThunburl(String thunburl) {
        this.thunburl = thunburl;
    }
}
