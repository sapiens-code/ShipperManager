using ShipperManager.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using System.Web;
using System.Web.Mvc;

namespace ShipperManager.ViewModel
{
    public class DonHangViewModel
    {
        public string Id { get; set; }
        public string MaNhanVien { get; set; }
        public string MaKhachHang { get; set; }
        public string MaPhuongThucThanhToan { get; set; }
        public DateTime NgayTao { get; set; }
        public bool TrangThai { get; set; }

        public IEnumerable<SelectListItem> KhachHangListItem { get; set; }
        public IEnumerable<SelectListItem> PaymentMethodListItem { get; set; }
        public IEnumerable<ChiTietDonHang> OrderDetailListItem { get; set; }

        public DonHangViewModel()
        {
            Task.Run(() => InitKhachHangList()).Wait();
            Task.Run(() => InitPaymentMethodgList()).Wait();
            OrderDetailListItem = new List<ChiTietDonHang>()
            {
                new ChiTietDonHang()
                {
                    Id = "123",
                    MaDonHang = "123",
                    MaSanPham = "123",
                    Ten = "so long",
                    SoLuong = 1
                },
                new ChiTietDonHang()
                {
                    Id = "123",
                    MaDonHang = "123",
                    MaSanPham = "123",
                    Ten = "so huyet",
                    SoLuong = 1
                }
            };
            NgayTao = DateTime.Now;
        }

        private async Task InitKhachHangList()
        {
            var lst = new List<SelectListItem>();
            var customers = await DatabaseUtils.GetAllElement<KhachHang>("KhachHang");
            foreach (var item in customers)
            {
                var kh = item.Object;
                kh.Id = item.Key;
                var selectItem = new SelectListItem()
                {
                    Text = kh.Ten,
                    Value = kh.Id
                };
                lst.Add(selectItem);
            }
            KhachHangListItem = lst;
        }

        private async Task InitPaymentMethodgList()
        {
            var lst = new List<SelectListItem>();
            var elements = await DatabaseUtils.GetAllElement<PhuongThucThanhToan>("PhuongThucThanhToan");
            foreach (var item in elements)
            {
                var kh = item.Object;
                kh.Id = item.Key;
                var selectItem = new SelectListItem()
                {
                    Text = kh.Ten,
                    Value = kh.Id
                };
                lst.Add(selectItem);
            }
            PaymentMethodListItem = lst;
        }
    }
}