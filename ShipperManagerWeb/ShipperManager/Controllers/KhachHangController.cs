using Firebase.Database;
using Firebase.Database.Query;
using ShipperManager.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using System.Web;
using System.Web.Mvc;

namespace ShipperManager.Controllers
{
    public class KhachHangController : Controller
    {
        // GET: Customer
        public async Task<ActionResult> Index()
        {
            List<KhachHang> lst = new List<KhachHang>();
            var customers = await DatabaseUtils.GetAllElement<KhachHang>("KhachHang");
            foreach (var item in customers)
            {
                var kh = item.Object;
                kh.Id = item.Key;
                lst.Add(kh);
            }

            return View(lst);
        }

        // GET: Customer/Details/5
        public ActionResult Details(int id)
        {
            return View();
        }

        // GET: Customer/Create
        public ActionResult Create()
        {
            return View();
        }

        // POST: Customer/Create
        [HttpPost]
        public async Task<ActionResult> Create(KhachHang customer)
        {
            try
            {
                await DatabaseUtils.AddElement("KhachHang", customer);
                return RedirectToAction("Index");
            }
            catch (Exception)
            {
                return View();
            }
        }

        // GET: Customer/Edit/5
        public async Task<ActionResult> Edit(string id)
        {
            var kh = await DatabaseUtils.GetElementByKey<KhachHang>(TableCategory.KhachHang, id);
            return View(kh.Object);
        }

        // POST: Customer/Edit/5
        [HttpPost]
        public async Task<ActionResult> Edit(string id, FormCollection collection)
        {
            try
            {
                var fireObj = await DatabaseUtils.GetElementByKey<KhachHang>(TableCategory.KhachHang, id);
                var kh = fireObj.Object;
                kh.Id = fireObj.Key;
                kh.Ten = collection["Ten"];
                kh.DiaChi = collection["DiaChi"];
                kh.SoDienThoai = collection["SoDienThoai"];
                await DatabaseUtils.UpdateElementByKey(TableCategory.KhachHang, kh, kh.Id);

                foreach(var item in await DatabaseUtils.GetAllElement<DonHang>(TableCategory.DonHang))
                {
                    var dh = item.Object;
                    if(dh.KhachHang.Id.Equals(id))
                    {
                        dh.KhachHang = kh;
                        await DatabaseUtils.UpdateElementByKey<DonHang>(TableCategory.DonHang, dh, dh.Id);
                    }
                }

                return RedirectToAction("Index");
            }
            catch(Exception ex)
            {
                return View();
            }
        }

        // GET: Customer/Delete/5
        public ActionResult Delete(int id)
        {
            return View();
        }

        // POST: Customer/Delete/5
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
