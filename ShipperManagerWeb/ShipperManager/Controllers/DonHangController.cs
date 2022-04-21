using ShipperManager.Models;
using ShipperManager.ViewModel;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using System.Web;
using System.Web.Mvc;

namespace ShipperManager.Controllers
{
    public class DonHangController : Controller
    {
        private string OrderDetailSessionName = "OrderDetail";
        // GET: DonHang
        public async Task<ActionResult> Index()
        {
            var lstDonHang = await DatabaseUtils.GetAllElement<DonHang>(TableCategory.DonHang);
            var lst = new List<DonHang>();
            foreach (var item in lstDonHang)
            {
                var dh = item.Object;
                dh.Id = item.Key;
                lst.Add(dh);
            }
            return View(lst);
        }

        public async Task<ActionResult> AddOrderDetail(string proId, string strUrl)
        {
            var p = await DatabaseUtils.GetElementByKey<SanPham>(TableCategory.SanPham, proId);
            if (p == null)
            {
                Response.StatusCode = 400;
                return null;
            }
            List<ChiTietDonHang> orderDetails = GetOrderDetailt();
            var detail = orderDetails.FirstOrDefault(x => x.SanPham.Id.Equals(proId));
            if(detail == null)
            {
                p.Object.Id = p.Key;
                orderDetails.Add(new ChiTietDonHang(p.Object));
                return Redirect(strUrl);
            }
            else
            {
                detail.SoLuong++;
                return Redirect(strUrl);
            }
            
        }

        private List<ChiTietDonHang> GetOrderDetailt()
        {
            List<ChiTietDonHang> lst = Session[OrderDetailSessionName] as List<ChiTietDonHang>;
            if(lst == null)
            {
                lst = new List<ChiTietDonHang>();
                Session[OrderDetailSessionName] = lst;
            }
            return lst;
        }

        private void ClearOrderDetail()
        {
            Session[OrderDetailSessionName] = null;
        }

        private int QuantitySum()
        {
            int sum = 0;
            var lst = Session[OrderDetailSessionName] as List<ChiTietDonHang>;
            if (lst != null)
            {
                sum = lst.Sum(i => i.SoLuong);
            }
            return sum;
        }

        public PartialViewResult OrderDetailPartial()
        {
            int sum = QuantitySum();
            if(sum != 0)
            {
                ViewBag.QuantitySum = sum;
            }
            return PartialView();
        }

        // GET: DonHang/Details/5
        public ActionResult Details(int id)
        {
            return View();
        }

        // GET: DonHang/Create
        public ActionResult Create()
        {
            var lst = GetOrderDetailt();
            DonHangViewModel vm = new DonHangViewModel(lst);
            return View(vm);
        }

        // POST: DonHang/Create
        [HttpPost]
        public async Task<ActionResult> Create(DonHangViewModel donHangViewModel)
        {
            try
            {
                string id = Session["maNhanVien"].ToString();
                var lstDetail = GetOrderDetailt();
                donHangViewModel.MaNhanVien = id;
                donHangViewModel.OrderDetailListItem = lstDetail;

                DonHang dh = await donHangViewModel.GetDonHang();
                await DatabaseUtils.AddElement(TableCategory.DonHang, dh);
                ClearOrderDetail();
                return RedirectToAction("Index");
            }
            catch(Exception)
            {
                return View(donHangViewModel);
            }
        }

        // GET: DonHang/Edit/5
        public ActionResult Edit(int id)
        {
            return View();
        }

        // POST: DonHang/Edit/5
        [HttpPost]
        public ActionResult Edit(int id, FormCollection collection)
        {
            try
            {
                // TODO: Add update logic here

                return RedirectToAction("Index");
            }
            catch
            {
                return View();
            }
        }

        // GET: DonHang/Delete/5
        public ActionResult Delete(int id)
        {
            return View();
        }

        // POST: DonHang/Delete/5
        [HttpPost]
        public ActionResult Delete(int id, FormCollection collection)
        {
            try
            {
                // TODO: Add delete logic here

                return RedirectToAction("Index");
            }
            catch
            {
                return View();
            }
        }
    }
}
