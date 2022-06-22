package com.example.kasir1912.model;

public class Menu {

    private  String id;
    private String name;
    private String desc;
    private String harga;

    public Menu() {
    }

    public Menu(String name, String desc, String harga) {

        this.name = name;
        this.harga = harga;
        this.desc = desc;

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHarga() {
        return harga;
    }

    public void setHarga(String harga) {
        this.harga = harga;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
