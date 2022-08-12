package com.example.noteapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;

public class SignUp extends AppCompatActivity {
    private EditText msingupemail;
    private EditText msinguppassword;
    private RelativeLayout msignup;
    private TextView mgotologin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        getSupportActionBar().hide();
        msingupemail = findViewById(R.id.signupemail);
        msinguppassword = findViewById(R.id.signuppassword);
        msignup = findViewById(R.id.signup);
        mgotologin = findViewById(R.id.gotologin);

        mgotologin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SignUp.this, MainActivity.class));
            }
        });

        msignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String emainl = msingupemail.getText().toString().trim();
                String password = msinguppassword.getText().toString().trim();
                if (emainl.isEmpty() || password.isEmpty()){
                    Toast.makeText(SignUp.this, "Both fields are required", Toast.LENGTH_SHORT).show();
                }else if (password.length() < 7){
                    Toast.makeText(SignUp.this, "Password should greater than 7 digit", Toast.LENGTH_SHORT).show();
                }
                else {
                    // Registered email to firebase
                }
            }
        });
    }
}