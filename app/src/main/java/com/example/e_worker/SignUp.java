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

    TextInputLayout regName,regUsername,regEmail,regPhoneNo,regPassword;
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
        callSignIn =  findViewById(R.id.signin_screen);
        regBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){

                rootNode = FirebaseDatabase.getInstance("https://e-worker-fa0e4-default-rtdb.firebaseio.com/");
                reference=rootNode.getReference("users");

                String name = regName.getEditText().getText().toString();
                String username = regUsername.getEditText().getText().toString();
                String email = regEmail.getEditText().getText().toString();
                String phoneNo = regPhoneNo.getEditText().getText().toString();
                String password = regPassword.getEditText().getText().toString();

                UserHelperClass helperClass = new UserHelperClass(name,username,email,phoneNo,password);

                reference.child(username).setValue(helperClass);

            }
        });
        callSignIn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignUp.this,Login.class);
                startActivity(intent);
            }
        });



    }
}