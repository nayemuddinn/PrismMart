package com.example.prismmart.Homepage.UI;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentTransaction;


import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;


import com.example.prismmart.Admin_Upload_Product.Upload_Product;
import com.example.prismmart.CartDetails.cart;
import com.example.prismmart.Homepage.Fragment.Homepage_Fragment;
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

    Homepage_Fragment homepageFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);

        drawerLayout = findViewById(R.id.homepage_drawer_layout);
        navigationView = findViewById(R.id.homepage_nav_view);
        toolbar = findViewById(R.id.homepage_toolbar);
        auth = FirebaseAuth.getInstance();
        homepageFragment = new Homepage_Fragment();
        loadFragment(homepageFragment);


        //setting navigation drawer menu
        navigationView.getMenu().clear();
        if (sign_in.userType == "Admin")
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

    private void loadFragment(Homepage_Fragment homepageFragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.homepage_container, homepageFragment);
        transaction.commit();
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
        } else if (item.getItemId() == R.id.nav_add_product) {
            Intent i = new Intent(Homepage.this, Upload_Product.class);
            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(i);
            finish();
        } else if (item.getItemId() == R.id.nav_cart) {
            Intent i = new Intent(Homepage.this, cart.class);
            startActivity(i);
        }
        drawerLayout.closeDrawers();
        return true;

    }


}