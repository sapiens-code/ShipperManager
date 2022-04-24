package com.example.shippermanager.Order;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

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

import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class DonHangAdapter extends RecyclerView.Adapter<DonHangAdapter.ViewHolder> {
    private Context context;
    private ArrayList<DonHang> list;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;

    NumberFormat format = NumberFormat.getCurrencyInstance();

    public DonHangAdapter() {

    }

    public DonHangAdapter(Context context, ArrayList<DonHang> list) {
        this.context = context;
        this.list = list;
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference().child("DonHang");
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //Set layout cho adapter để hiển thị lên list

        View view = LayoutInflater.from(context).inflate(R.layout.view_don_hang, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        //Trả về 1 item tại vị trí position(vị trí hiện tại theo list)
        DonHang dh = list.get(position);
        //Set tiêu đề
        if(dh.TrangThaiGiao == TrangThaiDonHang.DangTim.ordinal())
            holder.txtTrangThai.setText("đang tìm");
        else
            holder.txtTrangThai.setText("đã nhận");
        holder.txtLoTrinh.setText(String.valueOf(dh.KhoanCach));
        holder.txtPhiShip.setText(String.valueOf( dh.PhiGiaoHang));
        holder.txtDiaChi.setText(dh.KhachHang.DiaChi);
        holder.txtTongDon.setText(String.valueOf(dh.TongTien));

        holder.view.setOnClickListener(v -> {
            //nếu đơn hang đã được nhận
            if(dh.TrangThaiGiao == TrangThaiDonHang.DangGiao.ordinal())
            {
                Intent intent = new Intent(context, ShowOrderActivity.class);
                intent.putExtra("MaDonHang",dh.Id);
                context.startActivity(intent);
            }
            else
            {
                new AlertDialog.Builder(context)
                        .setTitle("Nhận Đơn Hàng")
                        .setMessage("Bạn có muốn nhận đơn hàng này?")
                        // Specifying a listener allows you to take an action before dismissing the dialog.
                        // The dialog is automatically dismissed when a dialog button is clicked.
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                String MY_PREFS_NAME = "MyPrefsFile";
                                SharedPreferences mPrefs = context.getSharedPreferences(MY_PREFS_NAME,0);
                                Gson gson = new Gson();
                                String json = mPrefs.getString("Shipper", "");
                                Shipper obj = gson.fromJson(json, Shipper.class);

                                dh.Shipper = obj;
                                dh.TrangThaiGiao = 1;
                                addDatatoFirebase(dh);
                                Intent intent = new Intent(context, ShowOrderActivity.class);
                                intent.putExtra("MaDonHang",dh.Id);
                                context.startActivity(intent);
                            }
                        })
                        // A null listener allows the button to dismiss the dialog and take no further action.
                        .setNegativeButton(android.R.string.no, null)
                        .setIcon(android.R.drawable.ic_dialog_info)
                        .show();
            }

        });
    }

    private void addDatatoFirebase(DonHang donHang) {

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                databaseReference.child(donHang.Id).setValue(donHang);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtLoTrinh, txtPhiShip,txtTongDon,txtDiaChi,txtTrangThai;
        View view;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            //Khai báo id theo itemView
            txtLoTrinh = itemView.findViewById(R.id.txtLoTrinh);
            txtPhiShip = itemView.findViewById(R.id.txtPhiShip);
            txtTongDon = itemView.findViewById(R.id.txtTongDon);
            txtDiaChi = itemView.findViewById(R.id.txtDiaChi);
            txtTrangThai = itemView.findViewById(R.id.txtTrangThai);
            view = itemView;
        }
    }

}
