package com.example.mfstore;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.LinearLayoutCompat;

import com.example.mfstore.MainActivity;
import com.example.mfstore.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class LoginActivity extends AppCompatActivity {

    Button loginBtn;
    TextView resetPass;
    LinearLayoutCompat goToRegister;
    TextInputEditText emailRegister, passRegister;
    DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Customers");


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activty);

        init();
        handleLoginBtn();
        handleGoToRegister();
        handleResetPass();

    }
    private void init(){
        loginBtn = findViewById(R.id.loginButton);
        resetPass = findViewById(R.id.resetPassword);
        goToRegister = findViewById(R.id.goToRegisterLinear);
        emailRegister = findViewById(R.id.emailRegisterEdit);
        passRegister = findViewById(R.id.passwordRegisterEdit);
    }

    private void handleGoToRegister(){
        // Go to Rigister Function
        goToRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
            }
        });
    }

    private void handleLoginBtn(){
        // Go to Main Activity
        loginBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                String email = emailRegister.getText().toString().trim();
                String password = passRegister.getText().toString().trim();

                if(TextUtils.isEmpty(email)){
                    emailRegister.setError("Phone is not empty.");
                    emailRegister.requestFocus();
                }else if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                    emailRegister.setError("Invalid phone format");
                    emailRegister.requestFocus();
                }
                if(TextUtils.isEmpty(password)){
                    passRegister.setError("Password is not empty.");
                    passRegister.requestFocus();
                }else if(password.length() < 6){
                    passRegister.setError("Password must be 6 characters.");
                }else{
                    FirebaseAuth mAuth = FirebaseAuth.getInstance();
                    mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(LoginActivity.this,new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                startActivity(new Intent(LoginActivity.this, MainActivity.class));
                                finishAffinity();
                            }else{
                                Toast.makeText(LoginActivity.this,"Login failed", Toast.LENGTH_LONG).show();
                            }
                        }
                    });

//                    ref.child("Customer").addValueEventListener(new ValueEventListener() {
//                        @Override
//                        public void onDataChange(@NonNull DataSnapshot snapshot) {
//                            if(snapshot.hasChild(phone)){
//                               final String getPw = snapshot.child(phone).child("Password").getValue(String.class);
//                                if(getPw.equals(password)){
//                                    Toast.makeText(LoginActivity.this,"Successfully logged in", Toast.LENGTH_LONG).show();
//                                    Intent intentHome = new Intent(LoginActivity.this, MainActivity.class);
//                                    startActivity(intentHome);
//                                    finish();
//                                }else{
//                                    Toast.makeText(LoginActivity.this,"Wrong Password", Toast.LENGTH_LONG).show();
//                                }
//                            }else{
//                                Toast.makeText(LoginActivity.this,"Wrong Password", Toast.LENGTH_LONG).show();
//                            }
//                        }
//
//                        @Override
//                        public void onCancelled(@NonNull DatabaseError error) {
//
//                        }
//                    });

                }
            }
        });
    }

    private void handleResetPass(){
        // Reset Password Function
        resetPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this, ForgetPasswordActivity.class));
            }
        });
    }
}
