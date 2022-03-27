using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace ShipperManager.Models
{
    public class Shipper
    {
        public string Id { get; set; }
        public string ten { get; set; }
        public string QueQuan { get; set; }
        public string TaiKhoan { get; set; }
        public string MatKhau { get; set; }
        public bool TrangThai { get; set; }
    }
}