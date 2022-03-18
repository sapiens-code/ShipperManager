using Firebase.Database;
using Newtonsoft.Json;
using Newtonsoft.Json.Linq;
using ShipperManager.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using System.Web;
using System.Web.Mvc;

namespace ShipperManager.Controllers
{
    public class AccountController : Controller
    {
        // GET: Login
        public ActionResult Index()
        {
            return View();
        }

        public async Task<ActionResult> LoginAsync(string strUrl,FormCollection f)
        {
            string username = f["txtUser"].ToString();
            string password = f["txtPass"].ToString();
            if (await CheckUser(username, password))
            {
                Session["maTaiKhoan"] = username;
                HttpCookie ck = new HttpCookie("myCookies");
                ck["name"] = username;
                Response.Cookies.Add(ck);
                ck.Expires = DateTime.Now.AddDays(3);//giới hạn thời gian là 3 ngày
                return RedirectToAction("Index", "Home");
            }
            TempData["ThongBao"] = "tài khoản hoặc mật khẩu không đúng";
            return Redirect(strUrl);
        }

        private async Task<bool> CheckUser(string username,string password)
        {
            var firebaseClient = new FirebaseClient("https://shippermanager-be26e-default-rtdb.firebaseio.com/");
            var nhanviens = await firebaseClient
              .Child("NhanVien")
              .OnceAsync<NhanVien>();
            foreach(var item in nhanviens)
            {
                var nv = item.Object;
                if (nv.TaiKhoan.Equals(username) && nv.MatKhau.Equals(password)) return true;
            }
            return false;
        }

        
    }
}