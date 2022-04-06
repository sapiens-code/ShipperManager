package com.example.shippermanager.loggin;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.shippermanager.Model.Shipper;
import com.example.shippermanager.Order.OrderListActivity;
import com.example.shippermanager.R;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;

public class LoginActivity extends AppCompatActivity implements LoginInContract.View, View.OnClickListener {

    private TextInputEditText textUsername;
    private TextInputEditText textPassword;
    private Button buttonLogin;
    private Button buttonRegister;
    private SingInPresenter singInPresenter;
    private DatabaseReference Database;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Database = FirebaseDatabase.getInstance().getReference();
        initView();
        registerListener();
        initPresenter();
    }

    private void initView()
    {
        textPassword = findViewById(R.id.text_password);
        textUsername = findViewById(R.id.text_username);
        buttonLogin = findViewById(R.id.btnDangNhap);
        buttonRegister = findViewById(R.id.btnDangKy);
    }

    private void registerListener(){

        buttonLogin.setOnClickListener(this);
        buttonRegister.setOnClickListener(this);
    }

    private void initPresenter(){
        singInPresenter = new SingInPresenter();
        singInPresenter.setView(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.btnDangNhap:
                login();
                break;
            case R.id.btnDangKy:
                Register();
                break;
            default:
                break;


        }
    }

    private void Register()
    {
        Calendar car =  Calendar.getInstance();
        car.set(Calendar.YEAR,1999);
        car.set(Calendar.MONTH,10);
        car.set(Calendar.DAY_OF_MONTH,20);
        String key = Database.child("Shipper").push().getKey();
        Shipper shipper = new Shipper(key,"hy",car.getTimeInMillis(),"Kien Giang",true,"admin","admin");
        Database.child("Shipper").child(key).setValue(shipper);
    }

    private void login()
    {
        String username = textUsername.getText().toString();
        String password = textPassword.getText().toString();

        if(username.isEmpty() || password.isEmpty())
        {
            Toast.makeText(this,"username or password is empty",Toast.LENGTH_SHORT).show();
            return;
        }
        singInPresenter.handleSingIn(username,password);
    }

    @Override
    public void loginInSuccess() {
        Toast.makeText(this,"Loggin is success",Toast.LENGTH_SHORT).show();
        startActivity(new Intent(this, OrderListActivity.class));
    }

    @Override
    public void loginInFailure(String error) {
        Toast.makeText(getBaseContext(),error,Toast.LENGTH_SHORT).show();
    }
}
