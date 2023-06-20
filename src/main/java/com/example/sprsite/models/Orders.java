package com.example.sprsite.models;


import jakarta.persistence.*;

import java.text.DecimalFormat;
import java.util.Date;
import java.util.Set;
import java.sql.Timestamp;
@Entity
public class Orders {
    public Orders(){
        Date date = new Date();
        time = new Timestamp(date.getTime());
    }

    public long getId() {
        return id;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

    private Timestamp time;

    public Timestamp getTime() {
        return time;
    }

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "order")
    private Set<Order_tovar> order_tovars;

    public Set<Order_tovar> getOrder_tovars() {
        return order_tovars;
    }

    public void setOrder_tovars(Set<Order_tovar> order_tovars) {
        this.order_tovars = order_tovars;
    }

    private String Status;

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public double getTotalPrice(){
        double tp = 0f;
        for (Order_tovar i : order_tovars) {
            tp += i.getPizza().getPrice() * i.getCount();
        }

        return tp;
    }
    public String getTotalPriceString(){
        DecimalFormat df = new DecimalFormat("#.##");
        return df.format(getTotalPrice());
    }


}
