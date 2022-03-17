using System;
using System.Collections.Generic;
using System.Linq;
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

        public ActionResult Login(string strUrl,FormCollection f)
        {
            
            string username = f["txtUser"].ToString();
            string password = f["txtPass"].ToString();
            if (CheckUser(username, password))
            {
                Session["maTaiKhoan"] = username;
                HttpCookie ck = new HttpCookie("myCookies");
                ck["name"] = username;
                Response.Cookies.Add(ck);
                ck.Expires = DateTime.Now.AddDays(3);//giới hạn thời gian là 3 ngày
                return RedirectToAction("Index", "Home");
            }
            ViewBag.ThongBao = "tài khoản hoặc mật khẩu không đúng";
            return RedirectToAction("Index", "Home");
        }

        private bool CheckUser(string username,string password)
        {
            return true;
        }
    }
}