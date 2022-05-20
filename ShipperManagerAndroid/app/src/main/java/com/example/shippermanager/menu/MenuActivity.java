package com.example.shippermanager.menu;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.IntentSender;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.provider.FontsContractCompat;

import com.example.shippermanager.Model.HelperUtils;
import com.example.shippermanager.Model.LocationService;
import com.example.shippermanager.Model.Shipper;
import com.example.shippermanager.Order.HistoryActivity;
import com.example.shippermanager.Order.OrderListActivity;
import com.example.shippermanager.R;
import com.example.shippermanager.loggin.LoginActivity;
import com.google.android.gms.common.api.ResolvableApiException;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResponse;
import com.google.android.gms.location.SettingsClient;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.gson.Gson;

public class MenuActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnTimDonHang;
    private Button btnDaGiao;
    private Button btnLogout;

    String TAG = "MenuActivity";

    int LOCATION_REQUEST_CODE = 10001;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        setTitle("MENU");
        initView();
        registerListener();

    }



    private void askForPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            if(ActivityCompat.shouldShowRequestPermissionRationale(this,Manifest.permission.ACCESS_FINE_LOCATION))
            {
                ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION}, LOCATION_REQUEST_CODE);
            }else
            {
                ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION}, LOCATION_REQUEST_CODE);
            }
        }

    }

//    @Override
//    protected void onStart() {
//        super.onStart();
//        //ask for permission access location
////        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
////            startService(new Intent(this, LocationService.class));
////        } else {
////            askForPermission();
////        }
//    }

    private void initView() {
        btnTimDonHang = findViewById(R.id.btnTimDonHang);
        btnDaGiao = findViewById(R.id.btnDaGiao);
        btnLogout = findViewById(R.id.btnLogout);
    }

    private void registerListener() {
        btnTimDonHang.setOnClickListener(this);
        btnDaGiao.setOnClickListener(this);
        btnLogout.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnTimDonHang:
                TimDonHang();
                break;
            case R.id.btnDaGiao:
                ShowHistory();
                break;
            case R.id.btnLogout:
                Logout();
                break;
            default:
                break;
        }
    }

    private void ShowHistory() {
        startActivity(new Intent(this, HistoryActivity.class));
    }

    private void TimDonHang() {
        startActivity(new Intent(this, OrderListActivity.class));
    }

    private void Logout() {
        startActivity(new Intent(this, LoginActivity.class));
    }

}
