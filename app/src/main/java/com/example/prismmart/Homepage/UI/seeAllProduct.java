package com.example.prismmart.Homepage.UI;

import static android.app.PendingIntent.getActivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.SearchView;
import android.widget.Toast;

import com.example.prismmart.Adapter.popularProductAdapter;
import com.example.prismmart.Model.popularProductModel;
import com.example.prismmart.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class seeAllProduct extends AppCompatActivity {

    SearchView searchView;
    RecyclerView AllproductrecyclerView;
    popularProductAdapter adapter;
    List<popularProductModel> popularProductList;
    FirebaseFirestore fstore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_see_all_productt);


        AllproductrecyclerView = findViewById(R.id.see_all_recycle);
        searchView = findViewById(R.id.seeAll_search);
        fstore = FirebaseFirestore.getInstance();

        popularProductList = new ArrayList<>();


        String type = getIntent().getStringExtra("type").toString();

        if (type.contains("All Product")) {
            fstore.collection("All Product").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                    if (task.isSuccessful()) {

                        for (QueryDocumentSnapshot document : task.getResult()) {
                            popularProductModel popularProduct = document.toObject(com.example.prismmart.Model.popularProductModel.class);
                            popularProductList.add(popularProduct);
                            adapter.notifyDataSetChanged();
                        }
                    } else {

                    }
                }
            });
        } else {

            fstore.collection("All Product").whereEqualTo("productCategory",type).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                    if (task.isSuccessful()) {

                        for (QueryDocumentSnapshot document : task.getResult()) {
                            popularProductModel popularProduct = document.toObject(com.example.prismmart.Model.popularProductModel.class);
                            popularProductList.add(popularProduct);
                            adapter.notifyDataSetChanged();
                        }
                    } else {

                    }
                }
            });

        }


        AllproductrecyclerView.setLayoutManager(new GridLayoutManager(getApplicationContext(), 3));
        adapter = new popularProductAdapter(this, popularProductList);
        AllproductrecyclerView.setAdapter(adapter);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                adapter.getFilter(s);
                return true;
            }
        });
    }


}