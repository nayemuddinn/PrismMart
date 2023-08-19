package com.example.prismmart.CartDetails;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.prismmart.Adapter.cartAdapter;
import com.example.prismmart.Map.googleMap;
import com.example.prismmart.Model.cartModel;

import com.example.prismmart.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class cart extends AppCompatActivity implements View.OnClickListener {


    RecyclerView cartRecycle;
    cartAdapter adapter;
    List<cartModel> cartList;
    FirebaseAuth mAuth;
    FirebaseFirestore fstore;
    TextView showDate, showTime, orderNumber;
    EditText Address;
    Button map, pay;
    int totalBill = 0;
    String time, date, orderNo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        cartRecycle = findViewById(R.id.cart_recycle);
        showDate = findViewById(R.id.cart_current_date);
        showTime = findViewById(R.id.cart_current_time);
        Address = findViewById(R.id.cart_address);
        map = findViewById(R.id.cart_get_Address_map);
        pay = findViewById(R.id.cart_pay);
        orderNumber = findViewById(R.id.cart_orderNo);


        pay.setOnClickListener(this);
        map.setOnClickListener(this);


        String address = getIntent().getStringExtra("Address");


        if (address.contains("null"))
            Address.setHint("Enter Address");
        else
            Address.setText(address);

        mAuth = FirebaseAuth.getInstance();
        fstore = FirebaseFirestore.getInstance();

        fstore.collection("Cart").document(mAuth.getCurrentUser().getUid()).collection("UserCart").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {

                    for (QueryDocumentSnapshot document : task.getResult()) {
                        cartModel cart = document.toObject(cartModel.class);
                        int t_price = Integer.parseInt(cart.getTotalPrice().toString());
                        totalBill += t_price;
                        cartList.add(cart);
                        adapter.notifyDataSetChanged();
                    }
                    pay.setText("PAY " + String.valueOf(totalBill) + " Taka");
                } else {

                }
            }
        });

        cartList = new ArrayList<>();
        cartRecycle.setLayoutManager(new LinearLayoutManager(this));
        adapter = new cartAdapter(this, cartList);
        cartRecycle.setAdapter(adapter);


        pay.setText(String.valueOf(totalBill));
        setDateTime();


            UUID uuid = UUID.randomUUID();
            orderNo = uuid.toString();
            orderNumber.setText(orderNo);



    }

    private void setDateTime() {


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

        if (view.getId() == R.id.cart_pay) {
            if (totalBill == 0) {
                Toast.makeText(this, "You have nothing to pay", Toast.LENGTH_SHORT).show();
                return;
            }
            generateOrder(totalBill);
            adapter.clearCart();
            Address.setText("");
            Address.setHint("Enter Address here");
            pay.setText("PAY");
            totalBill=0;
            orderNumber.setText("");
        }

        if (view.getId() == R.id.cart_get_Address_map) {
            Intent i = new Intent(cart.this, googleMap.class);
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(i);
            finish();
        }

    }

    private void generateOrder(int totalBill) {


        final HashMap<String, Object> orderDetails = new HashMap<>();

        orderDetails.put("orderDate", date);
        orderDetails.put("orderTime", time);
        orderDetails.put("orderNo", orderNo);
        orderDetails.put("totalBill", String.valueOf(totalBill));
        orderDetails.put("Address", Address.getText().toString());

        //Customer panel
        fstore.collection("Order").document(mAuth.getCurrentUser().getUid()).collection("UserOrder").add(orderDetails).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
            @Override
            public void onComplete(@androidx.annotation.NonNull Task<DocumentReference> task) {
                Toast.makeText(cart.this, "Payment Successful", Toast.LENGTH_SHORT).show();
            }
        });


        //All Sales for Admin Panel
        String key = "Prism-" + System.currentTimeMillis();
        DocumentReference saleReference = fstore.collection("SalesAdmin").document(orderNo + key.toString());
        saleReference.set(orderDetails);

        //All Order for Customer
        DocumentReference customerReference = fstore.collection("CustomerOrder").document(mAuth.getCurrentUser().getUid().toString()).collection("Order").document(orderNo);
        customerReference.set(orderDetails);


        //Clear Cart
        fstore.collection("Cart").document(mAuth.getCurrentUser().getUid().toString()).collection("UserCart").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@androidx.annotation.NonNull Task<QuerySnapshot> task) {


                for (QueryDocumentSnapshot snapshot : task.getResult()) {

                    fstore.collection("Cart").document(mAuth.getCurrentUser().getUid().toString()).collection("UserCart").document(snapshot.getId()).delete();
                }


            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@androidx.annotation.NonNull Exception e) {

            }
        });


    }
}