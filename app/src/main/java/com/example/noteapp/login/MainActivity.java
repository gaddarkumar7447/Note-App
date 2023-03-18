package com.example.noteapp.login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.noteapp.Notes;
import com.example.noteapp.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {
    private EditText mloginemail;
    private EditText mloginpassword;
    private TextView mgotoforgetpassword;
    //private RelativeLayout mlogin, mgotosignup;
    private TextView mlogin, mgotosignup;
    private FirebaseAuth firebaseAuth;
    private ProgressBar mprogressbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Objects.requireNonNull(getSupportActionBar()).hide();
        mloginemail = findViewById(R.id.loginemail);
        mloginpassword = findViewById(R.id.loginpassword);
        mgotoforgetpassword = findViewById(R.id.gotoforgetpassword);
        mlogin = findViewById(R.id.login);
        mgotosignup = findViewById(R.id.gotosingup);

        firebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
        mprogressbar = findViewById(R.id.progressbarinmainactivity);

        if (firebaseUser != null){
            finish();
            startActivity(new Intent(MainActivity.this, Notes.class));
        }
        mgotoforgetpassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, ForgetPassword.class));
            }
        });

        mgotosignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, SignUp.class));
            }
        });

        mlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = mloginemail.getText().toString().trim();
                String password = mloginpassword.getText().toString().trim();

                if (email.isEmpty()){
                    mloginemail.setError("Email is empty");
//                    Toast.makeText(MainActivity.this, "All field are required", Toast.LENGTH_SHORT).show();
                }
                else if (password.isEmpty()){
                    mloginpassword.setError("password is empty");
                }
                else {
                    mprogressbar.setVisibility(View.VISIBLE);
                        firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()){
                                    checkMainVerification();
                                }
                                else {
                                    mloginemail.setError("Email is not exits");
                                    mloginpassword.setError("password is not exits");
                                    mprogressbar.setVisibility(View.INVISIBLE);
                                }
                            }
                        });
                }
            }
        });
    }
    private void checkMainVerification(){
        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
        if (firebaseUser.isEmailVerified() == true){
            Toast.makeText(this, "Logged in", Toast.LENGTH_SHORT).show();
            finish();
            startActivity(new Intent(MainActivity.this, Notes.class));
        }
        else {
            mprogressbar.setVisibility(View.INVISIBLE);
            Toast.makeText(this, "Verify email first", Toast.LENGTH_SHORT).show();
            firebaseAuth.signOut();
        }
    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
        alertDialog.setTitle("Alert");
        alertDialog.setMessage("You want to exit the app");
        alertDialog.setIcon(R.drawable.logo_splash);
        alertDialog.setCancelable(false);
        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finishAffinity();
            }
        });
        alertDialog.setNegativeButton("Cancel", null);
        alertDialog.create();
        alertDialog.show();
    }
}
