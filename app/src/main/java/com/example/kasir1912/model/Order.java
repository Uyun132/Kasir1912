package com.example.kasir1912.model;

public class Order {

    private String produk,jumlah,total,gedung,lantai,ruangan;
    private String id;

    public Order() {
    }



    public Order(String produk, String jumlah, String total, String gedung, String lantai, String ruangan) {

        this.produk = produk;
        this.jumlah = jumlah;
        this.total = total;
        this.gedung = gedung;
        this.lantai = lantai;
        this.ruangan = ruangan;
    }
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProduk() {
        return produk;
    }

    public void setProduk(String produk) {
        this.produk = produk;
    }

    public String getJumlah() {
        return jumlah;
    }

    public void setJumlah(String jumlah) {
        this.jumlah = jumlah;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getGedung() {
        return gedung;
    }

    public void setGedung(String gedung) {
        this.gedung = gedung;
    }

    public String getLantai() {
        return lantai;
    }

    public void setLantai(String lantai) {
        this.lantai = lantai;
    }

    public String getRuangan() {
        return ruangan;
    }

    public void setRuangan(String ruangan) {
        this.ruangan = ruangan;
    }
}
