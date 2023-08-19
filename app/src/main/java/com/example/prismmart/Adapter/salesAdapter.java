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


public class salesAdapter extends RecyclerView.Adapter<salesAdapter.ViewHolder> {

    Context c;
    List<salesModel> salesList;

    public salesAdapter(Context c, List<salesModel> salesList) {
        this.c = c;
        this.salesList = salesList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(c).inflate(R.layout.sales_child, parent, false);
        return new salesAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.orderNo.setText(salesList.get(position).getOrderNo().toString());
        holder.date.setText(salesList.get(position).getOrderDate().toString());
        holder.totalRevene.setText(salesList.get(position).getTotalBill().toString());
    }

    @Override
    public int getItemCount() {
        return salesList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView orderNo, date, totalRevene;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            orderNo = itemView.findViewById(R.id.sales_order_No);
            date = itemView.findViewById(R.id.sales_date);
            totalRevene = itemView.findViewById(R.id.sales_totalRevenue);
        }
    }
}
