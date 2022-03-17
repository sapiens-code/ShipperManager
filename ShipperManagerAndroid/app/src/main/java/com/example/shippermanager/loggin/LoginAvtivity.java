package com.example.shippermanager.loggin;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.shippermanager.MainActivity;
import com.example.shippermanager.OrderListActivity;
import com.example.shippermanager.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;

public class LoginAvtivity extends AppCompatActivity implements LoginInContract.View, View.OnClickListener {

    private TextInputEditText textUsername;
    private TextInputEditText textPassword;
    private Button buttonLogin;
    private SingInPresenter singInPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initView();
        registerListener();
        initPresenter();
    }

    private void initView()
    {
        textPassword = findViewById(R.id.text_password);
        textUsername = findViewById(R.id.text_username);
        buttonLogin = findViewById(R.id.btnDangNhap);
    }

    private void registerListener(){
        buttonLogin.setOnClickListener(this);
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
            default:
                break;


        }
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
        Toast.makeText(this,error,Toast.LENGTH_SHORT).show();
    }
}
