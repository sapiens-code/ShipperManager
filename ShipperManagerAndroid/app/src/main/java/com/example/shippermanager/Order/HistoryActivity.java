package com.example.shippermanager.Order;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;

import com.example.shippermanager.Model.DonHang;
import com.example.shippermanager.Model.Shipper;
import com.example.shippermanager.Model.TrangThaiDonHang;
import com.example.shippermanager.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.gson.Gson;

import java.util.ArrayList;

public class HistoryActivity extends AppCompatActivity {

    private ArrayList<DonHang> OrderList = new ArrayList<>();
    DonHangAdapter adapter;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        setTitle("LỊCH SỬ GIAO HÀNG");
        init();
    }

    private void init() {

        //Set layout recycle view
        recyclerView = findViewById(R.id.history_list);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        //add order list to adapter
        adapter = new DonHangAdapter(this, OrderList);
        recyclerView.setAdapter(adapter);
        //add data to list order
        GetOrderList();
    }

    private void GetOrderList()
    {
        DatabaseReference database = FirebaseDatabase.getInstance().getReference();
        DatabaseReference ref = database.child("DonHang");
        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot child: snapshot.getChildren()) {
                    DonHang o = child.getValue(DonHang.class);
                    o.Id = child.getKey();

                    if(o.TrangThaiGiao == TrangThaiDonHang.DaGiao.ordinal())
                    {
                        String MY_PREFS_NAME = "MyPrefsFile";
                        SharedPreferences mPrefs = getSharedPreferences(MY_PREFS_NAME,0);
                        Gson gson = new Gson();
                        String json = mPrefs.getString("Shipper", "");
                        Shipper obj = gson.fromJson(json, Shipper.class);
                        if(obj.getId().equals(o.Shipper.getId()))
                        {
                            OrderList.add(o);
                            adapter.notifyItemInserted(OrderList.size() -1);
                        }
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