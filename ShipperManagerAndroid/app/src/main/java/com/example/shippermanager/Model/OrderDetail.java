package com.example.shippermanager.Model;

public class OrderDetail {
    private String maSanPham;
    private String ten;
    private  int soLuong;

    public OrderDetail(String maSanPham, String ten, int soLuong) {
        this.maSanPham = maSanPham;
        this.ten = ten;
        this.soLuong = soLuong;
    }

    public OrderDetail() {
    }

    public String getMaSanPham() {
        return maSanPham;
    }

    public void setMaSanPham(String maSanPham) {
        this.maSanPham = maSanPham;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }
}
