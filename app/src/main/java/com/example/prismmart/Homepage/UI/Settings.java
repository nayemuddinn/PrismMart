package com.example.prismmart.Homepage.UI;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.prismmart.Admin.Update_Product;
import com.example.prismmart.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

public class Settings extends AppCompatActivity implements View.OnClickListener {

    Button updateBtn, changePassBt;
    EditText userName, userEmail, userPhone, newPassword, confirmNewPassword, currentPassword;

    FirebaseFirestore fstore;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        updateBtn = findViewById(R.id.setting_update_information);
        changePassBt = findViewById(R.id.setting_update_pass);
        userName = findViewById(R.id.setting_username);
        userEmail = findViewById(R.id.setting_email);
        userPhone = findViewById(R.id.setting_Phone);
        currentPassword = findViewById(R.id.setting_curr_pass);
        newPassword = findViewById(R.id.setting_New_pass);
        confirmNewPassword = findViewById(R.id.setting_confirm_new_pass);

        updateBtn.setOnClickListener(this);
        changePassBt.setOnClickListener(this);

        fstore = FirebaseFirestore.getInstance();
        mAuth = FirebaseAuth.getInstance();

        fstore.collection("User").document(mAuth.getUid().toString()).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {

                   DocumentSnapshot documentSnapshot = task.getResult();

                    userName.setText(documentSnapshot.getString("Full name").toString());
                    userEmail.setText(documentSnapshot.getString("Email").toString());
                    userPhone.setText(documentSnapshot.getString("Phone").toString());




                }
            }
        });


    }

    @Override
    public void onClick(View view) {


        if (view.getId() == R.id.setting_update_information) {
            updateInformation();
        }

        if (view.getId() == R.id.setting_update_pass) {
            updatePassword();
        }
    }

    private void updatePassword() {
    }

    private void updateInformation() {

        String name = userName.getText().toString();
        String email = userEmail.getText().toString();
        String phone = userPhone.getText().toString();


    }
}