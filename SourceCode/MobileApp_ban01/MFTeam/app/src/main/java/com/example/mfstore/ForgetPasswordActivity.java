package com.example.mfstore;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.mfstore.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ForgetPasswordActivity extends AppCompatActivity {
    FirebaseAuth firebaseAuth;
    TextInputEditText emailFg;
    Button sendFg, backFg;
    String emailTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);

        emailFg = findViewById(R.id.emailFgEdit);
        sendFg = findViewById(R.id.sendFgButton);
        backFg = findViewById(R.id.backFgButton);
        firebaseAuth = FirebaseAuth.getInstance();

        sendFg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                emailTxt = emailFg.getText().toString().trim();

                if(TextUtils.isEmpty(emailTxt)){
                    emailFg.setError("Email is required");
                    emailFg.requestFocus();
                    if(!Patterns.EMAIL_ADDRESS.matcher(emailTxt).matches()){
                        emailFg.setError("Invalid email format.");
                        emailFg.requestFocus();
                    }
                }else{
                    handleForgetPassword();
                }

            }
        });

        backFg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }

    private void handleForgetPassword() {
        sendFg.setVisibility(View.INVISIBLE);
        firebaseAuth.sendPasswordResetEmail(emailTxt).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Customers/Password");
//                ref.setValue()
                Toast.makeText(ForgetPasswordActivity.this,"Reset Password link has been sent to your registered Email",Toast.LENGTH_LONG).show();
                startActivity(new Intent(ForgetPasswordActivity.this, LoginActivity.class));
                finish();
            }
        })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(ForgetPasswordActivity.this,"Error: "+e.getMessage(),Toast.LENGTH_LONG).show();
                        sendFg.setVisibility(View.VISIBLE);
                    }
                });


    }
}