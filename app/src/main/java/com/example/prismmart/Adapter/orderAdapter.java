package com.example.prismmart.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.prismmart.Model.salesModel;
import com.example.prismmart.R;

import java.util.List;

public class orderAdapter extends RecyclerView.Adapter<orderAdapter.ViewHolder> {

    Context c;
    List<salesModel> orderList;

    public orderAdapter(Context c, List<salesModel> orderList) {
        this.c = c;
        this.orderList = orderList;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(c).inflate(R.layout.order_child, parent, false);
        return new orderAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.orderNo.setText(orderList.get(position).getOrderNo().toString());
        holder.orderDate.setText(orderList.get(position).getOrderDate().toString());
        holder.orderTotalBill.setText(orderList.get(position).getTotalBill().toString());
        holder.orderTime.setText(orderList.get(position).getOrderTime().toString());
        holder.orderAddress.setText(orderList.get(position).getAddress().toString());

    }

    @Override
    public int getItemCount() {
        return orderList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView orderNo, orderDate, orderTime, orderTotalBill, orderAddress;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            orderNo = itemView.findViewById(R.id.order_order_No);
            orderDate = itemView.findViewById(R.id.order_date);
            orderTime = itemView.findViewById(R.id.order_time);
            orderTotalBill = itemView.findViewById(R.id.order_totalBill);
            orderAddress = itemView.findViewById(R.id.order_Address);


        }
    }
}
