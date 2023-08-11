package com.example.prismmart.Login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.prismmart.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;


public class forget_password extends AppCompatActivity {
    //Declaration
    Button btnReset;
    Button btnBack;
    EditText edtEmail;
    FirebaseAuth mAuth;

    // String strEmail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);
        //initialization
        btnBack = (Button) findViewById(R.id.back_button);
        btnReset = (Button) findViewById(R.id.reset_button);
        edtEmail = findViewById(R.id.forget_password_Page_email);

        mAuth = FirebaseAuth.getInstance();

        //reset Button listener
        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String strEmail = edtEmail.getText().toString();
                FirebaseAuth.getInstance().sendPasswordResetEmail(strEmail).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(forget_password.this, "send mail", Toast.LENGTH_SHORT).show();

                        } else {
                            Toast.makeText(forget_password.this, "Failed", Toast.LENGTH_SHORT).show();

                        }
                    }
                });


            }
        });
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(forget_password.this, sign_in.class);
                startActivity(intent);
            }
        });
    }
}