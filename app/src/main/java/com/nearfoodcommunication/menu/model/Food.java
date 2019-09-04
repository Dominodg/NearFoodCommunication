package com.nearfoodcommunication.menu.model;

import java.io.Serializable;

public class Food implements Serializable {

    private String foodName;
    private Long foodId;
    private String foodDescription;
    private Double foodPrice;
    private String foodPicture;

    public Food(String foodName, Long foodId, String foodDescription, Double foodPrice, String foodPicture) {
        this.foodName = foodName;
        this.foodId = foodId;
        this.foodDescription = foodDescription;
        this.foodPrice = foodPrice;
        this.foodPicture = foodPicture;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public Long getFoodId() {
        return foodId;
    }

    public void setFoodId(Long foodId) {
        this.foodId = foodId;
    }

    public String getFoodDescription() {
        return foodDescription;
    }

    public void setFoodDescription(String foodDescription) {
        this.foodDescription = foodDescription;
    }

    public Double getFoodPrice() {
        return foodPrice;
    }

    public void setFoodPrice(Double foodPrice) {
        this.foodPrice = foodPrice;
    }

    public String getFoodPicture() {
        return foodPicture;
    }

    public void setFoodPicture(String foodPicture) {
        this.foodPicture = foodPicture;
    }
}
