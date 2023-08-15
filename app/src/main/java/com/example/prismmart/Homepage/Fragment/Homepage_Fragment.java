package com.example.prismmart.Homepage.Fragment;

import static android.content.ContentValues.TAG;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;
import com.example.prismmart.Adapter.categoryListAdapter;
import com.example.prismmart.Adapter.popularProductAdapter;
import com.example.prismmart.Homepage.UI.seeAllProduct;
import com.example.prismmart.Model.categoryModel;
import com.example.prismmart.Model.popularProductModel;
import com.example.prismmart.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class Homepage_Fragment extends Fragment implements View.OnClickListener {

    RecyclerView categoryRecycleView, popularProductRecycleView;
    categoryListAdapter categoryAdapter;
    popularProductAdapter popularProductAdapter;
    List<popularProductModel> popularProductList;
    List<categoryModel> categoryList;
    FirebaseFirestore fstoreCat;
    TextView seeAll;

    public Homepage_Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_homepage, container, false);

        ImageSlider slider = view.findViewById(R.id.image_slider);
        categoryRecycleView = view.findViewById(R.id.recycle_category);
        popularProductRecycleView = view.findViewById(R.id.popular_recycle);
        seeAll=view.findViewById(R.id.homepage_popular_see_all);


        fstoreCat = FirebaseFirestore.getInstance();

        seeAll.setOnClickListener(this);

        List<SlideModel> slideModelList = new ArrayList<>();

        slideModelList.add(new SlideModel(R.drawable.homepage_sliding_image1, "Welcome to Prism Mart", ScaleTypes.CENTER_CROP));
        slideModelList.add(new SlideModel(R.drawable.homepage_sliding_image2, "Get Exclusive deal", ScaleTypes.CENTER_CROP));
        slideModelList.add(new SlideModel(R.drawable.homepage_sliding_image4, "Multiple payment gateway", ScaleTypes.CENTER_CROP));
        slideModelList.add(new SlideModel(R.drawable.homepage_sliding_image5, "Free delivery", ScaleTypes.CENTER_CROP));
        slideModelList.add(new SlideModel(R.drawable.homepage_sliding_image3, "Up to 70% off", ScaleTypes.CENTER_CROP));
        slideModelList.add(new SlideModel(R.drawable.homepage_sliding_image6, "Worldwide Collection", ScaleTypes.CENTER_CROP));
        slider.setImageList(slideModelList);

        fstoreCat.collection("Catagory List")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {

                                categoryModel categoryModel = document.toObject(com.example.prismmart.Model.categoryModel.class);
                                categoryList.add(categoryModel);
                                categoryAdapter.notifyDataSetChanged();
                            }
                        } else {


                        }
                    }
                });

        fstoreCat.collection("All Product")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {

                            for (QueryDocumentSnapshot document : task.getResult()) {
                                popularProductModel popularProduct = document.toObject(com.example.prismmart.Model.popularProductModel.class);
                                popularProductList.add(popularProduct);
                                popularProductAdapter.notifyDataSetChanged();
                            }
                        } else {

                        }
                    }
                });


        categoryRecycleView.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.HORIZONTAL, false));
        popularProductRecycleView.setLayoutManager(new GridLayoutManager(getActivity(), 3));

        categoryList = new ArrayList<>();
        categoryAdapter = new categoryListAdapter(getActivity(), categoryList);

        popularProductList = new ArrayList<>();
        popularProductAdapter = new popularProductAdapter(getActivity(), popularProductList);
        categoryRecycleView.setAdapter(categoryAdapter);
        popularProductRecycleView.setAdapter(popularProductAdapter);


        return view;
    }

    @Override
    public void onClick(View view) {

        if(view.getId()==R.id.homepage_popular_see_all) {
            Intent i=new Intent(getActivity(),seeAllProduct.class);
            startActivity(i);
        }
    }
}