package com.tc.brewery.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

@Entity
public class CartItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JsonIgnoreProperties("cartItems")
    @JoinColumn(name = "cart_id",referencedColumnName = "id") // Foreign key referencing Cart
    private Cart cart;

    @ManyToOne
//    @JsonIgnoreProperties("cartItems")
    @JsonIgnore
    @JoinColumn(name = "beer_id",referencedColumnName = "id") // The foreign key column in CartItem table
    private Beer beer;

    private Integer beerQuantity;
    private Double beerVolumeInMl;
    private Double beerAmount;
    private Double amountOfEachBeer;

    public CartItem() {
    }

    public CartItem(Long id, Cart cart, Beer beer, Long beerId, Integer beerQuantity, Double beerVolumeInMl, Double beerAmount, Double amountOfEachBeer) {
        this.id = id;
        this.cart = cart;
        this.beer = beer;
        this.beerQuantity = beerQuantity;
        this.beerVolumeInMl = beerVolumeInMl;
        this.beerAmount = beerAmount;
        this.amountOfEachBeer = amountOfEachBeer;
    }

    @Override
    public String toString() {
        return "CartItem{" +
                "id=" + id +
                ", cart=" + cart +
                ", beerQuantity=" + beerQuantity +
                ", beerVolumeInMl=" + beerVolumeInMl +
                ", beerAmount=" + beerAmount +
                ", amountOfEachBeer=" + amountOfEachBeer +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public Beer getBeer() {
        return beer;
    }

    public void setBeer(Beer beer) {
        this.beer = beer;
    }


    public Integer getBeerQuantity() {
        return beerQuantity;
    }

    public void setBeerQuantity(Integer beerQuantity) {
        this.beerQuantity = beerQuantity;
    }

    public Double getBeerVolumeInMl() {
        return beerVolumeInMl;
    }

    public void setBeerVolumeInMl(Double beerVolumeInMl) {
        this.beerVolumeInMl = beerVolumeInMl;
    }

    public Double getBeerAmount() {
        return beerAmount;
    }

    public void setBeerAmount(Double beerAmount) {
        this.beerAmount = beerAmount;
    }

    public Double getAmountOfEachBeer() {
        return amountOfEachBeer;
    }

    public void setAmountOfEachBeer(Double amountOfEachBeer) {
        this.amountOfEachBeer = amountOfEachBeer;
    }
}