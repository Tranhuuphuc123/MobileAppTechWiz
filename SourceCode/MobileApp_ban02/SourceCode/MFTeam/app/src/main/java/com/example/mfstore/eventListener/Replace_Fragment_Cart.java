package com.example.mfstore.eventListener;

import android.content.Context;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mfstore.fragment.CartFragment;


public class Replace_Fragment_Cart  implements View.OnClickListener{
    private Context context;

    public Replace_Fragment_Cart(Context context) {
        this.context = context;
    }

    @Override
    public void onClick(View view) {
        Replace_Fragment rf = new Replace_Fragment();
        rf.replaceFragment((AppCompatActivity) context, new CartFragment());
    }
}
