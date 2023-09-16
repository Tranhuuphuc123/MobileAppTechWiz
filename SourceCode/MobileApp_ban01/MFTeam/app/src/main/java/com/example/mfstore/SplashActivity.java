package com.example.mfstore;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class SplashActivity extends AppCompatActivity {

    /** Duration of wait **/
    private final int SPLASH_DISPLAY_LENGTH = 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        /* New Handler to start the Menu-Activity
         * and close this Splash-Screen after some seconds.*/
        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
//                nextActivity();
                /* Create an Intent that will start the Menu-Activity. */
                Intent mainIntent = new Intent(SplashActivity.this, MainActivity.class);
                startActivity(mainIntent);
                finish();
            }
        }, SPLASH_DISPLAY_LENGTH);
    }

//    private void nextActivity() {
//        FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
//        if(firebaseUser == null){
//            // User is not logged in
//            Intent intentUser = new Intent(this, LoginActivity.class);
//            startActivity(intentUser);
//        }else{
//            // User is logged in
//            Intent intentUser = new Intent(this, MainActivity.class);
//            startActivity(intentUser);
//        }
//        finish();
//    }


}