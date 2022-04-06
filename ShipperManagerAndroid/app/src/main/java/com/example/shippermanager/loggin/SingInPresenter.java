package com.example.shippermanager.loggin;

import android.util.Log;

import androidx.annotation.NonNull;

import com.example.shippermanager.Model.Shipper;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class SingInPresenter implements LoginInContract.Presenter {


    private LoginInContract.View View;

    public void setView(LoginInContract.View view)
    {
        View = view;
    }

    @Override
    public void handleSingIn(String userName, String password) {
        DatabaseReference database = FirebaseDatabase.getInstance().getReference();
        DatabaseReference ref = database.child("Shipper");
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot child: snapshot.getChildren()) {
                    Shipper s = child.getValue(Shipper.class);
                    if(userName.equals(s.getTaiKhoan())&&password.equals(s.getMatKhau()))
                    {
                        View.loginInSuccess();
                        return;
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.w("error", "Failed to read value.", error.toException());
            }
        });

        View.loginInFailure("Username or Password not true");
    }
}
