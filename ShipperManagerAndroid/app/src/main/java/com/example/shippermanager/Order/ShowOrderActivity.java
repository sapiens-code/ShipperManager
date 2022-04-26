package com.example.shippermanager.Order;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.shippermanager.map.MapsActivity;
import com.example.shippermanager.Model.DonHang;
import com.example.shippermanager.Model.HelperUtils;
import com.example.shippermanager.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.NumberFormat;

public class ShowOrderActivity extends AppCompatActivity implements View.OnClickListener{

    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    private DonHang DonHang;

    private TextView txtTongTien;
    private TextView txtPhiShip;
    private TextView txtThuHo;
    private TextView txtLoTrinh;
    private TextView txtDiaChi;
    private Button btnHuyDon;
    private Button btnXemMap;

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_order);
        assert getSupportActionBar() != null;
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        setTitle("THÔNG TIN ĐƠN HÀNG");
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference().child("DonHang");
        initView();
        registerListener();
    }

    private void registerListener(){

        btnHuyDon.setOnClickListener(this);
        btnXemMap.setOnClickListener(this);
    }

    private void initView()
    {
        txtTongTien = findViewById(R.id.txt_show_order_tong);
        txtPhiShip = findViewById(R.id.txt_show_order_phi_ship);
        txtThuHo = findViewById(R.id.txt_show_order_thu_ho);
        txtLoTrinh = findViewById(R.id.txt_show_order_lo_trinh);
        txtDiaChi = findViewById(R.id.txt_show_order_dia_chi);
        btnHuyDon = findViewById(R.id.btn_show_order_huy_don);
        btnXemMap = findViewById(R.id.btn_show_order_xem_map);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String ma = extras.getString("MaDonHang");
            DatabaseReference database = FirebaseDatabase.getInstance().getReference();
            DatabaseReference ref = database.child("DonHang");
            //money fotmat
            NumberFormat format = HelperUtils.GetNumberFormat();

            ref.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    for (DataSnapshot child: snapshot.getChildren()) {
                        DonHang = child.getValue(DonHang.class);
                        if(DonHang.Id.equals(ma))
                        {
                            double tong = DonHang.TongTien + DonHang.PhiGiaoHang;
                            txtTongTien.setText(format.format(tong));
                            txtPhiShip.setText(format.format(DonHang.PhiGiaoHang));
                            txtThuHo.setText(format.format(DonHang.TongTien));
                            txtLoTrinh.setText(String.valueOf(DonHang.KhoanCach)+"km");
                            txtDiaChi.setText(DonHang.KhachHang.DiaChi);
                            return;
                        }
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    Log.w("error", "Failed to read value.", error.toException());
                }
            });
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.btn_show_order_huy_don:
                CancelOrder();
                break;
            case R.id.btn_show_order_xem_map:
                ViewMap();
                break;
            default:
                break;


        }
    }

    private void ViewMap()
    {
        startActivity(new Intent(this, MapsActivity.class));
    }

    private void CancelOrder()
    {
        DonHang.Shipper = null;
        DonHang.TrangThaiGiao = 0;
        databaseReference.child(DonHang.Id).child("Shipper").setValue(null);
        databaseReference.child(DonHang.Id).child("TrangThaiGiao").setValue(0);
        startActivity(new Intent(this, OrderListActivity.class));
    }
}
