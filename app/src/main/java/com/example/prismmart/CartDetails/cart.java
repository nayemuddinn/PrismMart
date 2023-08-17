package com.example.prismmart.CartDetails;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.widget.TextView;

import com.example.prismmart.Adapter.cartAdapter;
import com.example.prismmart.Adapter.popularProductAdapter;
import com.example.prismmart.Model.cartModel;
import com.example.prismmart.Model.popularProductModel;
import com.example.prismmart.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class cart extends AppCompatActivity {


    RecyclerView cartRecycle;
    cartAdapter adapter;
    List<cartModel> cartList;
    FirebaseAuth mAuth;
    FirebaseFirestore fstore;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        cartRecycle = findViewById(R.id.cart_recycle);

        mAuth = FirebaseAuth.getInstance();
        fstore = FirebaseFirestore.getInstance();

        fstore.collection("Cart").document(mAuth.getCurrentUser().getUid()).collection("UserCart").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {

                    for (QueryDocumentSnapshot document : task.getResult()) {
                        cartModel cart = document.toObject(cartModel.class);
                        cartList.add(cart);
                        adapter.notifyDataSetChanged();
                    }
                } else {

                }
            }
        });

        cartList = new ArrayList<>();
        cartRecycle.setLayoutManager(new LinearLayoutManager(this));
        adapter = new cartAdapter(this, cartList);
        cartRecycle.setAdapter(adapter);


    }
}