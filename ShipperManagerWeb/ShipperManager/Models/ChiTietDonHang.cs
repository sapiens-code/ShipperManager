using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using System.Web;

namespace ShipperManager.Models
{
    public class ChiTietDonHang
    {
        public string Id { get; set; }
        public string Ten { get; set; }
        public string MaSanPham { get; set; }
        public string MaDonHang { get; set; }
        public int SoLuong { get; set; }

        public ChiTietDonHang(SanPham sp)
        {
            Ten = sp.Ten;
            MaSanPham = sp.Id;
            SoLuong = 1;
        }

        public ChiTietDonHang()
        {
        }

        private async Task InitAsync(string id)
        {
            var p = await DatabaseUtils.GetElementByKey<SanPham>("SanPham", id);
            if(p != null)
            {
                Ten = p.Object.Ten;
                MaSanPham = id;
                SoLuong = 1;
            }
        }
    }
}