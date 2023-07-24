package com.example.prismmart.Login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.prismmart.Onboard_Slider.HomePage_onBoard;
import com.example.prismmart.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class sign_in extends AppCompatActivity implements View.OnClickListener{

    EditText getEmail, getPassword;
    Button signinButton;
    TextView signUpoption;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);


        getEmail = findViewById(R.id.signinPage_email);
        getPassword = findViewById(R.id.signinPage_password);
        signinButton = findViewById(R.id.signin_button);
        signUpoption = findViewById(R.id.signinPage_signUp);

        signinButton.setOnClickListener(this);
        signUpoption.setOnClickListener(this);
        mAuth = FirebaseAuth.getInstance();


        signinButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = getEmail.getText().toString();
                String password = getPassword.getText().toString();

                getEmail.setText("");
                getPassword.setText("");

                if (email.isEmpty()) {
                    getEmail.setError("Enter a Username");
                    getEmail.requestFocus();
                }

                if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                    getEmail.setError("Enter a valid Email");
                    getEmail.requestFocus();
                }

                if (password.isEmpty()) {
                    getPassword.setError("Fill out the form");
                    getPassword.requestFocus();
                    return;
                }

                if (password.length() < 6) {
                    getPassword.setError("Password cannot be less than 6 word");
                    getPassword.requestFocus();
                }

                mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Intent i=new Intent(sign_in.this, HomePage_onBoard.class);
                                    startActivity(i);
                            Toast.makeText(sign_in.this, "Success", Toast.LENGTH_SHORT).show();
                        }
                        else
                            Toast.makeText(sign_in.this, "Failed", Toast.LENGTH_SHORT).show();
                    }
                });
            }


        });

    }


    @Override
    public void onClick(View view) {

        if (view.getId() == R.id.signin_button) {

        } else if (view.getId() == R.id.signinPage_signUp) {
            Intent i = new Intent(sign_in.this, sign_up.class);
            startActivity(i);
        }

    }
}