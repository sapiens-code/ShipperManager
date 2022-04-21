package com.example.shippermanager.Model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DonHang {
    public String Id;
    public String MaKhachHang;
    public String MaNhanVien;
    public String MaPhuongThucThanhToan;
    public String NgayTao;
    public boolean TrangThaiGiao;
    public String DiaChi;
    public double TongTien;
    public List<ChiTietDonHang> DanhSachCTTT = new ArrayList<>();

    public DonHang() {
    }

}
