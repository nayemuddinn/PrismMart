package com.example.prismmart.Adapter;

import static androidx.core.content.ContextCompat.startActivity;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.prismmart.Homepage.UI.productInfo;
import com.example.prismmart.Model.popularProductModel;
import com.example.prismmart.R;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class popularProductAdapter extends RecyclerView.Adapter<popularProductAdapter.ViewHolder> {


    Context c;
    List<popularProductModel> popularProductModelList;
    List<popularProductModel> popularProductModelListAll;


    public popularProductAdapter(Context c, List<popularProductModel> popularProductModelList) {
        this.c = c;
        this.popularProductModelList = popularProductModelList;
        this.popularProductModelListAll = popularProductModelList;
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

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(c, productInfo.class);
                i.putExtra("productInfo", popularProductModelList.get(position));
                c.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return popularProductModelList.size();
    }


    public void getFilter(String charSequence) {
        List<popularProductModel> filteredList = new ArrayList<>();


        for (popularProductModel product : popularProductModelListAll) {
            if (product.getProductName().toString().toLowerCase().contains(charSequence.toString().toLowerCase())) {
                filteredList.add(product);
            }
        }

        popularProductModelList = filteredList;
        notifyDataSetChanged();

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
