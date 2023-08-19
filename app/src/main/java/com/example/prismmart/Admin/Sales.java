package com.example.prismmart.Admin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.widget.Toast;

import com.example.prismmart.Adapter.cartAdapter;
import com.example.prismmart.Adapter.salesAdapter;
import com.example.prismmart.Model.cartModel;
import com.example.prismmart.Model.popularProductModel;
import com.example.prismmart.Model.salesModel;
import com.example.prismmart.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class Sales extends AppCompatActivity {

    RecyclerView salesRecycle;

    FirebaseFirestore fstore;
    salesAdapter adapter;
    List<salesModel> salesList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sales);

        salesRecycle = findViewById(R.id.sales_recycle);

        fstore = FirebaseFirestore.getInstance();

        fstore.collection("SalesAdmin").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {

                    for (QueryDocumentSnapshot document : task.getResult()) {
                        salesModel sales = document.toObject(salesModel.class);
                        salesList.add(sales);
                        adapter.notifyDataSetChanged();

                    }
                }
            }
        });

        salesList = new ArrayList<>();
        salesRecycle.setLayoutManager(new LinearLayoutManager(this));
        adapter = new salesAdapter(Sales.this, salesList);
        salesRecycle.setAdapter(adapter);
    }
}
