package com.example.mfstore.eventListener;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.mfstore.R;


public class Replace_Fragment {

    /*Method xử lý logic Fragment*/
    public void replaceFragment(AppCompatActivity activity, Fragment fragment){
        FragmentTransaction transaction = activity.getSupportFragmentManager().beginTransaction();

        transaction.replace(R.id.content_frame_id,fragment);
        transaction.commit();  // commit: dùng để áp dụng các thay đổi trên
    }
}
