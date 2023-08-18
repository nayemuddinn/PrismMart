package com.example.prismmart.Admin_Upload_Update_Product;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.prismmart.Homepage.UI.Homepage;
import com.example.prismmart.Model.cartModel;
import com.example.prismmart.Model.popularProductModel;
import com.example.prismmart.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

public class Update_Product extends AppCompatActivity implements View.OnClickListener {

    EditText productID, productName, productDescription, productPrice;
    ImageView productImage;
    Button update, delete, ViewProduct;
    AutoCompleteTextView productCategory;
    AutoCompleteTextView productUnit;
    ArrayAdapter<String> product_cat_adapter;
    ArrayAdapter<String> product_unit_adapter;
    String[] categoryName, Unitname;
    FirebaseFirestore fstore;
    String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_product);

        productID = findViewById(R.id.update_product_ID);
        update = findViewById(R.id.update_update_product);
        delete = findViewById(R.id.update_product_delete);
        ViewProduct = findViewById(R.id.update_product_View);
        productDescription = findViewById(R.id.update_product_description);
        productPrice = findViewById(R.id.update_product_price);
        productCategory = findViewById(R.id.update_product_catagory);
        productUnit = findViewById(R.id.update_product_unit_type);
        productImage = findViewById(R.id.update_image_view);
        productName = findViewById(R.id.update_product_name);


        delete.setOnClickListener(this);
        ViewProduct.setOnClickListener(this);
        fstore = FirebaseFirestore.getInstance();


        //category List autoCompleteTextview
        categoryName = getResources().getStringArray(R.array.category_name);
        product_cat_adapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, categoryName);
        productCategory.setThreshold(1);
        productCategory.setAdapter(product_cat_adapter);

        //Product Unit autoCompleteTextView
        Unitname = getResources().getStringArray(R.array.product_Unit_name);
        product_unit_adapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, Unitname);
        productUnit.setThreshold(1);
        productUnit.setAdapter(product_unit_adapter);


    }

    @Override
    public void onClick(View view) {

        if (view.getId() == R.id.update_product_delete) {
            fstore.collection("All Product").whereEqualTo("productID", productID.getText().toString()).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                    if (task.isSuccessful() && !task.getResult().isEmpty()) {

                        DocumentSnapshot documentSnapshot = task.getResult().getDocuments().get(0);
                        String documentID = documentSnapshot.getId();

                        fstore.collection("All Product").document(documentID).delete().addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {
                                Toast.makeText(Update_Product.this, "Deleted Successfully", Toast.LENGTH_SHORT).show();
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@androidx.annotation.NonNull Exception e) {
                                Toast.makeText(Update_Product.this, "Failed to Delete Product", Toast.LENGTH_SHORT).show();
                            }
                        });

                    } else {
                        Toast.makeText(Update_Product.this, "Product Not Found", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }

        if (view.getId() == R.id.update_product_View) {
            fstore.collection("All Product").whereEqualTo("productID", productID.getText().toString()).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                    if (task.isSuccessful() && !task.getResult().isEmpty()) {

                        DocumentSnapshot documentSnapshot = task.getResult().getDocuments().get(0);

                        productName.setText(documentSnapshot.getString("productName").toString());
                        productCategory.setText(documentSnapshot.getString("productCategory").toString());
                        productUnit.setText(documentSnapshot.getString("productUnit").toString());
                        productDescription.setText(documentSnapshot.getString("productDescription").toString());
                        productPrice.setText(documentSnapshot.getString("productPrice").toString());
                        Glide.with(Update_Product.this).load(documentSnapshot.getString("productImage").toString()).into(productImage);


                    } else {
                        Toast.makeText(Update_Product.this, "Product Not Found", Toast.LENGTH_SHORT).show();
                    }
                }
            });


        }
    }

    @Override
    public void onBackPressed() {
        Intent i = new Intent(this, Homepage.class);
        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(i);
        finish();
    }
}
