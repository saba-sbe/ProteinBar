package com.example.proteinbar;

public class SingleItemModel {

    private int id;
    private String name;
    private int type;
    private String photo;
    private int price;

    public SingleItemModel(int id, String name, int type, int price, String photo){
        this.id = id;
        this.name = name;
        this.type = type;
        this.photo = photo;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String images) {
        this.photo = images;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
