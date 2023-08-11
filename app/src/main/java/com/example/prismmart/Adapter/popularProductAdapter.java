package com.example.prismmart.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.prismmart.Model.popularProductModel;
import com.example.prismmart.R;

import java.util.List;

public class popularProductAdapter extends RecyclerView.Adapter<popularProductAdapter.ViewHolder> {


    Context c;
    List<popularProductModel> popularProductModelList;

    public popularProductAdapter(Context c, List<popularProductModel> popularProductModelList) {
        this.c = c;
        this.popularProductModelList = popularProductModelList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(c).inflate(R.layout.populer_product_child, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {


        Glide.with(c).load(popularProductModelList.get(position).getProductImage()).into(holder.popularProductImage);
        holder.popularProductName.setText(String.valueOf(popularProductModelList.get(position).getProductName()));
    }

    @Override
    public int getItemCount() {
        return popularProductModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView popularProductImage;
        TextView popularProductName;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            popularProductImage = itemView.findViewById(R.id.popular_products_image);
            popularProductName = itemView.findViewById(R.id.popular_products_name);

        }
    }
}