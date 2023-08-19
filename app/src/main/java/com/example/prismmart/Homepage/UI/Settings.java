package com.example.prismmart.Homepage.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.prismmart.R;

public class Settings extends AppCompatActivity implements View.OnClickListener{

    Button updateBtn,changePassBt;
    EditText userName,userEmail,userPhone,newPassword,confirmNewPassword,currentPassword;;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        updateBtn=findViewById(R.id.setting_update_information);
        changePassBt=findViewById(R.id.setting_update_pass);
        userName=findViewById(R.id.setting_username);
        userEmail=findViewById(R.id.setting_email);
        userPhone=findViewById(R.id.setting_Phone);
        currentPassword=findViewById(R.id.setting_curr_pass);
        newPassword=findViewById(R.id.setting_New_pass);
        confirmNewPassword=findViewById(R.id.setting_confirm_new_pass);

        updateBtn.setOnClickListener(this);
        changePassBt.setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {

    }
}