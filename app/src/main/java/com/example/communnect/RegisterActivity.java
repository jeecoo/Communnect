package com.example.communnect;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

public class RegisterActivity extends AppCompatActivity {

    EditText mEmailEt, mPasswordEt;
    Button mRegisterBtn;

    ProgressDialog progressDialog;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        // Actionbar and Title
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle("Create Account");
        }

        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();

        // Initialize Views
        mEmailEt = findViewById(R.id.emailET);
        mPasswordEt = findViewById(R.id.passwordET);
        mRegisterBtn = findViewById(R.id.registerBtn);

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Registering User...");

        mRegisterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerUser();
            }
        });
    }

    private void registerUser() {
        String email = mEmailEt.getText().toString().trim();
        String password = mPasswordEt.getText().toString().trim();

        if (email.isEmpty()) {
            mEmailEt.setError("Email is required");
            mEmailEt.requestFocus();
            return;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            mEmailEt.setError("Invalid email format");
            mEmailEt.requestFocus();
            return;
        }

        if (password.isEmpty()) {
            mPasswordEt.setError("Password is required");
            mPasswordEt.requestFocus();
            return;
        }

        if (password.length() < 6) {
            mPasswordEt.setError("Password length should be at least 6 characters");
            mPasswordEt.requestFocus();
            return;
        }

        progressDialog.show();

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(task -> {
                    progressDialog.dismiss();
                    if (task.isSuccessful()) {
                        Toast.makeText(RegisterActivity.this, "User registered successfully", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(RegisterActivity.this, MainActivity.class));
                        finish();
                    } else {
                        Toast.makeText(RegisterActivity.this, "Registration failed. " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }
}
