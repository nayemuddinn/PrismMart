package com.example.prismmart.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewpager.widget.PagerAdapter;

import com.example.prismmart.R;

public class SliderAdapter extends PagerAdapter {

    Context context;
    int imageAry[] = {R.drawable.camera_homepage, R.drawable.ecommerce_lead_homepage,R.drawable.homepage_sliding_image5};
    int descriptionAry[] = {R.string.first_slide, R.string.second_slide, R.string.third_slide};
    int headAry[] = {R.string.first_head, R.string.second_head, R.string.third_head};

    public SliderAdapter(Context context) {
        this.context = context;
    }


    @Override
    public int getCount() {
        return headAry.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == (ConstraintLayout) object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {

        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.homepage_sliding_layout, container, false);
        ImageView imageView = view.findViewById(R.id.slider_img);
        TextView head = view.findViewById(R.id.heading);
        TextView description = view.findViewById(R.id.description);

        imageView.setImageResource(imageAry[position]);
        head.setText(headAry[position]);
        description.setText(descriptionAry[position]);

        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((ConstraintLayout) object);
    }
}
