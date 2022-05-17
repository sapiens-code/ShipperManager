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

public class MenuActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnTimDonHang;
    private Button btnDaGiao;
    private Button btnLogout;
    //protected LocationManager locationManager;
    private FusedLocationProviderClient fusedLocationClient;
    private LocationRequest locationRequest;

    String TAG = "menuactivity";

    LocationCallback locationCallback = new LocationCallback() {
        @Override
        public void onLocationResult(LocationResult locationResult) {
            if (locationResult == null) {
                Log.v("location", "= null");
                return;
            }
            for (Location location : locationResult.getLocations()) {
                Log.v("location", location.getLatitude() + "-" + location.getLongitude());
            }
        }
    };

    int LOCATION_REFRESH_TIME = 3000; // 15 seconds to update
    int LOCATION_REFRESH_DISTANCE = 100; // 500 meters to update
    private static final int REQUEST_LOCATION = 1;
    int LOCATION_REQUEST_CODE = 10001;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        setTitle("MENU");
        initView();
        registerListener();

        //locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);

        locationRequest = LocationRequest.create();
        locationRequest.setInterval(4000);
        locationRequest.setFastestInterval(2000);
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
    }

    private void getLastLocation() {
        Task<Location> locationTask = fusedLocationClient.getLastLocation();
        locationTask.addOnSuccessListener(this, new OnSuccessListener<Location>() {
                    @Override
                    public void onSuccess(Location location) {
                        // Got last known location. In some rare situations this can be null.
                        if (location != null) {
                            Log.v("location google service", String.valueOf(location.getLatitude()));
                        }else
                        {
                            Log.d(TAG, "onSuccess: location was null");
                        }
                    }
                });
        locationTask.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.e("fail","on failure");
            }
        });
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

    @Override
    protected void onStart() {
        super.onStart();
        //ask for permission access location
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            checkSettingAndStartLocationUpdate();
            //getLastLocation();
        } else {
            askForPermission();
        }
    }

    private void checkSettingAndStartLocationUpdate() {
        LocationSettingsRequest request = new LocationSettingsRequest.Builder().addLocationRequest(locationRequest).build();
        SettingsClient client = LocationServices.getSettingsClient(this);

        Task<LocationSettingsResponse> locationSettingsResponseTask = client.checkLocationSettings(request);
        locationSettingsResponseTask.addOnSuccessListener(new OnSuccessListener<LocationSettingsResponse>() {
            @Override
            public void onSuccess(LocationSettingsResponse locationSettingsResponse) {
                startLocationUpdate();
            }
        });
        locationSettingsResponseTask.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                if (e instanceof ResolvableApiException) {
                    ResolvableApiException apiException = (ResolvableApiException) e;
                    try {
                        apiException.startResolutionForResult(MenuActivity.this, 1001);
                    } catch (IntentSender.SendIntentException sendIntentException) {
                        sendIntentException.printStackTrace();
                    }
                }
            }
        });

    }

    private void startLocationUpdate() {
        fusedLocationClient.requestLocationUpdates(locationRequest, locationCallback, Looper.getMainLooper());
    }

    @Override
    protected void onStop() {
        super.onStop();
        stopLocationUpdate();
    }

    private void stopLocationUpdate() {
        fusedLocationClient.removeLocationUpdates(locationCallback);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == LOCATION_REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                checkSettingAndStartLocationUpdate();
                //getLastLocation();
            } else {
                askForPermission();
            }
        }
    }

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
