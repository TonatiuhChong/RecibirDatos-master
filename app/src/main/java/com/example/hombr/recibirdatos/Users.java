package com.example.hombr.recibirdatos;

public class Users {
    private String id;
    private String email;
    private String name;
    private String password;

    public Users(String pop, String hgg, String s) {
    }

    public Users(String id, String email, String name,String password) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.password = password;
    }

    public String getId() {
        return id;
    }
}
