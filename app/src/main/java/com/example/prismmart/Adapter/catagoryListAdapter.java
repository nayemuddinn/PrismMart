package com.example.prismmart.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.prismmart.Model.catagoryModel;
import com.example.prismmart.R;

import java.util.List;

public class catagoryListAdapter  extends RecyclerView.Adapter<catagoryListAdapter.ViewHolder> {

    Context c;
    List<catagoryModel> catagoryModelList;

    public catagoryListAdapter(Context c, List<catagoryModel> catagoryModelList) {
        this.c = c;
        this.catagoryModelList = catagoryModelList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view= LayoutInflater.from(c).inflate(R.layout.catagory_list_child,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return catagoryModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView catagoryImage;
        TextView catagoryName;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            catagoryImage=itemView.findViewById(R.id.cat_img);
            catagoryName=itemView.findViewById(R.id.cat_name);


        }
    }
}
