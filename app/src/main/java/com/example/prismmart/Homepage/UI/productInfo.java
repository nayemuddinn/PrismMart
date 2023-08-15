package com.example.prismmart.Homepage.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.prismmart.Model.popularProductModel;
import com.example.prismmart.R;
import com.google.firebase.firestore.FirebaseFirestore;

public class productInfo extends AppCompatActivity {

    ImageView productImage, addItem, removeItem, clearItem;
    TextView product_name, product_description, product_price, product_id, product_unit, product_category;
    Button addToCart, buyNow;
    FirebaseFirestore fstore;
    popularProductModel popularProductModel = null;

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
        product_category = findViewById(R.id.productInfo_product_category);
        product_unit = findViewById(R.id.productInfo_product_unit);
        addToCart = findViewById(R.id.productInfo_add_to_cart);
        buyNow = findViewById(R.id.productInfo_buy_now);

        fstore = FirebaseFirestore.getInstance();
        final Object obj = getIntent().getSerializableExtra("productInfo");


        if (obj instanceof popularProductModel)
            popularProductModel = (popularProductModel) obj;

        if (popularProductModel != null) {
            Glide.with(getApplicationContext()).load(popularProductModel.getProductImage()).into(productImage);
            product_name.setText(popularProductModel.getProductName());
            product_category.setText("Category : " + popularProductModel.getProductCategory());
            product_id.setText("ID: " + popularProductModel.getProductID());
            product_description.setText(popularProductModel.getProductDescription());
            product_price.setText(popularProductModel.getProductPrice() + "Taka/");
            product_unit.setText("per " + popularProductModel.getProductUnit());

        }


    }
}