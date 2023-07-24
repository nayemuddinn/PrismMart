package com.example.prismmart.Login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.prismmart.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class sign_up extends AppCompatActivity {
    EditText getUsername, getPassword, getEmail, getPhone;
    Button signUpButton;
    TextView signInoption;
    RadioButton rCustomerButton;
    String accountType;
    private FirebaseFirestore fStore;
    private FirebaseAuth auth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);


        getUsername = findViewById(R.id.signupPage_username);
        getPassword = findViewById(R.id.signupPage_password);
        getEmail = findViewById(R.id.signupPage_Email);
        signUpButton = findViewById(R.id.signup_button);
        signInoption = findViewById(R.id.signUpPage_signIn);
        getPhone = findViewById(R.id.signupPage_phone);
        rCustomerButton = findViewById(R.id.signupPage_customer_rbuttom);

        auth = FirebaseAuth.getInstance();
        fStore= FirebaseFirestore.getInstance();



        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = getUsername.getText().toString();
                String password = getPassword.getText().toString();
                String email = getEmail.getText().toString();
                String phone = getPhone.getText().toString();

                getUsername.setText("");
                getPassword.setText("");
                getEmail.setText("");
                getPhone.setText("");


                 /* if(TextUtils.isEmpty(username))
                      Toast.makeText(sign_up.this, "Enter name", Toast.LENGTH_SHORT).show();*/

                if (username.isEmpty()) {
                    getUsername.setError("Enter a Username");
                    getUsername.requestFocus();
                }

                if (email.isEmpty()) {
                    getEmail.setError("Enter a email");
                    getEmail.requestFocus();
                    return;
                }

                if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                    getEmail.setError("Enter a valid email");
                    getEmail.requestFocus();
                    return;
                }

                if (phone.length() != 11) {
                    getPhone.setError("Enter a valid phone number");
                    getPhone.requestFocus();
                    return;
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

                if (rCustomerButton.isChecked()) {
                    accountType = "User";
                    rCustomerButton.setChecked(false);
                } else {
                    Toast.makeText(getApplicationContext(), "Choosing the customer field is required", Toast.LENGTH_SHORT).show();
                }

                auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {

                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (task.isSuccessful()) {
                            Toast.makeText(getApplicationContext(), "Account created successfully", Toast.LENGTH_SHORT).show();
                            FirebaseUser fUser=auth.getCurrentUser();
                            DocumentReference documentReference=fStore.collection("User").document(fUser.getUid());
                            Map<String,Object> userInfo=new HashMap<>();
                            userInfo.put("Full name",username);
                            userInfo.put("Email",email);
                            userInfo.put("Phone",phone);
                            userInfo.put("AccountType",accountType);

                            documentReference.set(userInfo);


                        } else
                            Toast.makeText(getApplicationContext(), "Account created failed", Toast.LENGTH_SHORT).show();

                    }
                });
            }
        });

        signInoption.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), sign_in.class);
                startActivity(i);
            }
        });

    }
}