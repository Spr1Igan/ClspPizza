package com.example.sprsite.models;

import jakarta.persistence.*;

@Entity
public class Order_tovar {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    public long getId() {
        return id;
    }

    @ManyToOne(optional = false)
    @JoinColumn(name="pizza_id", nullable = false, updatable = true)
    private Pizza pizza;

    private int count;

    public Orders getOrders() {
        return order;
    }

    public void setOrders(Orders order) {
        this.order = order;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "order_id", nullable = false)
    private Orders order;

    public Pizza getPizza() {
        return pizza;
    }

    public void setPizza(Pizza pizza) {
        this.pizza = pizza;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
