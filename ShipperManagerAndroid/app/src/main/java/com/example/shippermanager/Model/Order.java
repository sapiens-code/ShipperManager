package com.example.shippermanager.Model;

public class Order {
    String ma;
    String monHang;
    String diaChi;
    double tongTien;

    public String getMa() {
        return ma;
    }

    public void setMa(String ma) {
        this.ma = ma;
    }

    public String getMonHang() {
        return monHang;
    }

    public void setMonHang(String monHang) {
        this.monHang = monHang;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public double getTongTien() {
        return tongTien;
    }

    public void setTongTien(double tongTien) {
        this.tongTien = tongTien;
    }



    public Order(String ma, String monHang, String diaChi, double tongTien) {
        this.ma = ma;
        this.monHang = monHang;
        this.diaChi = diaChi;
        this.tongTien = tongTien;
    }


}
