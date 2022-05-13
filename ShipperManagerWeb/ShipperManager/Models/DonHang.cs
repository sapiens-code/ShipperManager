using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace ShipperManager.Models
{
    public enum TrangThaiDonHang
    {
        DangTim,DangGiao,DaGiao
    }
    public class DonHang
    {
        public string Id { get; set; }
        public NhanVien NhanVien { get; set; }
        public KhachHang KhachHang { get; set; }
        public string NgayTao { get; set; }
        public TrangThaiDonHang TrangThaiGiao { get; set; }
        public PhuongThucThanhToan PhuongThucThanhToan { get; set; }
        public decimal TongTien { get; set; } = 0;
        public List<ChiTietDonHang> DanhSachCTTT { get; set; } = new List<ChiTietDonHang>();
        public double KhoanCach { get; set; }
        public string NgayGiao { get; set; }
        public decimal PhiGiaoHang { get; set; }
        public Shipper Shipper { get; set; }

        public DonHang(NhanVien nhanVien, KhachHang khachHang, string ngayTao, PhuongThucThanhToan phuongThucThanhToan,List<ChiTietDonHang> cttt,double khoanCanh,decimal phiGiaoHang)
        {
            NhanVien = nhanVien;
            KhachHang = khachHang;
            NgayTao = ngayTao;
            TrangThaiGiao = TrangThaiDonHang.DangTim;
            PhuongThucThanhToan = phuongThucThanhToan;
            DanhSachCTTT = cttt;
            foreach (var item in DanhSachCTTT) TongTien += item.GetTongTien();
            Shipper = null;
            NgayGiao = string.Empty;
            KhoanCach = khoanCanh;
            PhiGiaoHang = phiGiaoHang;
            
        }

        public DonHang() { }

        
    }
}