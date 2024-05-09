package com.example.communnect;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Objects;

public class RegisterActivity extends AppCompatActivity {

    EditText mEmailEt, mPasswordEt, mFirstNameEt, mLastNameEt;
    Button mRegisterBtn;
    TextView mloginRedirectText;
    FirebaseDatabase database;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        // Actionbar and Title
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle("Create Account");
        }

        // Initialize Views
        mEmailEt = findViewById(R.id.emailET);
        mPasswordEt = findViewById(R.id.passwordET);
        mFirstNameEt = findViewById(R.id.firstnameET); // Initialize mFirstNameEt
        mLastNameEt = findViewById(R.id.lastnameET);   // Initialize mLastNameEt
        mRegisterBtn = findViewById(R.id.registerBtn);
        mloginRedirectText = findViewById(R.id.loginRedirectText);


        mRegisterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerUser();
            }
        });

        mloginRedirectText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
    }

    private void registerUser() {
        database = FirebaseDatabase.getInstance();
        reference = database.getReference("users");

        String email = mEmailEt.getText().toString().trim();
        String password = mPasswordEt.getText().toString().trim();
        String firstName = mFirstNameEt.getText().toString().trim();
        String lastName = mLastNameEt.getText().toString().trim();

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

        if (firstName.isEmpty()) {
            mFirstNameEt.setError("First name is required");
            mFirstNameEt.requestFocus();
            return;
        }

        if (lastName.isEmpty()) {
            mLastNameEt.setError("Last name is required");
            mLastNameEt.requestFocus();
            return;
        }

        HelperClass helperClass = new HelperClass(firstName, lastName,email, password);
        reference.child(firstName).setValue(helperClass);

        Toast.makeText(RegisterActivity.this, "You have signup successfully!", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
        startActivity(intent);

    }

}
