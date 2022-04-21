using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace ShipperManager.Models
{
    public class DonHang
    {
        public string Id { get; set; }
        public string MaNhanVien { get; set; }
        public string MaKhachHang { get; set; }
        public string NgayTao { get; set; }
        public bool TrangThaiGiao { get; set; }
        public string MaPhuongThucThanhToan { get; set; }
        public string DiaChi { get; set; }
        public decimal TongTien { get; set; } = 0;
        public List<ChiTietDonHang> DanhSachCTTT { get; set; } = new List<ChiTietDonHang>();

        public DonHang(string maNhanVien, string maKhachHang, string ngayTao, bool trangThaiGiao,string maPhuongThucThanhToan,List<ChiTietDonHang> cttt,string diaChi)
        {
            MaNhanVien = maNhanVien;
            MaKhachHang = maKhachHang;
            NgayTao = ngayTao;
            TrangThaiGiao = trangThaiGiao;
            MaPhuongThucThanhToan = maPhuongThucThanhToan;
            DanhSachCTTT = cttt;
            DiaChi = diaChi;

            foreach (var item in DanhSachCTTT) TongTien += item.GetTongTien();
            
        }

        public DonHang() { }

        
    }
}