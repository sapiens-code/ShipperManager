package com.example.shippermanager.Order;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.shippermanager.Model.Order;
import com.example.shippermanager.R;

import java.text.NumberFormat;
import java.util.ArrayList;

public class DonHangAdapter extends RecyclerView.Adapter<DonHangAdapter.ViewHolder> {
    private Context context;
    private ArrayList<Order> list;
    NumberFormat format = NumberFormat.getCurrencyInstance();

    public DonHangAdapter() {

    }

    public DonHangAdapter(Context context, ArrayList<Order> list) {
        this.context = context;
        this.list = list;
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
        Order gh = list.get(position);
        //Set tiêu đề
        holder.ma.setText(gh.Id);
        holder.khachHang.setText(gh.MaKhachHang);
        holder.diaChi.setText(gh.MaPhuongThucThanhToan);
        holder.tongTien.setText("13.000");

//        holder.view.setOnClickListener(v -> {
//            Intent intent = new Intent(context, MainActivity.class);
//            context.startActivity(intent);
//        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView ma, khachHang,diaChi,tongTien;
        View view;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            //Khai báo id theo itemView
            ma = itemView.findViewById(R.id.ma_don_hang);
            diaChi = itemView.findViewById(R.id.dia_chi);
            khachHang = itemView.findViewById(R.id.khach_hang);
            tongTien = itemView.findViewById(R.id.tong_tien);
            view = itemView;
        }
    }

}
