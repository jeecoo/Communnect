package com.example.communnect;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.firebase.Firebase;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthSettings;

public class RegisterActivity extends AppCompatActivity {

    EditText mEmailEt, mPasswordEt;
    Button mRegisterBtn;

    //progressbar to display  while registering user
    ProgressDialog progressDialog;

    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_register);
        //Actionbar and Title
        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setTitle("Create Account");


        //init
        mEmailEt = findViewById(R.id.emailET);
        mPasswordEt = findViewById(R.id.passwordET);
        mRegisterBtn = findViewById(R.id.registerBtn);
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Registering User...");

        mAuth = FirebaseAuth.getInstance();



        //handle registration
        mRegisterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //input email, password
                String email = mEmailEt.getText().toString().trim();
                String password = mPasswordEt.getText().toString().trim();

                //validate
                if (Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                    //set error and focus to email edittext
                    mEmailEt.setError("Invalid Email");
                    mEmailEt.setFocusable(true);
                }
                else if (password.length() < 6){
                    //set error and focus to password edittext
                    mPasswordEt.setError("Password length at least 6 characters");
                    mPasswordEt.setFocusable(true);
                }
                else{
                    registerUser(email, password); //register the user
                }
            }
        });



        //enable back button
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


    }

    private void registerUser(String email, String password) {

    }

    @Override
    public boolean onSupportNavigateUp(){
        onBackPressed(); //go previous activity
        return super.onSupportNavigateUp();
    }
}