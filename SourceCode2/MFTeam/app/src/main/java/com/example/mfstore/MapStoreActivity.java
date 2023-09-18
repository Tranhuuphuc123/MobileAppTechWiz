package com.example.mfstore;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mfstore.R;

public class MapStoreActivity extends AppCompatActivity {

    private Button openMapsBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.map_store);

        openMapsBtn = this.<Button>findViewById(R.id.openMapButton);
        openMapsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Tạo URI với tọa độ cố định (TP.cantho)
                Uri gmmIntentUri = Uri.parse("geo:10.0454,105.7468");

                // Tạo Intent để mở Google Maps với tọa độ
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                mapIntent.setPackage("com.google.android.apps.maps");

                // Kiểm tra xem có ứng dụng Google Maps trên thiết bị hay không
                if (mapIntent.resolveActivity(getPackageManager()) != null) {
                    startActivity(mapIntent);
                }
            }
        });
    }
}
