package com.example.prismmart.CartDetails;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

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

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class cart extends AppCompatActivity implements View.OnClickListener{


    RecyclerView cartRecycle;
    cartAdapter adapter;
    List<cartModel> cartList;
    FirebaseAuth mAuth;
    FirebaseFirestore fstore;
    TextView showDate, showTime;
    EditText Address;
    Button map,pay;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        cartRecycle = findViewById(R.id.cart_recycle);
        showDate = findViewById(R.id.cart_current_date);
        showTime = findViewById(R.id.cart_current_time);
        Address=findViewById(R.id.cart_address);
        map=findViewById(R.id.cart_get_Address_map);
        pay=findViewById(R.id.cart_pay);


        pay.setOnClickListener(this);
        map.setOnClickListener(this);

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


        setDateTime();


    }

    private void setDateTime() {
        String time, date;

        Calendar calForDate = Calendar.getInstance();


        SimpleDateFormat currentDate = new SimpleDateFormat("dd/MM/yyyy");
        date = currentDate.format(calForDate.getTime());

        SimpleDateFormat currentTime = new SimpleDateFormat("HH:mm:ss a");
        time = currentTime.format(calForDate.getTime());

        showDate.setText(date);
        showTime.setText(time);


    }

    @Override
    public void onClick(View view) {

        if(view.getId()==R.id.cart_pay)
        {
            Toast.makeText(this, "Paid Successfully", Toast.LENGTH_SHORT).show();
            pay.setText("0");
        }

    }
}