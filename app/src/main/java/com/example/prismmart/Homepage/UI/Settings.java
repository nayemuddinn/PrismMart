package com.example.prismmart.Homepage.UI;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.prismmart.Admin.Update_Product;
import com.example.prismmart.Admin.Upload_Product;
import com.example.prismmart.Login.sign_in;
import com.example.prismmart.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

public class Settings extends AppCompatActivity implements View.OnClickListener {

    Button updateBtn, changePassBt;
    EditText userName, userEmail, userPhone, newPassword, confirmNewPassword;

    FirebaseFirestore fstore;
    FirebaseAuth mAuth;
    FirebaseUser mUser;
    String CurrEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        updateBtn = findViewById(R.id.setting_update_information);
        changePassBt = findViewById(R.id.setting_update_pass);
        userName = findViewById(R.id.setting_username);
        userEmail = findViewById(R.id.setting_email);
        userPhone = findViewById(R.id.setting_Phone);
        newPassword = findViewById(R.id.setting_New_pass);
        confirmNewPassword = findViewById(R.id.setting_confirm_new_pass);

        updateBtn.setOnClickListener(this);
        changePassBt.setOnClickListener(this);

        fstore = FirebaseFirestore.getInstance();
        mAuth = FirebaseAuth.getInstance();
        mUser = FirebaseAuth.getInstance().getCurrentUser();

        fstore.collection("User").document(mAuth.getUid().toString()).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {

                    DocumentSnapshot documentSnapshot = task.getResult();

                    userName.setText(documentSnapshot.getString("Full name").toString());
                    userEmail.setText(documentSnapshot.getString("Email").toString());
                    userPhone.setText(documentSnapshot.getString("Phone").toString());
                    CurrEmail = documentSnapshot.getString("Email").toString();


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

        if (newPassword.getText().toString().length() < 6) {
            newPassword.setError("Password cannot be less than 6 word");
            newPassword.requestFocus();
            return;
        }

        if (!newPassword.getText().toString().equals(confirmNewPassword.getText().toString())) {
            confirmNewPassword.setError("Password Don't Match");
            confirmNewPassword.requestFocus();
            return;
        }
        mUser.updatePassword(newPassword.getText().toString())
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(Settings.this, "Password Changed Successfully", Toast.LENGTH_SHORT).show();
                            Toast.makeText(Settings.this, "Session Expired", Toast.LENGTH_SHORT).show();
                            mAuth.signOut();
                            Intent i = new Intent(Settings.this, sign_in.class);
                            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            startActivity(i);
                            finish();

                        }
                    }
                });
    }

    private void updateInformation() {

        String name = userName.getText().toString();
        String email = userEmail.getText().toString();
        String phone = userPhone.getText().toString();


        fstore.collection("User").document(mAuth.getUid().toString()).update("Full name", name, "Email", email, "Phone", phone).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                Toast.makeText(Settings.this, "Updated Successfully", Toast.LENGTH_SHORT).show();
            }
        });

        mUser.updateEmail(email)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {

                            if (!email.equals(CurrEmail)) {
                                Toast.makeText(Settings.this, "Session Expired", Toast.LENGTH_SHORT).show();
                                mAuth.signOut();
                                Intent i = new Intent(Settings.this, sign_in.class);
                                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                startActivity(i);
                                finish();
                            }
                        }
                    }
                });


    }
}