package com.example.prismmart.Admin_Upload_Product;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.prismmart.Homepage.UI.Homepage;
import com.example.prismmart.Login.sign_in;
import com.example.prismmart.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Upload_Product extends AppCompatActivity {

    Uri imageUri;
    Button saveProduct;
    ImageView loadImage;
    EditText productName, productDescription, productPrice, productID;
    AutoCompleteTextView productCategory;
    AutoCompleteTextView productUnit;
    ArrayAdapter<String> product_cat_adapter;
    ArrayAdapter<String> product_unit_adapter;
    String[] categoryName, Unitname;
    private FirebaseFirestore fStore;
    StorageReference storageReference;
    private static final int image_request = 1;

    @SuppressLint("ResourceType")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_product);


        saveProduct = findViewById(R.id.upload_save_product);
        loadImage = findViewById(R.id.upload_image_view);
        productName = findViewById(R.id.upload_product_name);
        productCategory = findViewById(R.id.upload_product_catagory);
        productUnit = findViewById(R.id.upload_product_unit_type);
        productDescription = findViewById(R.id.upload_product_description);
        productPrice = findViewById(R.id.upload_product_price);
        productID = findViewById(R.id.upload_product_ID);

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


        fStore = FirebaseFirestore.getInstance();
        storageReference = FirebaseStorage.getInstance().getReference("All Product");

        saveProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = productName.getText().toString().trim();
                String category = productCategory.getText().toString().trim();
                String description = productDescription.getText().toString().trim();
                String price = productPrice.getText().toString().trim();
                String unit = productUnit.getText().toString().trim();
                String ID = productID.getText().toString().trim();

                String key = System.currentTimeMillis() + "." + getFileExtention(imageUri);
                StorageReference ref = storageReference.child(key);

                ref.putFile(imageUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        Toast.makeText(getApplicationContext(), "Success", Toast.LENGTH_SHORT).show();


                        Task<Uri> uriTask = taskSnapshot.getStorage().getDownloadUrl();
                        while (!uriTask.isSuccessful())
                            ;   // waits until uriTask.isSuccessful() returns true
                        Uri downloadUrL = uriTask.getResult();

                        DocumentReference documentReference = fStore.collection("All Product").document(category.toString() + key.toString());
                        Map<String, Object> product = new HashMap<>();

                        product.put("productImage", downloadUrL.toString());
                        product.put("productPrice", price);
                        product.put("productCategory", category);
                        product.put("productDescription", description);
                        product.put("productName", name);
                        product.put("productUnit", unit);
                        product.put("productID", ID);
                        documentReference.set(product);

                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getApplicationContext(), "Failed", Toast.LENGTH_SHORT).show();
                    }
                });


            }
        });

        loadImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(intent, image_request);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == image_request && resultCode == RESULT_OK && data != null && data.getData() != null) {
            imageUri = data.getData();
            Picasso.get().load(imageUri).into(loadImage);
        }

    }

    ///  GETTING IMAGE EXTENTION ///
    public String getFileExtention(Uri imageuri) {
        ContentResolver contentResolver = getContentResolver();
        MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();
        return mimeTypeMap.getExtensionFromMimeType(contentResolver.getType(imageuri));
    }

    @Override
    public void onBackPressed() {
        Intent i = new Intent(Upload_Product.this, Homepage.class);
        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(i);
        finish();
    }
}