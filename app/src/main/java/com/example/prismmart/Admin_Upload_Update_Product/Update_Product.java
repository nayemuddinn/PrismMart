package com.example.prismmart.Admin_Upload_Update_Product;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.prismmart.Homepage.UI.Homepage;
import com.example.prismmart.Model.popularProductModel;
import com.example.prismmart.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

public class Update_Product extends AppCompatActivity implements View.OnClickListener {

    EditText productID;
    Button update, delete;
    FirebaseFirestore fstore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_product);

        productID = findViewById(R.id.update_product_ID);
        update = findViewById(R.id.update_update_product);
        delete = findViewById(R.id.update_product_delete);


        delete.setOnClickListener(this);
        fstore = FirebaseFirestore.getInstance();


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

    }

    @Override
    public void onBackPressed() {
        Intent i = new Intent(this, Homepage.class);
        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(i);
        finish();
    }
}