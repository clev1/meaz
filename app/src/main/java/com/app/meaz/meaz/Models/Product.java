package com.app.meaz.meaz.Models;


import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class Product {

    public String title;
    public String frontName;
    public String part_1;
    public String part_2;
    public String backName;
    public String backPart;
    public String threadColor;
    public String sizes;
    public String imageURL;
    public String thumbURL;


    public Product() {

    }

    public Product(String title, String frontName, String part, String part2, String backName, String backPart, String sizes, String threadColor, String imageURL, String thumbURL){

        this.title = title;
        this.frontName = frontName;
        this.part_1 = part;
        this.part_2 = part2;
        this.backName = backName;
        this.backPart = backPart;
        this.sizes = sizes;
        this.threadColor = threadColor;
        this.imageURL = imageURL;
        this.thumbURL = thumbURL;

    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getFrontName() {
        return frontName;
    }

    public void setFrontName(String frontName) {
        this.frontName = frontName;
    }

    public String getPart_1() {
        return part_1;
    }

    public void setPart_1(String part_1) {
        this.part_1 = part_1;
    }

    public String getPart_2() {
        return part_2;
    }

    public void setPart_2(String part_2) {
        this.part_2 = part_2;
    }

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

    public String getThreadColor() {
        return threadColor;
    }

    public void setThreadColor(String threadColor) {
        this.threadColor = threadColor;
    }

    public String getSizes() {
        return sizes;
    }

    public void setSizes(String sizes) {
        this.sizes = sizes;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public String getThumbURL() {
        return thumbURL;
    }

    public void setThumbURL(String thumbURL) {
        this.thumbURL = thumbURL;
    }
}