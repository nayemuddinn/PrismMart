package com.example.prismmart.Homepage.UI;

import static android.app.PendingIntent.getActivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.support.annotation.NonNull;

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

    RecyclerView AllproductrecyclerView;
    popularProductAdapter adapter;
    List<popularProductModel> popularProductList;
    FirebaseFirestore fstore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_see_all_productt);


        AllproductrecyclerView = findViewById(R.id.see_all_recycle);
        fstore = FirebaseFirestore.getInstance();

        popularProductList = new ArrayList<>();

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


        AllproductrecyclerView.setLayoutManager(new GridLayoutManager(getApplicationContext(), 3));
        adapter = new popularProductAdapter(getApplicationContext(), popularProductList);
        AllproductrecyclerView.setAdapter(adapter);
    }
}