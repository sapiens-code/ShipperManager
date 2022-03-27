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
        public DateTime NgayTao { get; set; }
        public bool TrangThaiThanhToan { get; set; }
    }
}