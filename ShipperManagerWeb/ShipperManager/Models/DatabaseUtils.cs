﻿using Firebase.Database;
using Firebase.Database.Query;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using System.Web;

namespace ShipperManager.Models
{


    public class DatabaseUtils
    {
        private static string firebaseUrl = "https://shippermanager-be26e-default-rtdb.firebaseio.com/";

        public static async Task<IReadOnlyCollection<FirebaseObject<T>>> GetAllElement<T>(string path)
        {
            var firebaseClient = new FirebaseClient(firebaseUrl);
            return await firebaseClient
              .Child(path)
              .OnceAsync<T>();

        }

        public static async Task<FirebaseObject<T>> AddElement<T>(string path,T element)
        {
            var firebaseClient = new FirebaseClient(firebaseUrl);
            return await firebaseClient
              .Child(path)
              .PostAsync(element);
        }

        public static async Task<FirebaseObject<T>> GetElementByKey<T>(string path,string key)
        {
            var firebaseClient = new FirebaseClient(firebaseUrl);
            var lst = await firebaseClient
              .Child(path)
              .OrderByKey()
              .EqualTo(key)
              .OnceAsync<T>();
            return lst.FirstOrDefault();
        }
    }
}