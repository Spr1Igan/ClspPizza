package com.example.sprsite.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String Login;
    private String Pass;
    private boolean admin;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogin() {
        return Login;
    }

    public void setLogin(String title) {
        this.Login = title;
    }

    public String getPass() {
        return Pass;
    }

    public void setPass(String full_text) {
        this.Pass = full_text;
    }

    public boolean getAdmin() {
        return admin;
    }

    public void setAdmin(boolean views) {
        this.admin = views;
    }

    public Person() {
    }

    public Person(String login, String pass, boolean admin) {
        Login = login;
        Pass = pass;
        this.admin = admin;
    }
}
