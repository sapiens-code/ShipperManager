package com.example.shippermanager.menu;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.shippermanager.Order.HistoryActivity;
import com.example.shippermanager.Order.OrderListActivity;
import com.example.shippermanager.R;
import com.example.shippermanager.loggin.LoginActivity;

public class MenuActivity extends AppCompatActivity implements View.OnClickListener{

    private Button btnTimDonHang;
    private Button btnDaGiao;
    private Button btnLogout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        setTitle("MENU");
        initView();
        registerListener();
    }

    private void initView()
    {
        btnTimDonHang = findViewById(R.id.btnTimDonHang);
        btnDaGiao = findViewById(R.id.btnDaGiao);
        btnLogout = findViewById(R.id.btnLogout);
    }

    private void registerListener(){

        btnTimDonHang.setOnClickListener(this);
        btnDaGiao.setOnClickListener(this);
        btnLogout.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
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
    private void ShowHistory(){startActivity(new Intent(this, HistoryActivity.class));}
    private void TimDonHang()
    {
        startActivity(new Intent(this, OrderListActivity.class));
    }
    private void Logout()
    {
        startActivity(new Intent(this, LoginActivity.class));
    }
}
