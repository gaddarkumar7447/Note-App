package com.example.noteapp.login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.noteapp.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SignUp extends AppCompatActivity {
    private EditText msingupemail, msinguppassword;
    private TextView msignup, mgotologin;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        getSupportActionBar().hide();

        msingupemail = findViewById(R.id.signupemail);
        msinguppassword = findViewById(R.id.signuppassword);
        msignup = findViewById(R.id.signup);
        mgotologin = findViewById(R.id.gotologin);

        firebaseAuth = FirebaseAuth.getInstance();
        mgotologin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SignUp.this, MainActivity.class));
            }
        });

        msignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = msingupemail.getText().toString().trim();
                String password = msinguppassword.getText().toString().trim();
                if (email.isEmpty()){
                    msingupemail.setError("email is empty");
                }
                else if (password.length() < 7){
                    msinguppassword.setError("password should greater than 7 digit");
                }
                else {
                    // Registered email to firebase
                    firebaseAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            // isCompleted to isSuccessfully
                            if (task.isSuccessful()){
                                Toast.makeText(SignUp.this, "Registration successful", Toast.LENGTH_SHORT).show();
                                sendEmailVerification();
                            }
                            else {
                                Toast.makeText(SignUp.this, "Failed to register", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });
    }

    private void sendEmailVerification(){
        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
        if (firebaseUser != null){
            firebaseUser.sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    Toast.makeText(SignUp.this, "Verification Email is send, Verify and login again", Toast.LENGTH_SHORT).show();
                    firebaseAuth.signOut();
                    finish();
                    startActivity(new Intent(SignUp.this, MainActivity.class));
                }
            });
        }
        else {
            Toast.makeText(this, "Failed to send verification email", Toast.LENGTH_SHORT).show();
        }
    }
}