package com.example.mfstore.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.example.mfstore.ProductDetailsActivity;
import com.example.mfstore.R;
import com.example.mfstore.eventListener.Event_FragmentCart;
import com.example.mfstore.eventListener.Event_Recycleview_home;
import com.example.mfstore.model.AllProductModels;
import com.example.mfstore.model.ExclusivesModels;
import com.makeramen.roundedimageview.RoundedImageView;

import java.util.ArrayList;
import java.util.List;

public class CartFragment extends Fragment {

  RecyclerView recyclerViewCart;
  List<AllProductModels> cartModel;
  Event_FragmentCart event_fragmentCart;

  private RoundedImageView productRoundImg;
  private ImageView icon;
  private View view;

  /*method kế thừa Fragment*/
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_cart, container, false);
        recyclerViewCart = view.findViewById(R.id.recycleview_cart_id);

      /*gọi đến method xu lý sự kiện RecycleView của fragmen_cart.xml tạo slider chuyển ảnh*/
      get_Recycleview_cart();

      return view;
    }

  private void get_Recycleview_cart() {

    recyclerViewCart.setLayoutManager(new LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false));
    recyclerViewCart.setHasFixedSize(true);

    cartModel = new ArrayList<>();
    cartModel.add(new AllProductModels(R.drawable.bonsai_bestseller1));
    cartModel.add(new AllProductModels(R.drawable.bonsai_bestseller2));
    cartModel.add(new AllProductModels(R.drawable.bonsai_bestseller3));
    cartModel.add(new AllProductModels(R.drawable.bonsai_bestseller1));

    event_fragmentCart = new Event_FragmentCart(cartModel);
    recyclerViewCart.setAdapter(event_fragmentCart);
  }
}