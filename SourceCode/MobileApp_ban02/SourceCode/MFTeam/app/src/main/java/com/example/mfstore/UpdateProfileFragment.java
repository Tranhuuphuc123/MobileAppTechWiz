package com.example.mfstore;

import static com.example.mfstore.MainActivity.MY_REQUEST_CODE;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.mfstore.MainActivity;
import com.example.mfstore.R;
import com.example.mfstore.model.Customers;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.time.LocalDate;
import java.util.Map;

public class UpdateProfileFragment extends Fragment {
    private View updateView;
    private ImageView updateUserImg;
    private TextInputEditText updateFullname, updateEmail, updatePhone, updateAddress;
    private Button updateProfileBtn;
    private Uri uri;

    private final String STATUS = "ACTIVE";
    private MainActivity mainActivity;
    private ProgressBar progressBar;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        updateView = inflater.inflate(R.layout.activity_update_profile_fragment,container,false);
        init();
        setUserProfile();
        return updateView;
    }

    private void init() {
        updateUserImg = updateView.findViewById(R.id.updateProfileImg);
        updateFullname = updateView.findViewById(R.id.fullNameUpdateProfileEdit);
        updateEmail = updateView.findViewById(R.id.emailUpdateProfileEdit);
        updatePhone = updateView.findViewById(R.id.phoneUpdateProfileEdit);
        updateAddress = updateView.findViewById(R.id.addressUpdateProfileEdit);
        updateProfileBtn = updateView.findViewById(R.id.updateProfileButton);

        mainActivity = (MainActivity) getActivity();
        progressBar = new ProgressBar(getActivity());
    }

    private void setUserProfile() {
        updateUserImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickRequestPermission();
            }
        });

        updateProfileBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickUpdateProfile();
            }
        });
    }

    private void onClickUpdateProfile() {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String fullname = updateFullname.getText().toString().trim();
        String email = updateEmail.getText().toString().trim();
        String phone = updatePhone.getText().toString().trim();
        String address = updateAddress.getText().toString().trim();
        String key = user.getPhoneNumber();

        if(fullname.isEmpty() && email.isEmpty() && phone.isEmpty() && address.isEmpty() &&
                    !Patterns.EMAIL_ADDRESS.matcher(email).matches() && !Patterns.PHONE.matcher(phone).matches()){
            Toast.makeText(requireContext(),"All fields cannot be left blank.",Toast.LENGTH_LONG).show();
            return;
        }else{
            progressBar.setVisibility(View.VISIBLE);

            UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                    .setDisplayName(fullname)
                    .setPhotoUri(Uri.parse(String.valueOf(uri)))
                    .build();

            user.updateProfile(profileUpdates)
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            progressBar.setVisibility(View.GONE);
                            if (task.isSuccessful()) {
                                Toast.makeText(getActivity(),"Success.",Toast.LENGTH_LONG).show();
                                mainActivity.showUserProfile();
                            }
                        }
                    });

            user.updateEmail("Example@gmail.com");

            updateInfoCustomers(fullname,address,email,phone);

        }
    }

    private void updateInfoCustomers(String fullname,String address,String email,String phone){
        // update realtime db
        if(!phone.isEmpty() && Patterns.PHONE.matcher(phone).matches()){
            DatabaseReference ref2 = FirebaseDatabase.getInstance().getReference("Customers");
            ref2.setValue(phone);
        }
        LocalDate localDate = LocalDate.now();
        DatabaseReference ref1 = FirebaseDatabase.getInstance().getReference("Customers/"+phone);
        Customers customers = new Customers(fullname,address,email,phone,localDate,"",STATUS);
        Map<String,Object> map = customers.toMap();
        ref1.updateChildren(map, new DatabaseReference.CompletionListener() {
            @Override
            public void onComplete(@Nullable DatabaseError error, @NonNull DatabaseReference ref) {
                Toast.makeText(requireContext(),"Profile has been updated successfully.",Toast.LENGTH_LONG).show();
            }
        });
    }
    // Pequest Permission
    // The permission request is because your app needs to access and use image resources from the user's device
    private void onClickRequestPermission() {
        if(Build.VERSION.SDK_INT < Build.VERSION_CODES.M){
            mainActivity.openGallery();
            return;
        }
        // check if the app has been granted permission to access external storage
        if(getActivity().checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED){
            mainActivity.openGallery();
        }else{
            String []permissions = {Manifest.permission.READ_EXTERNAL_STORAGE};
            getActivity().requestPermissions(permissions, MY_REQUEST_CODE);
        }

    }

    public void setBitmapImageView(Bitmap bitmapImageView){
        updateUserImg.setImageBitmap(bitmapImageView);
    }

    public void setUrl(Uri uri){
        this.uri = uri;
    }

}