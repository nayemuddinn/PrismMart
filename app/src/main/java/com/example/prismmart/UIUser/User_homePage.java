package com.example.prismmart.UIUser;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import android.os.Bundle;

import com.example.prismmart.R;
import com.google.android.material.navigation.NavigationView;


public class User_homePage extends AppCompatActivity {

    DrawerLayout drawerLayout;

    NavigationView navigationView;
   Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_homepage);



        drawerLayout=findViewById(R.id.user_homepage_drawer_layout);
        navigationView=findViewById(R.id.user_homepage_nav_view);
        toolbar=findViewById(R.id.user_homepage_toolbar);

        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");
        ActionBarDrawerToggle toggle=new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.open_nav,R.string.close_nav);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
    }



}