package com.example.prismmart.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.prismmart.Model.categoryModel;
import com.example.prismmart.R;
import com.google.protobuf.StringValue;

import java.util.ArrayList;
import java.util.List;

public class categoryListAdapter extends RecyclerView.Adapter<categoryListAdapter.ViewHolder> {

    Context c;
    List<categoryModel> catagoryModelList;

    public categoryListAdapter(Context c, List<categoryModel> catagoryModelList) {
        this.c = c;
        this.catagoryModelList = catagoryModelList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(c).inflate(R.layout.catagory_list_child, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Glide.with(c).load(catagoryModelList.get(position).getImage_url()).into(holder.categoryImage);
        holder.categoryName.setText(String.valueOf(catagoryModelList.get(position).getName()));

    }

    @Override
    public int getItemCount() {
        return catagoryModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView categoryImage;
        TextView categoryName;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            categoryImage = itemView.findViewById(R.id.cat_img);
            categoryName = itemView.findViewById(R.id.cat_name);


        }
    }
}
