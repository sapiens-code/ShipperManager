package com.example.shippermanager;

import android.os.Bundle;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.shippermanager.DonHangAdapter;
import com.example.shippermanager.R;
import com.example.shippermanager.Model.Order;

import java.util.ArrayList;

public class OrderListActivity extends AppCompatActivity {

    private ArrayList<Order> list = new ArrayList<>();
    DonHangAdapter adapter;
    private RecyclerView recyclerView;
    private LinearLayout linearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_list);
        setTitle("TÌM ĐƠN HÀNG MỚI");
        init();
        Order order = new Order("123","gà chiên nước mắm","20/5 trần quốc tuấn, gò vấp",30000);
        list.add(order);
    }

    public void init() {
        recyclerView = findViewById(R.id.rv_list);
        //Set list
        LinearLayoutManager layoutManager
                = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        //Set list vào adapter
        adapter = new DonHangAdapter(this, list);
        recyclerView.setAdapter(adapter);

    }

}

