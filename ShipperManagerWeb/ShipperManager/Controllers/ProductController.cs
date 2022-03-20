using Firebase.Auth;
using Firebase.Storage;
using ShipperManager.Models;
using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Threading;
using System.Threading.Tasks;
using System.Web;
using System.Web.Mvc;

namespace ShipperManager.Controllers
{
    public class ProductController : Controller
    {
        private static string ApiKey = "AIzaSyDydtdaqFseP31FMEtLQDqiUWT-8yyGq1Y";
        private static string Bucket = "shippermanager-be26e.appspot.com";
        private static string AuthEmail = "lamminhhy9999@gmail.com";
        private static string AuthPassword = "01674275557";
        // GET: Product
        public ActionResult Index()
        {
            List<SanPham> lstSp = new List<SanPham>()
            {
                new SanPham("123","áo sơ mi",350000,"áo chât lượng cao nhà làm","Shirt","c:/image"),
                new SanPham("123","áo sơ mi",350000,"áo chât lượng cao nhà làm","Shirt","c:/image"),
                new SanPham("123","áo sơ mi",350000,"áo chât lượng cao nhà làm","Shirt","c:/image"),
            };
            return View(lstSp);
        }

        // GET: Product/Details/5
        public ActionResult Details(int id)
        {
            return View();
        }

        // GET: Product/Create
        public ActionResult Create()
        {
            return View();
        }

        // POST: Product/Create
        [HttpPost]
        public async Task<ActionResult> Create(SanPham product,HttpPostedFileBase file)
        {
            try
            {
                FileStream stream;
                if(file.ContentLength > 0)
                {
                    string path = Path.Combine(Server.MapPath("~/Content/images/"), file.FileName);
                    file.SaveAs(path);
                    stream = new FileStream(Path.Combine(path), FileMode.Open);
                    await Task.Run(() => Upload(stream, file.FileName));
                }

                return RedirectToAction("Index");
            }
            catch(Exception)
            {
                return View();
            }
        }

        private async void Upload(FileStream stream,string fileName)
        {
            // of course you can login using other method, not just email+password
            var auth = new FirebaseAuthProvider(new FirebaseConfig(ApiKey));
            var a = await auth.SignInWithEmailAndPasswordAsync(AuthEmail, AuthPassword);

            // you can use CancellationTokenSource to cancel the upload midway
            var cancellation = new CancellationTokenSource();

            var task = new FirebaseStorage(
                Bucket,
                new FirebaseStorageOptions
                {
                    AuthTokenAsyncFactory = () => Task.FromResult(a.FirebaseToken),
                    ThrowOnCancel = true // when you cancel the upload, exception is thrown. By default no exception is thrown
                })
                .Child("product-images")
                .Child(fileName)
                .PutAsync(stream, cancellation.Token);


            try
            {
                // error during upload will be thrown when you await the task
                string link = await task;
            }
            catch (Exception ex)
            {
                Console.WriteLine("Exception was thrown: {0}", ex);
            }
            finally
            {
                stream.Close();
            }
        }

        // GET: Product/Edit/5
        public ActionResult Edit(int id)
        {
            return View();
        }

        // POST: Product/Edit/5
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

        // GET: Product/Delete/5
        public ActionResult Delete(int id)
        {
            return View();
        }

        // POST: Product/Delete/5
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
