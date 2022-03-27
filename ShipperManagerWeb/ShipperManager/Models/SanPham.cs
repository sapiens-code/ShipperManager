using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.Linq;
using System.Web;

namespace ShipperManager.Models
{
    public class SanPham
    {
        public string Id { get; set; }
        [Display(Name = "Tên")]
        public string Ten { get; set; }
        [Display(Name = "Giá")]
        public decimal Gia { get; set; }
        [Display(Name = "Mô Tả")]
        public string MoTa { get; set; }
        [Display(Name = "Danh Mục")]
        public string DanhMuc { get; set; }
        [Display(Name = "Đường Dẫn Ảnh")]
        public string ImagePath { get; set; }

        public SanPham()
        {

        }

        public SanPham(string id, string ten, decimal gia, string moTa, string danhMuc, string imagePath)
        {
            Id = id;
            Ten = ten;
            Gia = gia;
            MoTa = moTa;
            DanhMuc = danhMuc;
            ImagePath = imagePath;
        }

        public SanPham(string ten, decimal gia, string moTa, string danhMuc, string imagePath)
        {
            Ten = ten;
            Gia = gia;
            MoTa = moTa;
            DanhMuc = danhMuc;
            ImagePath = imagePath;
        }


    }
}