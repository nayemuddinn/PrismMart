package com.example.prismmart.Homepage.UI;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.support.annotation.NonNull;

import com.example.prismmart.Adapter.orderAdapter;
import com.example.prismmart.Adapter.salesAdapter;
import com.example.prismmart.Admin.Sales;
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

public class Orders extends AppCompatActivity {

    RecyclerView orderRecycle;

    FirebaseFirestore fstore;
    FirebaseAuth mAuth;
    orderAdapter adapter;
    List<salesModel> orderList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orders);

        orderRecycle = findViewById(R.id.order_recycle);

        fstore = FirebaseFirestore.getInstance();
        mAuth = FirebaseAuth.getInstance();

        fstore.collection("CustomerOrder").document(mAuth.getCurrentUser().getUid().toString()).collection("Order").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {

                    for (QueryDocumentSnapshot document : task.getResult()) {
                        salesModel sales = document.toObject(salesModel.class);
                        orderList.add(sales);
                        adapter.notifyDataSetChanged();

                    }
                }
            }
        });

        orderList = new ArrayList<>();
        orderRecycle.setLayoutManager(new LinearLayoutManager(this));
        adapter = new orderAdapter(Orders.this, orderList);
        orderRecycle.setAdapter(adapter);


    }
}