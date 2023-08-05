package com.example.prismmart.Homepage.Fragment;

import static android.content.ContentValues.TAG;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;
import com.example.prismmart.Adapter.categoryListAdapter;
import com.example.prismmart.Model.categoryModel;
import com.example.prismmart.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class Homepage_Fragment extends Fragment {

    RecyclerView categoryRecycleView;
    categoryListAdapter categoryAdapter;
    List<categoryModel> categoryList;
    FirebaseFirestore fstoreCat;

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


        fstoreCat = FirebaseFirestore.getInstance();


        List<SlideModel> slideModelList = new ArrayList<>();
        slideModelList.add(new SlideModel(R.drawable.camera_homepage, "Camera on Sale", ScaleTypes.CENTER_CROP));

        slideModelList.add(new SlideModel(R.drawable.camera_homepage, "Camera on Sale", ScaleTypes.CENTER_CROP));
        slideModelList.add(new SlideModel(R.drawable.ecommerce_lead_homepage, "Camera on Discount", ScaleTypes.CENTER_CROP));
        slideModelList.add(new SlideModel(R.drawable.camera_homepage, "90% off", ScaleTypes.CENTER_CROP));

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


        categoryRecycleView.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.HORIZONTAL, false));

        categoryList = new ArrayList<>();
        categoryAdapter = new categoryListAdapter(getActivity(), categoryList);
        categoryRecycleView.setAdapter(categoryAdapter);



        return view;
    }
}