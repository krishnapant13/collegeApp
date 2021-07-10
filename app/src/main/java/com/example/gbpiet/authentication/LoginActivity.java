package com.example.gbpiet.authentication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gbpiet.MainActivity;
import com.example.gbpiet.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {

    private TextView openRegister,openForgetPassword;
    private EditText loginEmail,loginPass;
    private Button loginButton;
    private FirebaseAuth auth;
    private String email ,  pass;
    private ProgressDialog pd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setTitle("Login To GBPIET");
        openRegister = findViewById(R.id.openRegister);
        loginEmail = findViewById(R.id.loginEmail);
        loginPass = findViewById(R.id.loginPass);
        loginButton = findViewById(R.id.loginButton);
        openForgetPassword = findViewById(R.id.openForgetPassword);

        pd = new ProgressDialog(this);
        pd.setTitle("Opening User Registration");
        pd.setMessage("Please Wait...");
        auth = FirebaseAuth.getInstance();


        openRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pd.show();
                openRegister();

            }
        });
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validateData();
                pd.show();

            }
        });
        openForgetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, ForgetPassword.class));
            }
        });
    }

    private void validateData() {

        email = loginEmail.getText().toString();
        pass = loginPass.getText().toString();

        if (email.isEmpty() || pass.isEmpty()){
            Toast.makeText(this, "Please Fill All Fields", Toast.LENGTH_SHORT).show();
        }else{
            loginUser();
        }
    }

    private void loginUser() {
        auth.signInWithEmailAndPassword(email , pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                pd.dismiss();
                if (task.isSuccessful()){
                    openMain();
                }else{
                    Toast.makeText(LoginActivity.this, "Error"+task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                }

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                pd.dismiss();
                Toast.makeText(LoginActivity.this, "Error :"+e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void openMain() {
        startActivity(new Intent(LoginActivity.this , MainActivity.class));
        finish();
    }

    private void openRegister() {
        startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
        finish();
    }
}