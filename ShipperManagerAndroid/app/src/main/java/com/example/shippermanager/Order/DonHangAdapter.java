package com.example.shippermanager.Order;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.shippermanager.Model.DonHang;
import com.example.shippermanager.R;

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
    NumberFormat format = NumberFormat.getCurrencyInstance();

    public DonHangAdapter() {

    }

    public DonHangAdapter(Context context, ArrayList<DonHang> list) {
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
        DonHang dh = list.get(position);
        //Set tiêu đề
        holder.txtLoTrinh.setText(dh.Id);
        holder.txtPhiShip.setText(dh.MaKhachHang);
        holder.txtDiaChi.setText(dh.DiaChi);
        holder.txtTongDon.setText(String.valueOf(dh.TongTien));

        holder.view.setOnClickListener(v -> {
//            Intent intent = new Intent(context, MainActivity.class);
//            context.startActivity(intent);
            try {
                DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                Date date = format.parse(dh.NgayTao);
                Toast.makeText(context,date.toString(),Toast.LENGTH_LONG);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtLoTrinh, txtPhiShip,txtTongDon,txtDiaChi;
        View view;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            //Khai báo id theo itemView
            txtLoTrinh = itemView.findViewById(R.id.txtLoTrinh);
            txtPhiShip = itemView.findViewById(R.id.txtPhiShip);
            txtTongDon = itemView.findViewById(R.id.txtTongDon);
            txtDiaChi = itemView.findViewById(R.id.txtDiaChi);
            view = itemView;
        }
    }

}
