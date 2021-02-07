package com.example.e_worker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignUp extends AppCompatActivity {

    //Variables
    FirebaseDatabase rootNode;
    DatabaseReference reference;

    TextInputLayout regName, regUsername, regEmail, regPhoneNo, regPassword;
    Button regBtn;
    Button callSignIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        //Hooks to all xml  element in activity sign_up.xml
        regName = findViewById(R.id.reg_name);
        regUsername = findViewById(R.id.reg_username);
        regEmail = findViewById(R.id.reg_email);
        regPhoneNo = findViewById(R.id.reg_phoneNo);
        regPassword = findViewById(R.id.reg_password);
        regBtn = findViewById(R.id.reg_btn);
        callSignIn = findViewById(R.id.signin_screen);


        regBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                rootNode = FirebaseDatabase.getInstance("https://e-worker-fa0e4-default-rtdb.firebaseio.com/");
                reference = rootNode.getReference("users");

                if(!validateName()|!validateUserName()|!validateEmail()|!validatePhoneNo()|!validatePassword()){

                    return;

                }
                String name = regName.getEditText().getText().toString();
                String username = regUsername.getEditText().getText().toString();
                String email = regEmail.getEditText().getText().toString();
                String phoneNo = regPhoneNo.getEditText().getText().toString();
                String password = regPassword.getEditText().getText().toString();

                UserHelperClass helperClass = new UserHelperClass(name, username, email, phoneNo, password);

                reference.child(username).setValue(helperClass);

            }
        });
        callSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignUp.this, Login.class);
                startActivity(intent);
            }
        });


    }


    private Boolean validateName() {
        String val = regName.getEditText().getText().toString();

        if (val.isEmpty()) {
            regName.setError("Field cannot be empty");
            return false;
        } else {
            regName.setError(null);
            return true;
        }
    }

    private Boolean validateUserName() {
        String val = regUsername.getEditText().getText().toString();
        String noWhiteSpace = "\\S+";
        if (val.isEmpty()) {
            regUsername.setError("Field cannot be empty");
            return false;
        }
        else if(val.length()>=15){

            regUsername.setError("Username too long");
            return false;
        }
        else if(!val.matches(noWhiteSpace)){
            regUsername.setError("No Whitespace allowed");
            return false;
        }
        else {
            regUsername.setError(null);
            return true;
        }
    }

    private Boolean validateEmail() {
        String emailPattern = "([a-z]+)([_.a-z0-9]*)([a-z0-9]+)(@)([a-z]+)([.a-z]+)([a-z]+)";

        String val = regEmail.getEditText().getText().toString();


        if (val.isEmpty()) {
            regEmail.setError("Field cannot be empty");
            return false;
        }
        else if(!val.matches(emailPattern)){
            regEmail.setError("Invalid email address");
            return false;
        }
        else {
            regEmail.setError(null);
            return true;
        }
    }

    private Boolean validatePhoneNo() {
        String val = regPhoneNo.getEditText().getText().toString();

        if (val.isEmpty()) {
            regPhoneNo.setError("Field cannot be empty");
            return false;
        } else {
            regPhoneNo.setError(null);
            return true;
        }
    }

    private Boolean validatePassword() {
        String val = regPassword.getEditText().getText().toString();
        String passwordVal = "^"+
                "(?=.*[0-9])"+
                "(?=.*[a-z])"+
                "(?=.*[A-Z])"+
                "(?=.*[a-zA-Z])"+
                "(?=.*[Q#$%^&+=])"+
                "(?=\\s+$)"+
                ".(4,)"+
                "$";
        if (val.isEmpty()) {
            regPassword.setError("Field cannot be empty");
            return false;
        }
        else if(val.matches(passwordVal)){
            regPassword.setError("Password is too weak");
            return false;
        }
        else {
            regPassword.setError(null);
            return true;

        }
    }


}