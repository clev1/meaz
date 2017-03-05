package com.app.meaz.meaz.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Source {

    @SerializedName("backName")
    @Expose
    private String backName;
    @SerializedName("backPart")
    @Expose
    private String backPart;
    @SerializedName("frontName")
    @Expose
    private String frontName;
    @SerializedName("imageURL")
    @Expose
    private String imageURL;
    @SerializedName("part_1")
    @Expose
    private String part1;
    @SerializedName("part_2")
    @Expose
    private String part2;
    @SerializedName("sizes")
    @Expose
    private String sizes;
    @SerializedName("threadColor")
    @Expose
    private String threadColor;
    @SerializedName("thumbURL")
    @Expose
    private String thumbURL;
    @SerializedName("title")
    @Expose
    private String title;

    public String getBackName() {
        return backName;
    }

    public void setBackName(String backName) {
        this.backName = backName;
    }

    public String getBackPart() {
        return backPart;
    }

    public void setBackPart(String backPart) {
        this.backPart = backPart;
    }

    public String getFrontName() {
        return frontName;
    }

    public void setFrontName(String frontName) {
        this.frontName = frontName;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public String getPart1() {
        return part1;
    }

    public void setPart1(String part1) {
        this.part1 = part1;
    }

    public String getPart2() {
        return part2;
    }

    public void setPart2(String part2) {
        this.part2 = part2;
    }

    public String getSizes() {
        return sizes;
    }

    public void setSizes(String sizes) {
        this.sizes = sizes;
    }

    public String getThreadColor() {
        return threadColor;
    }

    public void setThreadColor(String threadColor) {
        this.threadColor = threadColor;
    }

    public String getThumbURL() {
        return thumbURL;
    }

    public void setThumbURL(String thumbURL) {
        this.thumbURL = thumbURL;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

}