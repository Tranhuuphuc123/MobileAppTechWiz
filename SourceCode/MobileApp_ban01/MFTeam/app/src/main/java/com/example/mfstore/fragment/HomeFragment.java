package com.example.mfstore.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

//import com.example.mfstore.ProductActivity;
import com.example.mfstore.CarePlantActivity;
import com.example.mfstore.MapStoreActivity;
import com.example.mfstore.NewsActivity;
import com.example.mfstore.ProductActivity;
import com.example.mfstore.R;
import com.example.mfstore.eventListener.Event_Recycleview_home;
import com.example.mfstore.eventListener.Replace_Fragment;
import com.example.mfstore.eventListener.Replace_Fragment_Cart;
import com.example.mfstore.model.ExclusivesModels;
import com.makeramen.roundedimageview.RoundedImageView;

import java.util.ArrayList;
import java.util.List;


public class HomeFragment  extends Fragment {

    private View view;
    private Context context;
    // nhóm chức năng properties của xử lý recycleview_home (mượn tạm ard của Exclusives, và cả model lun)
    RecyclerView recyclerViewHome;
    List<ExclusivesModels> homeModel;
    Event_Recycleview_home event_recycleview_home;
    private RoundedImageView productRoundImg, newsImg, careImg, storesImg;



    /*method kế thừa Fragment*/
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_home, container, false);


        // liên kết id của nút button trên giao diện home+fragment(nút nhấn sẽ gọi đến giao dện activity_product.xml)
        productRoundImg = view.findViewById(R.id.round_img_id1);
        // liên kết id của RecycleView chỗ slider các imgae của trang home_fragment.xml
        recyclerViewHome = view.findViewById(R.id.recyclerview_home_id);


        /* xử lý trực tiếp sự kiện click on Button Product Plants chuyển trang giao diện aticity_products
        * phương thức xử lý kiểu giao diện Activity là một file extend  AppCompatActivity*/
        productRoundImg.setOnClickListener(new View.OnClickListener() {
            // gọi lại method gôTProductActicity
            @Override
            public void onClick(View view) {
                goToProductActivity();
            }
        });

            newsImg.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    goToNewsActivity();
                }
            });

            careImg.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    goToCarePlantActivity();
                }
            });

            storesImg.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    goToStoreActivity();
                }
            });


        /*gọi đến method  lý sụ kiện RecycleView của fragmen_home.xml tạo slider chuyển ảnh*/
        get_Recycleview_home();

        return view;
    }


    private void goToStoreActivity() {
        startActivity(new Intent(requireContext(), MapStoreActivity.class));
    }

    private void goToCarePlantActivity() {
        startActivity(new Intent(requireContext(), CarePlantActivity.class));
    }

    private void goToNewsActivity() {
        startActivity(new Intent(requireContext(), NewsActivity.class));
    }

    private void goToProductActivity() {
        Intent intentProduct;
        intentProduct = new Intent(requireContext(), ProductActivity.class);
        startActivity(intentProduct);
    }



    /*load dl ảnh vào model=> qua recyclevies-> tạo slider trượt imge cho trang fragment_home.xml*/
    private void get_Recycleview_home() {

        recyclerViewHome.setLayoutManager(new LinearLayoutManager(requireContext(), RecyclerView.HORIZONTAL, false));
        recyclerViewHome.setHasFixedSize(true);

        homeModel = new ArrayList<>();
        homeModel.add(new ExclusivesModels(R.drawable.bonsai_bestseller1));
        homeModel.add(new ExclusivesModels(R.drawable.bonsai_bestseller2));
        homeModel.add(new ExclusivesModels(R.drawable.bonsai_bestseller3));
        homeModel.add(new ExclusivesModels(R.drawable.bonsai_bestseller1));
        homeModel.add(new ExclusivesModels(R.drawable.bonsai_bestseller2));
        homeModel.add(new ExclusivesModels(R.drawable.bonsai_bestseller3));
        homeModel.add(new ExclusivesModels(R.drawable.bonsai_bestseller1));

        event_recycleview_home = new Event_Recycleview_home(homeModel);
        recyclerViewHome.setAdapter(event_recycleview_home);
    }

}
