package com.example.shippermanager.Model;

public class ChiTietDonHang {

    public SanPham SanPham;
    public int SoLuong;

    public ChiTietDonHang(SanPham sanPham) {
        this.SanPham = sanPham;
        this.SoLuong = 1;
    }

    public ChiTietDonHang() {

    }

//    public SanPham getSanPham() {
//        return sanPham;
//    }
//
//    public void setSanPham(SanPham sanPham) {
//        this.sanPham = sanPham;
//    }
//
//    public int getSoLuong() {
//        return soLuong;
//    }
//
//    public void setSoLuong(int soLuong) {
//        this.soLuong = soLuong;
//    }
}
