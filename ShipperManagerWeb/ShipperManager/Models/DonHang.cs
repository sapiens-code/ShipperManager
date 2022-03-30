using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace ShipperManager.Models
{
    public class DonHang
    {
        public DonHang(string maNhanVien, string maKhachHang, DateTime ngayTao, bool trangThaiGiao,string maPhuongThucThanhToan,List<ChiTietDonHang> cttt)
        {
            MaNhanVien = maNhanVien;
            MaKhachHang = maKhachHang;
            NgayTao = ngayTao;
            TrangThaiGiao = trangThaiGiao;
            MaPhuongThucThanhToan = maPhuongThucThanhToan;
            DanhSachCTTT = cttt;
        }

        public string Id { get; set; }
        public string MaNhanVien { get; set; }
        public string MaKhachHang { get; set; }
        public DateTime NgayTao { get; set; }
        public bool TrangThaiGiao { get; set; }
        public string MaPhuongThucThanhToan { get; set; }
        public List<ChiTietDonHang> DanhSachCTTT { get; set; }
    }
}