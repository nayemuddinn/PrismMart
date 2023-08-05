package com.example.prismmart.Homepage.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;
import com.example.prismmart.R;

import java.util.ArrayList;
import java.util.List;

public class Homepage_Fragment extends Fragment {


    public Homepage_Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_homepage, container, false);

        ImageSlider slider = view.findViewById(R.id.image_slider);
        List<SlideModel> slideModelList = new ArrayList<>();
        slideModelList.add(new SlideModel(R.drawable.camera_homepage, "Camera on Sale", ScaleTypes.CENTER_CROP));

        slideModelList.add(new SlideModel(R.drawable.camera_homepage, "Camera on Sale", ScaleTypes.CENTER_CROP));
        slideModelList.add(new SlideModel(R.drawable.ecommerce_lead_homepage, "Camera on Discount", ScaleTypes.CENTER_CROP));
        slideModelList.add(new SlideModel(R.drawable.camera_homepage, "90% off", ScaleTypes.CENTER_CROP));

        slider.setImageList(slideModelList);
        return view;
    }
}