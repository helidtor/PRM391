package com.example.lab3;

public class Fruit {
    private int id;
    private String name;
    private String describe;
    private String img;

    public Fruit(int id, String name, String describe, String img) {
        this.id = id;
        this.name = name;
        this.describe = describe;
        this.img = img;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
}
