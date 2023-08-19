package com.example.prismmart.Admin_Upload_Update_Product;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.prismmart.Homepage.UI.Homepage;
import com.example.prismmart.R;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;


public class Update_Product extends AppCompatActivity implements View.OnClickListener {

    Uri imageUri;
    EditText productID, productName, productDescription, productPrice;
    ImageView productImage;
    Button update, delete, ViewProduct;
    AutoCompleteTextView productCategory;
    AutoCompleteTextView productUnit;
    ArrayAdapter<String> product_cat_adapter;
    ArrayAdapter<String> product_unit_adapter;
    String[] categoryName, Unitname;
    FirebaseFirestore fstore;
    StorageReference storageReference;
    StorageTask storageTask;

    String name;
    String category;
    String description;
    String price;
    String unit, imageURL;
    private static final int image_req = 2;
    boolean imageSelect = false;


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
        update.setOnClickListener(this);
        productImage.setOnClickListener(this);

        fstore = FirebaseFirestore.getInstance();
        storageReference = FirebaseStorage.getInstance().getReference("All Product");


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

        if (view.getId() == R.id.update_image_view) {
            imageSelect = true;
            Intent intent = new Intent();
            intent.setType("image/*");
            intent.setAction(Intent.ACTION_GET_CONTENT);
            startActivityForResult(intent, image_req);
        }


        if (view.getId() == R.id.update_product_delete) {

            if (productID.getText().toString().equals(null) || productID.getText().toString().isEmpty()) {
                productID.setError("Enter a Product ID");
                productID.requestFocus();
                return;
            }

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

            if (productID.getText().toString().equals(null) || productID.getText().toString().isEmpty()) {
                productID.setError("Enter a Product ID");
                productID.requestFocus();
                return;
            }


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
                        imageURL = documentSnapshot.getString("productImage").toString();


                    } else {
                        Toast.makeText(Update_Product.this, "Product Not Found", Toast.LENGTH_SHORT).show();
                    }
                }
            });

        }


        if (view.getId() == R.id.update_update_product) {

            if (productID.getText().toString().equals(null) || productID.getText().toString().isEmpty()) {
                productID.setError("Enter a Product ID");
                productID.requestFocus();
                return;
            }

            if (productName.getText().toString().equals(null) || productName.getText().toString().isEmpty()) {
                productName.setError("Enter a Product Name");
                productName.requestFocus();
                return;
            }
            if (productCategory.getText().toString().equals(null) || productCategory.getText().toString().isEmpty()) {
                productCategory.setError("Enter a Product Category");
                productCategory.requestFocus();
                return;
            }
            if (productUnit.getText().toString().equals(null) || productUnit.getText().toString().isEmpty()) {
                productUnit.setError("Enter a Product Unit");
                productUnit.requestFocus();
                return;
            }
            if (productPrice.getText().toString().equals(null) || productPrice.getText().toString().isEmpty()) {
                productPrice.setError("Enter a Product Price");
                productPrice.requestFocus();
                return;
            }


            name = productName.getText().toString().trim();
            category = productCategory.getText().toString().trim();
            description = productDescription.getText().toString().trim();
            price = productPrice.getText().toString().trim();
            unit = productUnit.getText().toString().trim();


            if (imageSelect == true) {
                String key = System.currentTimeMillis() + "." + getFileExtention(imageUri);
                StorageReference ref = storageReference.child(key);

                storageTask = ref.putFile(imageUri);

                storageTask.continueWithTask(new Continuation() {
                    @Override
                    public Object then(@NonNull Task task) throws Exception {
                        if (!task.isComplete()) {
                            throw task.getException();
                        }
                        return ref.getDownloadUrl();
                    }
                }).addOnCompleteListener(new OnCompleteListener<Uri>() {
                    @Override
                    public void onComplete(@NonNull Task<Uri> task) {
                        if (task.isSuccessful()) {
                            Uri downloadUri = task.getResult();
                            imageURL = downloadUri.toString();
                            Toast.makeText(Update_Product.this, "Uploaded Image", Toast.LENGTH_SHORT).show();
                            fstore.collection("All Product").whereEqualTo("productID", productID.getText().toString()).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                                @Override
                                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                    if (task.isSuccessful() && !task.getResult().isEmpty()) {

                                        DocumentSnapshot documentSnapshot = task.getResult().getDocuments().get(0);
                                        String documentID = documentSnapshot.getId();

                                        fstore.collection("All Product").document(documentID).update("productName", name, "productImage", imageURL, "productPrice", price, "productUnit", unit, "productDescription", description, "productCategory", category).addOnSuccessListener(new OnSuccessListener<Void>() {
                                            @Override
                                            public void onSuccess(Void unused) {
                                                Toast.makeText(Update_Product.this, "Updated Successfully", Toast.LENGTH_SHORT).show();
                                            }
                                        }).addOnFailureListener(new OnFailureListener() {
                                            @Override
                                            public void onFailure(@androidx.annotation.NonNull Exception e) {
                                                Toast.makeText(Update_Product.this, "Failed to Update Product", Toast.LENGTH_SHORT).show();
                                            }
                                        });

                                    } else {
                                        Toast.makeText(Update_Product.this, "Product Not Found", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                        }

                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(Update_Product.this, "Failed Storage", Toast.LENGTH_SHORT).show();
                    }
                });
            } else {


                //Firestore work
                fstore.collection("All Product").whereEqualTo("productID", productID.getText().toString()).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful() && !task.getResult().isEmpty()) {

                            DocumentSnapshot documentSnapshot = task.getResult().getDocuments().get(0);
                            String documentID = documentSnapshot.getId();

                            fstore.collection("All Product").document(documentID).update("productName", name, "productImage", imageURL, "productPrice", price, "productUnit", unit, "productDescription", description, "productCategory", category).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void unused) {
                                    Toast.makeText(Update_Product.this, "Updated Successfully", Toast.LENGTH_SHORT).show();
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@androidx.annotation.NonNull Exception e) {
                                    Toast.makeText(Update_Product.this, "Failed to Update Product", Toast.LENGTH_SHORT).show();
                                }
                            });

                        } else {
                            Toast.makeText(Update_Product.this, "Product Not Found", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        }


    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == image_req && resultCode == RESULT_OK && data != null && data.getData() != null) {
            imageUri = data.getData();
            Picasso.get().load(imageUri).into(productImage);
        }

    }


    public String getFileExtention(Uri imageuri) {
        ContentResolver contentResolver = getContentResolver();
        MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();
        return mimeTypeMap.getExtensionFromMimeType(contentResolver.getType(imageuri));
    }


    @Override
    public void onBackPressed() {
        Intent i = new Intent(this, Homepage.class);
        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(i);
        finish();
    }
}
