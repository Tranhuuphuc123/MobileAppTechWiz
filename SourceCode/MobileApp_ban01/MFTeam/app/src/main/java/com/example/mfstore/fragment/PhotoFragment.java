package com.example.mfstore.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.mfstore.R;
import com.example.mfstore.model.Photo;

import java.util.zip.Inflater;


/* file class fragment photo có chức năng ánh xạ đến các layout photo_fragment
để ánh xạ các các image hàng trăm image cũng đc vào trong mục imageview của layout photo_fragment*/
public class PhotoFragment extends Fragment {

    private View view_photo;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view_photo = inflater.inflate(R.layout.fragment_photo, container, false);

        // nhận dữ liệu bundle từ event listener của Replace_Fragment_Photo
        Bundle bundle = getArguments();

        // photo lúc này sẽ nhân đc dl từ layout fragment_photo.xml đã đc load (đc xử lý sự kiện với  Replace_Fragment_photo)
        Photo photo = (Photo) bundle.get("object_photo");

        ImageView imgview = view_photo.findViewById(R.id.img_photo_id);
        imgview.setImageResource(photo.getResourceID());

        return view_photo;
    }
}