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
        public List<ChiTietDonHang> OrderDetailListItem { get; set; }

        public DonHangViewModel(List<ChiTietDonHang> orderDetailList)
        {
            Task.Run(() => InitKhachHangList()).Wait();
            Task.Run(() => InitPaymentMethodgList()).Wait();
            OrderDetailListItem = orderDetailList;
            NgayTao = DateTime.Now;
            TrangThai = false;
        }

        public DonHangViewModel()
        {

        }

        public async Task<DonHang> GetDonHang()
        {
            var created = DateTime.Now.ToString("yyyy-MM-dd HH:mm:ss");
            var kh = await DatabaseUtils.GetElementByKey<KhachHang>(TableCategory.KhachHang, MaKhachHang);
            var pttt = await DatabaseUtils.GetElementByKey<PhuongThucThanhToan>(TableCategory.PhuongThucThanhToan, MaPhuongThucThanhToan);
            var nv = await DatabaseUtils.GetElementByKey<NhanVien>(TableCategory.NhanVien, MaNhanVien);
            return new DonHang(nv.Object, kh.Object, created, TrangThai, pttt.Object, OrderDetailListItem,13,25000);
        }

        private async Task InitKhachHangList()
        {
            var lst = new List<SelectListItem>();
            var customers = await DatabaseUtils.GetAllElement<KhachHang>(TableCategory.KhachHang);
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
            var elements = await DatabaseUtils.GetAllElement<PhuongThucThanhToan>(TableCategory.PhuongThucThanhToan);
            foreach (var item in elements)
            {
                var pttt = item.Object;
                pttt.Id = item.Key;
                var selectItem = new SelectListItem()
                {
                    Text = pttt.Ten,
                    Value = pttt.Id
                };
                lst.Add(selectItem);
            }
            PaymentMethodListItem = lst;
        }
    }
}