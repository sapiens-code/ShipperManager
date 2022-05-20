package com.example.shippermanager.loggin;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.example.shippermanager.Model.Shipper;
import com.example.shippermanager.R;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener{

    private TextInputEditText txtName;
    private TextInputEditText txtQueQuan;
    private TextInputEditText txtNgaySinh;
    private TextInputEditText txtUsername;
    private TextInputEditText txtPassword;
    private TextInputEditText txtValidatePassword;
    private Button btnRegister;
    private DatabaseReference Database;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        Database = FirebaseDatabase.getInstance().getReference();
        initView();
        registerListener();

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

    }

    // this event will enable the back
    // function to the button on press
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void initView()
    {
        txtName = findViewById(R.id.txt_resigter_name);
        txtQueQuan = findViewById(R.id.txt_register_quequan);
        txtNgaySinh = findViewById(R.id.txt_register_ngaysinh);
        txtUsername = findViewById(R.id.txt_register_username);
        txtPassword = findViewById(R.id.txt_register_password);
        txtValidatePassword = findViewById(R.id.txt_register_validate_password);
        btnRegister = findViewById(R.id.btn_register_dangky);

    }

    private void registerListener()
    {
        btnRegister.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.btn_register_dangky:
                register();
                break;
            default:
                break;


        }
    }

    private void register()
    {
//                Calendar car =  Calendar.getInstance();
//        car.set(Calendar.YEAR,1999);
//        car.set(Calendar.MONTH,10);
//        car.set(Calendar.DAY_OF_MONTH,20);
        String key = Database.child("Shipper").push().getKey();
        String name = txtName.getText().toString();
        String quequan = txtQueQuan.getText().toString();
        String ngaySinh = txtNgaySinh.getText().toString();
        String userName = txtUsername.getText().toString();
        String password = txtPassword.getText().toString();
        String validatePassword = txtValidatePassword.getText().toString();
        if(!password.equals(validatePassword))
        {
            Toast.makeText(getBaseContext(),"đăng ký thật bại, xác nhận mật khẩu không đúng",Toast.LENGTH_SHORT).show();
        }
        else
        {
            Shipper shipper = new Shipper(key,name,ngaySinh,quequan,userName,password,false,0,0);
            Database.child("Shipper").child(key).setValue(shipper);
            startActivity(new Intent(this,LoginActivity.class));
        }

    }
}
