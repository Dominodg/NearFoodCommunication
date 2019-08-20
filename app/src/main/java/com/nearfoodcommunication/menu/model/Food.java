package com.nearfoodcommunication.menu.model;

import java.io.Serializable;

public class Food implements Serializable {
    private Integer imgid;
    private String foodname;
    private String fooddescription;
    private String foodprice;

    public Food(Integer imgid, String foodname, String fooddescription, String foodprice) {
        this.imgid = imgid;
        this.foodname = foodname;
        this.fooddescription = fooddescription;
        this.foodprice = foodprice;
    }

    public Integer getImgid() {
        return imgid;
    }

    public void setImgid(Integer imgid) {
        this.imgid = imgid;
    }

    public String getFoodname() {
        return foodname;
    }

    public void setFoodname(String foodname) {
        this.foodname = foodname;
    }

    public String getFooddescription() {
        return fooddescription;
    }

    public void setFooddescription(String fooddescription) {
        this.fooddescription = fooddescription;
    }

    public String getFoodprice() {
        return foodprice;
    }

    public void setFoodprice(String foodprice) {
        this.foodprice = foodprice;
    }
}
