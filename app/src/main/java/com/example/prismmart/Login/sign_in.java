package com.example.prismmart.Login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.prismmart.Onboard_Slider.HomePage_onBoard;
import com.example.prismmart.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class sign_in extends AppCompatActivity implements View.OnClickListener {

    EditText getEmail, getPassword;
    Button signinButton;
    TextView signUpoption, forgotPassword;
    RadioGroup radioGroup;
    public static String userType;
    public static String userName;
    private FirebaseAuth mAuth;
    private FirebaseFirestore fStore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        getEmail = findViewById(R.id.signinPage_email);
        getPassword = findViewById(R.id.signinPage_password);
        signinButton = findViewById(R.id.signin_button);
        signUpoption = findViewById(R.id.signinPage_signUp);
        radioGroup = findViewById(R.id.signinPage_radiogroup);
        forgotPassword = findViewById(R.id.signinPage_forgot_password);

        signinButton.setOnClickListener(this);
        signUpoption.setOnClickListener(this);
        forgotPassword.setOnClickListener(this);


        mAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();


        signinButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = getEmail.getText().toString();
                String password = getPassword.getText().toString();


                if (email.isEmpty()) {
                    getEmail.setError("Enter a Username");
                    getEmail.requestFocus();
                    return;
                }

                if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                    getEmail.setError("Enter a valid Email");
                    getEmail.requestFocus();
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
                    return;
                }


                int id = radioGroup.getCheckedRadioButtonId();
                if (id == R.id.signinPage_rAdminButton)
                    sign_in.userType = "Admin";
                else if (id == R.id.signinPage_rCustomerButton)
                    sign_in.userType = "User";
                else {
                    Toast.makeText(getApplicationContext(), "Choosing the account type is required", Toast.LENGTH_SHORT).show();
                    return;
                }


                mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            String Uid = mAuth.getCurrentUser().getUid();
                            checkUserValidity(Uid);
                        } else
                            Toast.makeText(sign_in.this, "Wrong credential", Toast.LENGTH_SHORT).show();

                    }
                });
            }


        });

    }

    public void checkUserValidity(String Uid) {

        DocumentReference documentReference = fStore.collection("User").document(Uid);
        documentReference.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                String type = documentSnapshot.getString("AccountType");
                userName = documentSnapshot.getString("Full name");
                if (type.equals(userType)) {
                    Toast.makeText(sign_in.this, "Success", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(sign_in.this, HomePage_onBoard.class));
                    finish();
                } else
                    Toast.makeText(sign_in.this, "Wrong credential", Toast.LENGTH_SHORT).show();

            }
        });

    }

    @Override
    public void onClick(View view) {

        if (view.getId() == R.id.signin_button) {

        } else if (view.getId() == R.id.signinPage_signUp) {
            Intent i = new Intent(sign_in.this, sign_up.class);
            startActivity(i);
        } else if (view.getId() == R.id.signinPage_forgot_password) {
            //Forget Password
            Intent intent = new Intent(this, forget_password.class);
            startActivity(intent);
        }


    }
}