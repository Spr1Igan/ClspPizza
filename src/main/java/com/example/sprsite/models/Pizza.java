package com.example.sprsite.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
@Entity
public class Pizza {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String Name;
    private String Disc;
    private double Price;
    private String Img;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getDisc() {
        return Disc;
    }

    public void setDisc(String disc) {
        Disc = disc;
    }

    public double getPrice() {
        return Price;
    }

    public void setPrice(double price) {
        Price = price;
    }

    public String getImg() {
        return Img;
    }

    public void setImg(String img) {
        Img = img;
    }

    public Pizza() {
    }

    public Pizza(String name, String disc, double price, String img) {
        Name = name;
        Disc = disc;
        Price = price;
        Img = img;
    }
}
