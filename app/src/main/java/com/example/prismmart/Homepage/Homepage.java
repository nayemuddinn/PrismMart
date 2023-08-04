package com.example.prismmart.Homepage;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.prismmart.Login.sign_in;
import com.example.prismmart.R;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;

public class Homepage extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    DrawerLayout drawerLayout;

    NavigationView navigationView;
    Toolbar toolbar;

    TextView accountType, accountName;
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);

        drawerLayout = findViewById(R.id.admin_homepage_drawer_layout);
        navigationView = findViewById(R.id.admin_homepage_nav_view);
        toolbar = findViewById(R.id.admin_homepage_toolbar);
        auth = FirebaseAuth.getInstance();



        //setting navigation drawer menu
        navigationView.getMenu().clear();
        if(sign_in.userType=="Admin")
        navigationView.inflateMenu(R.menu.admin_homepage_navigation_drawer_menu);
        else
            navigationView.inflateMenu(R.menu.user_homepage_navigation_drawer_menu);

        //setting navigation drawer
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open_nav, R.string.close_nav);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.bringToFront();
        navigationView.setNavigationItemSelectedListener(this);
        View headerView = navigationView.getHeaderView(0);
        accountType = headerView.findViewById(R.id.nav_account_type);
        accountName = headerView.findViewById(R.id.nav_account_name);
        if (sign_in.userType == "Admin")
            accountType.setText(sign_in.userType);
        else
            accountType.setText("Customer");
        accountName.setText(sign_in.userName);


    }

    @Override
    public void onBackPressed() {

        if (drawerLayout.isDrawerOpen(GravityCompat.START))
            drawerLayout.closeDrawers();
        else {
            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_HOME);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        if (item.getItemId() == R.id.nav_logout) {
            Toast.makeText(Homepage.this, "Logged out", Toast.LENGTH_SHORT).show();
            auth.signOut();
            Intent i = new Intent(Homepage.this, sign_in.class);
            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(i);
            finish();
        }
        drawerLayout.closeDrawers();
        return true;

    }


}