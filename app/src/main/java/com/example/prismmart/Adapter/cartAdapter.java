package com.example.prismmart.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.prismmart.Model.cartModel;
import com.example.prismmart.R;

import java.util.List;

public class cartAdapter extends RecyclerView.Adapter<cartAdapter.ViewHolder> {

    Context c;
    List<cartModel> cartList;


    public cartAdapter(Context c, List<cartModel> cartList) {
        this.c = c;
        this.cartList = cartList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(c).inflate(R.layout.cart_child, parent, false);
        return new cartAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.id.setText(cartList.get(position).getProductID().toString());
        holder.name.setText(cartList.get(position).getProductName().toString());
        holder.category.setText(cartList.get(position).getProductCategory().toString());
        holder.price.setText(cartList.get(position).getTotalPrice().toString());
        holder.totalQuantity.setText(cartList.get(position).getTotalQuantity().toString());
        holder.totalPrice.setText(cartList.get(position).getTotalPrice().toString());

    }

    @Override
    public int getItemCount() {
        return cartList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView id, name, price, category, totalPrice, totalQuantity;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            id = itemView.findViewById(R.id.cart_product_ID);
            name = itemView.findViewById(R.id.cart_product_name);
            price = itemView.findViewById(R.id.cart_product_price);
            category = itemView.findViewById(R.id.cart_product_category);
            totalPrice = itemView.findViewById(R.id.cart_total_price);
            totalQuantity = itemView.findViewById(R.id.cart_total_quantity);
        }
    }


}
