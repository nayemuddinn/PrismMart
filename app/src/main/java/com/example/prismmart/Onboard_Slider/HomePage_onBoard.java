package com.example.prismmart.Onboard_Slider;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.prismmart.Adapter.SliderAdapter;
import com.example.prismmart.Homepage.Homepage;
import com.example.prismmart.R;

public class HomePage_onBoard extends AppCompatActivity {

    ViewPager viewpager;
    LinearLayout dotsLayout;
    SliderAdapter sliderAdapter;
    TextView[] dots;
    Button letsGo;
    Animation animation;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page_on_board);

        viewpager = findViewById(R.id.homepage_viewpager);
        sliderAdapter = new SliderAdapter(this);
        viewpager.setAdapter(sliderAdapter);
        dotsLayout = findViewById(R.id.onboard_dots);
        letsGo = findViewById(R.id.onboard_lets_go);

        showDots(0);

        viewpager.addOnPageChangeListener(changeListner);


        letsGo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    startActivity(new Intent(HomePage_onBoard.this, Homepage.class));
                    finish();

            }
        });

    }


    private void showDots(int pos) {
        dots = new TextView[3];
        dotsLayout.removeAllViews();
        for (int i = 0; i < dots.length; i++) {
            dots[i] = new TextView(this);
            dots[i].setText(Html.fromHtml("&#8226"));
            dots[i].setTextSize(35);
            dotsLayout.addView(dots[i]);
        }

        if (dots.length > 0) {
            dots[pos].setTextColor(getResources().getColor(R.color.AppTheme));
        }
    }

    ViewPager.OnPageChangeListener changeListner = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {

            showDots(position);
            if (position == 0)
                letsGo.setVisibility(View.INVISIBLE);
            else if (position == 1)
                letsGo.setVisibility(View.INVISIBLE);
            else {
                animation = AnimationUtils.loadAnimation(HomePage_onBoard.this, R.anim.homepage_onboard_slider_anim);
                letsGo.setAnimation(animation);
                letsGo.setVisibility(View.VISIBLE);
            }


        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };
}