package com.example.noteapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class ForgetPassword extends AppCompatActivity {
    private EditText mforgetPassword;
    private TextView mpassportRecoverButton;
    private TextView mgobacktologin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);
        getSupportActionBar().hide();
        mforgetPassword = findViewById(R.id.forgetpassword);
        mpassportRecoverButton = findViewById(R.id.passwordrecoverbutton);
        mgobacktologin = findViewById(R.id.gobacktologin);

        mgobacktologin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ForgetPassword.this, MainActivity.class));
            }
        });

        mpassportRecoverButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mail = mforgetPassword.getText().toString().trim();
                if (mail.isEmpty()){
                    Toast.makeText(ForgetPassword.this, "Enter your mail", Toast.LENGTH_SHORT).show();
                }else {
                    // we have to send password recover email
                }
            }
        });
    }
}