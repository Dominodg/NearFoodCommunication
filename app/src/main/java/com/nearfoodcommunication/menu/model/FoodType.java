package com.nearfoodcommunication.menu.model;

import java.io.Serializable;
import java.util.List;

public class FoodType implements Serializable {
    private Integer imgid;
    private String foodname;
    private String foodid;
    private List<Food> fooditems;


    public FoodType(Integer imgid, String foodname, String foodid, List<Food> fooditems) {
        this.imgid = imgid;
        this.foodname = foodname;
        this.foodid = foodid;
        this.fooditems = fooditems;
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

    public String getFoodid() {
        return foodid;
    }

    public void setFoodid(String foodid) {
        this.foodid = foodid;
    }

    public List<Food> getFooditems() {
        return fooditems;
    }

    public void setFooditems(List<Food> fooditems) {
        this.fooditems = fooditems;
    }
}
