package com.example.e_worker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class UserProfile extends AppCompatActivity {


    TextInputLayout fullName,email,phoneNo,password;
    TextView fullNameLabel,usernameLabel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);


        //hooks
        fullName = findViewById(R.id.profile_name);
        email = findViewById(R.id.profile_email);
        phoneNo = findViewById(R.id.profile_phoneNo);
        password = findViewById(R.id.profile_password);
        fullNameLabel = findViewById(R.id.fullNameLabel);
        usernameLabel = findViewById(R.id.usernameLabel);

        //ShowAlldata

        showAllUserData();

    }

    private void showAllUserData() {

        Intent intent = getIntent();
        String user_username = intent.getStringExtra("username");
        String user_name = intent.getStringExtra("name");
        String user_email = intent.getStringExtra("email");
        String user_phoneNo = intent.getStringExtra("phoneNo");
        String user_password = intent.getStringExtra("password");

        fullNameLabel.setText(user_name);
        usernameLabel.setText(user_username);
        email.getEditText().setText(user_email);
        fullName.getEditText().setText(user_username);
        phoneNo.getEditText().setText(user_phoneNo);
        password.getEditText().setText(user_password);
    }
}