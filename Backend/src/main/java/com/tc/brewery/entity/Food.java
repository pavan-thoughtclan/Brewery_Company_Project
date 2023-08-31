package com.tc.brewery.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Food {
    @Id
    private int id;
    private String food_name;
    private String category;
    private String image_url;
    private BigDecimal food_price;
    private String description;
    private String calories;
    @OneToMany(mappedBy = "food",cascade= CascadeType.ALL)
    @JsonIgnoreProperties("food")
    private List<CartItem> cartItems = new ArrayList<>();

    public Food() {
    }

    public Food(int id, String food_name, String category, String image_url, BigDecimal food_price, String description, String calories, List<CartItem> cartItems) {
        this.id = id;
        this.food_name = food_name;
        this.category = category;
        this.image_url = image_url;
        this.food_price = food_price;
        this.description = description;
        this.calories = calories;
//        this.cartItems = cartItems;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFood_name() {
        return food_name;
    }

    public void setFood_name(String food_name) {
        this.food_name = food_name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public BigDecimal getFood_price() {
        return food_price;
    }

    public void setFood_price(BigDecimal food_price) {
        this.food_price = food_price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCalories() {
        return calories;
    }

    public void setCalories(String calories) {
        this.calories = calories;
    }

    @Override
    public String toString() {
        return "Food{" +
                "id=" + id +
                ", food_name='" + food_name + '\'' +
                ", category='" + category + '\'' +
                ", image_url='" + image_url + '\'' +
                ", food_price=" + food_price +
                ", description='" + description + '\'' +
                ", calories='" + calories + '\'' +
//                ", cartItems=" + cartItems +
                '}';
    }
}

