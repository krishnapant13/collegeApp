package com.example.gbpiet.authentication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gbpiet.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class ForgetPassword extends AppCompatActivity {

    private TextView loginBack;
    private Button resetButton;
    private EditText ResetEmail;
    private FirebaseAuth auth;
    String email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);

        setTitle("password Reset");
        auth = FirebaseAuth.getInstance();
        ResetEmail = findViewById(R.id.ResetEmail);
        resetButton = findViewById(R.id.resetButton);

        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validateData();
            }
        });



        loginBack = findViewById(R.id.loginBack);

        loginBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openLogin();
            }
        });
    }

    private void validateData() {

        email = ResetEmail.getText().toString();
        if (email.isEmpty()) {
            ResetEmail.setError("Email is Required");
        }else{
            forgetPassword();
        }
    }

    private void forgetPassword() {
        auth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()){
                    Toast.makeText(ForgetPassword.this, "Reset Password is send to Your Email Address", Toast.LENGTH_LONG).show();
                    startActivity(new Intent(ForgetPassword.this,LoginActivity.class));
                    finish();
                }else{
                    Toast.makeText(ForgetPassword.this, "Error"+task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void openLogin() {
        startActivity(new Intent(ForgetPassword.this , LoginActivity.class));
        finish();
    }

    }
