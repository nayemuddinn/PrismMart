package com.example.prismmart.Homepage.UI;

import static java.lang.Integer.parseInt;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.prismmart.Model.popularProductModel;
import com.example.prismmart.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.protobuf.StringValue;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;

public class productInfo extends AppCompatActivity implements View.OnClickListener {

    ImageView productImage, addItem, removeItem, clearItem;
    TextView product_Quantity, product_name, product_description, product_price, product_id, product_unit, product_category;
    Button addToCart, buyNow;
    FirebaseFirestore fstore;
    FirebaseAuth mAuth;
    popularProductModel popularProductModel = null;
    String totalPrice;
    int totalQuantity = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_info);


        productImage = findViewById(R.id.productInfo_product_img);
        addItem = findViewById(R.id.productInfo_add_item);
        removeItem = findViewById(R.id.productInfo_remove_item);
        clearItem = findViewById(R.id.productInfo_clear_item);
        product_name = findViewById(R.id.productInfo_product_name);
        product_description = findViewById(R.id.productInfo_product_description);
        product_id = findViewById(R.id.productInfo_product_id);
        product_price = findViewById(R.id.productInfo_product_price);
        product_Quantity = findViewById(R.id.product_info_quantity);
        product_category = findViewById(R.id.productInfo_product_category);
        product_unit = findViewById(R.id.productInfo_product_unit);
        addToCart = findViewById(R.id.productInfo_add_to_cart);
        buyNow = findViewById(R.id.productInfo_buy_now);


        addToCart.setOnClickListener(this);
        addItem.setOnClickListener(this);
        removeItem.setOnClickListener(this);
        clearItem.setOnClickListener(this);

        fstore = FirebaseFirestore.getInstance();
        mAuth = FirebaseAuth.getInstance();

        final Object obj = getIntent().getSerializableExtra("productInfo");


        if (obj instanceof popularProductModel) popularProductModel = (popularProductModel) obj;

        if (popularProductModel != null) {
            Glide.with(getApplicationContext()).load(popularProductModel.getProductImage()).into(productImage);
            product_name.setText(popularProductModel.getProductName());
            product_category.setText("Category : " + popularProductModel.getProductCategory());
            product_id.setText("ID: " + popularProductModel.getProductID());
            product_description.setText(popularProductModel.getProductDescription());
            product_price.setText(popularProductModel.getProductPrice() + "Taka");
            product_unit.setText(popularProductModel.getProductUnit());

        }


    }

    @Override
    public void onClick(View view) {


        if (view.getId() == R.id.productInfo_clear_item) {
            product_Quantity.setText("1");
            totalQuantity = 1;
        }
        if (view.getId() == R.id.productInfo_add_item) {

            if (totalQuantity < 10) {
                totalQuantity++;
                product_Quantity.setText(String.valueOf(totalQuantity));
            } else
                Toast.makeText(this, "Maximum Order Limit is 10" + product_unit.getText().toString(), Toast.LENGTH_SHORT).show();
        }

        if (view.getId() == R.id.productInfo_remove_item) {
            if (totalQuantity > 1) {
                totalQuantity--;
                product_Quantity.setText(String.valueOf(totalQuantity));
            } else
                Toast.makeText(this, "Minimum Oder Limit is 1" + product_unit.getText().toString(), Toast.LENGTH_SHORT).show();
        }
        if (view.getId() == R.id.productInfo_add_to_cart) {

            int t_price = Integer.parseInt(popularProductModel.getProductPrice().toString());

            totalPrice = String.valueOf(t_price * totalQuantity);

            String time, date;

            Calendar calForDate = Calendar.getInstance();


            SimpleDateFormat currentDate = new SimpleDateFormat("dd/MM/yyyy");
            date = currentDate.format(calForDate.getTime());

            SimpleDateFormat currentTime = new SimpleDateFormat("HH:mm:ss a");
            time = currentTime.format(calForDate.getTime());

            final HashMap<String, Object> cartMap = new HashMap<>();

            cartMap.put("productID", product_id.getText().toString());
            cartMap.put("productCategory", product_category.getText().toString());
            cartMap.put("productName", product_name.getText().toString());
            cartMap.put("productPrice", product_price.getText().toString());
            cartMap.put("totalQuantity", product_Quantity.getText().toString());
            cartMap.put("totalPrice", totalPrice);


            fstore.collection("Cart").document(mAuth.getCurrentUser().getUid()).collection("UserCart").add(cartMap).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                @Override
                public void onComplete(@NonNull Task<DocumentReference> task) {
                    Toast.makeText(productInfo.this, "Added to Cart SuccessFully", Toast.LENGTH_SHORT).show();
                }
            });


        }

    }
}