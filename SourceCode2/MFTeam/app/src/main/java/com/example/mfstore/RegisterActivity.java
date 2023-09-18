package com.example.mfstore;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.mfstore.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.time.LocalDate;


public class RegisterActivity extends AppCompatActivity {
    private static final String TAG = RegisterActivity.class.getName();
    private final String STATUS = "ACTIVE";
    private ProgressBar progressBar;
    TextInputEditText fullNameEdit, phoneEdit, emailEdit, addressEdit, passEdit;
    Button registerBtn;
    TextView goToLogin;

    DatabaseReference ref = FirebaseDatabase.getInstance().getReferenceFromUrl("https://plantstore-19b68-default-rtdb.asia-southeast1.firebasedatabase.app");

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_activity);
        init();

        // ====== Event =========================
       handleRegisterBtn();
       handleGoToLogin();
    }

    private void init(){
        fullNameEdit = findViewById(R.id.firstNameEditTxt);
        phoneEdit = findViewById(R.id.phoneRegisterEditTxt);
        emailEdit = findViewById(R.id.emailEditTxt);
        addressEdit = findViewById(R.id.addressEditTxt);
        passEdit = findViewById(R.id.passwordEditTxt);
        registerBtn = findViewById(R.id.newRegisterButton);
        goToLogin = findViewById(R.id.goToLogin);
        progressBar = new ProgressBar(this);
    }

    private void handleRegisterBtn(){
        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String fnameTxt = fullNameEdit.getText().toString().trim();
                String phoneTxt = phoneEdit.getText().toString().trim();
                String emailTxt = emailEdit.getText().toString().trim();
                String addressTxt = addressEdit.getText().toString().trim();
                String passTxt = passEdit.getText().toString().trim();

                if(TextUtils.isEmpty(fnameTxt)){
                    fullNameEdit.setError("Fullname is required");
                    fullNameEdit.requestFocus();
                }else if(TextUtils.isEmpty(phoneTxt)){
                    phoneEdit.setError("Phone is required");
                    phoneEdit.requestFocus();
                }else if(!Patterns.PHONE.matcher(phoneTxt).matches()){
                    phoneEdit.setError("Invalid phone format");
                    phoneEdit.requestFocus();
                }else  if(TextUtils.isEmpty(emailTxt)){
                    emailEdit.setError("Email is required");
                    emailEdit.requestFocus();
                }else if(!Patterns.PHONE.matcher(phoneTxt).matches()){
                    emailEdit.setError("Invalid email format");
                    emailEdit.requestFocus();
                }else if(TextUtils.isEmpty(addressTxt)){
                    addressEdit.setError("Address is required");
                    addressEdit.requestFocus();
                }else if(passTxt.length() < 6){
                    passEdit.setError("Password must be 6 characters.");
                    passEdit.requestFocus();
                }else if(TextUtils.isEmpty(passTxt)){
                    passEdit.setError("Fullname is required");
                    passEdit.requestFocus();
                }else{
                    LocalDate date = LocalDate.now();
                    registerCustomer(fnameTxt,phoneTxt,emailTxt,addressTxt,passTxt,date.toString(),"",STATUS);
                    ref.child("Customers").addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            if(snapshot.hasChild(phoneTxt)){
                                Toast.makeText(RegisterActivity.this,"Phone is already registered.",Toast.LENGTH_LONG).show();
                                startActivity(new Intent(RegisterActivity.this, MainActivity.class));
                            }else{
                                ref.child("Customers").child(phoneTxt).child("CustomerName").setValue(fnameTxt);
                                ref.child("Customers").child(phoneTxt).child("Phone").setValue(phoneTxt);
                                ref.child("Customers").child(phoneTxt).child("Email").setValue(emailTxt);
                                ref.child("Customers").child(phoneTxt).child("Address").setValue(addressTxt);
//                                ref.child("Customer").child(phoneTxt).child("Password").setValue(passTxt);
                                ref.child("Customers").child(phoneTxt).child("DateCreated").setValue(date.toString());
                                ref.child("Customers").child(phoneTxt).child("Image").setValue("");
                                ref.child("Customers").child(phoneTxt).child("Status").setValue(STATUS);
                                Toast.makeText(RegisterActivity.this,"Login registered successfully.",Toast.LENGTH_LONG).show();
//                                finish();
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });

                }
            }
        });
    }

    private void registerCustomer(String fnameTxt, String phoneTxt, String emailTxt, String addressTxt, String passTxt, String toString, String s, String status) {
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        mAuth.createUserWithEmailAndPassword(emailTxt,passTxt).addOnCompleteListener(RegisterActivity.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Toast.makeText(RegisterActivity.this,"User registered successfully.",Toast.LENGTH_LONG).show();
                    FirebaseUser firebaseUser = mAuth.getCurrentUser();

                    // Send Verification Email
                    firebaseUser.sendEmailVerification();

                    // Open user Profile after successful registration
                    startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                    finish();
                }
            }
        });

    }


    private void handleGoToLogin(){
        goToLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToLoginActivity();
            }
        });
    }

    private void goToLoginActivity() {
        Intent intentLogin;
        intentLogin = new Intent(RegisterActivity.this, LoginActivity.class);
        startActivity(intentLogin);
    }


}
