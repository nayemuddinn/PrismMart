package com.example.prismmart.Admin_Upload_Product;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

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

import java.util.HashMap;
import java.util.Map;

public class Upload_Product extends AppCompatActivity {

    Uri imageUri;
    Button saveProduct, chooseImage;
    ImageView loadImage;
    EditText productName, productCategory, productDescription, productPrice;
    private FirebaseFirestore fStore;
    StorageReference storageReference;
    StorageTask storageTask;
    private static final int image_request = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_product);


        saveProduct = findViewById(R.id.upload_save_product);
        chooseImage = findViewById(R.id.upload_choose_productImage);
        loadImage = findViewById(R.id.upload_image_view);
        productName = findViewById(R.id.upload_product_name);
        productCategory = findViewById(R.id.upload_product_catagory);
        productDescription = findViewById(R.id.upload_product_description);
        productPrice = findViewById(R.id.upload_product_price);


        fStore = FirebaseFirestore.getInstance();
        storageReference = FirebaseStorage.getInstance().getReference("All Product");

        saveProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = productName.getText().toString().trim();
                String category = productCategory.getText().toString().trim();
                String description = productDescription.getText().toString().trim();
                String price = productPrice.getText().toString().trim();

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

        chooseImage.setOnClickListener(new View.OnClickListener() {
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


}