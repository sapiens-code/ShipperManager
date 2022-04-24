package com.example.shippermanager.Order;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.shippermanager.Model.DonHang;
import com.example.shippermanager.Model.Shipper;
import com.example.shippermanager.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.gson.Gson;

public class ShowOrderActivity extends AppCompatActivity {

    private TextView txtTongTien;
    private TextView txtPhiShip;
    private TextView txtThuHo;
    private TextView txtLoTrinh;
    private TextView txtDiaChi;

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_order);
        assert getSupportActionBar() != null;   //null check
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        setTitle("THÔNG TIN ĐƠN HÀNG");
        initView();
    }

    private void initView()
    {
        txtTongTien = findViewById(R.id.txt_show_order_tong);
        txtPhiShip = findViewById(R.id.txt_show_order_phi_ship);
        txtThuHo = findViewById(R.id.txt_show_order_thu_ho);
        txtLoTrinh = findViewById(R.id.txt_show_order_lo_trinh);
        txtDiaChi = findViewById(R.id.txt_show_order_dia_chi);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String ma = extras.getString("MaDonHang");
            DatabaseReference database = FirebaseDatabase.getInstance().getReference();
            DatabaseReference ref = database.child("DonHang");
            ref.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    for (DataSnapshot child: snapshot.getChildren()) {
                        DonHang dh = child.getValue(DonHang.class);
                        if(dh.Id.equals(ma))
                        {
                            double tong = dh.TongTien + dh.PhiGiaoHang;
                            txtTongTien.setText(String.valueOf(tong));
                            txtPhiShip.setText(String.valueOf(dh.PhiGiaoHang));
                            txtThuHo.setText(String.valueOf(dh.TongTien));
                            txtLoTrinh.setText(String.valueOf(dh.KhoanCach));
                            txtDiaChi.setText(dh.KhachHang.DiaChi);
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
}
