package com.example.mfstore.fragment;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.example.mfstore.Alert;
import com.example.mfstore.R;
import com.example.mfstore.eventListener.Event_FragmentCart;
import com.example.mfstore.eventListener.Event_Recycleview_home;
import com.example.mfstore.eventListener.MyCartAdapter;
import com.example.mfstore.model.AllProductModels;
import com.example.mfstore.model.CartModel;
import com.example.mfstore.model.ExclusivesModels;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.makeramen.roundedimageview.RoundedImageView;

import java.util.ArrayList;
import java.util.List;

public class CartFragment extends Fragment {

  RecyclerView recyclerView;
  MyCartAdapter myCartAdapter;
  List<CartModel> cartModelList;
  Event_FragmentCart event_fragmentCart;

  // khai báo cơ sở dữ liệu
  FirebaseDatabase database = FirebaseDatabase.getInstance();
  FirebaseAuth auth = FirebaseAuth.getInstance();
  TextView txt_TotalAmount;
  Button btn_cash;

  /*method kế thừa Fragment*/
    @SuppressLint("MissingInflatedId")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
      View view = inflater.inflate(R.layout.activity_cart, container, false);

      // Ánh xạ các thành phần UI trong layout cartview_cart.xml
      recyclerView = view.findViewById(R.id.recyclerview_cart);

      // Xử lý cho RecyclerView
      LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
      recyclerView.setLayoutManager(layoutManager);

      cartModelList = new ArrayList<>();
      myCartAdapter = new MyCartAdapter(getContext(), cartModelList);
      recyclerView.setAdapter(myCartAdapter);

      // ghi nhận tổng tiền hóa đơn
      txt_TotalAmount = view.findViewById(R.id.txt_cart);
      LocalBroadcastManager.getInstance(getActivity())
              .registerReceiver(rsult,new IntentFilter("MyTotalAmount"));

      // Gọi hàm xử lý đổ dữ liệu từ Firebase về RecyclerView của cartview_cart
      getListCartFromRealtimeDatabase();

      // xử lý chức năng nhấn thanh toán
      btn_cash = view.findViewById(R.id.btn_cash);
      btn_cash.setOnClickListener(v->{
          Intent intent = new Intent(getActivity(), Alert.class);
          startActivity(intent);
      });

      return view;
    }




  /*method xử lý đổ dl từ firebase về recycleview của cardview_cart*/
  public void getListCartFromRealtimeDatabase(){
    DatabaseReference realTime_Cart = database.getReference("Orders");
    realTime_Cart.addValueEventListener(new ValueEventListener() {
      @Override
      public void onDataChange(@NonNull DataSnapshot snapshot) {
        // vòng lặp for giúp ta lấy từng item tp trong realtime
        for(DataSnapshot dataSnapshot:snapshot.getChildren()){
          CartModel cartModel = dataSnapshot.getValue(CartModel.class);
          cartModelList.add(cartModel);
        }
        // tiến hành refresh trạng thái sau khi load
        myCartAdapter.notifyDataSetChanged();
      }

      // onCacelled thông báo cho người dùng nếu có lỗi xảy ra
      @Override
      public void onCancelled(@NonNull DatabaseError error) {
        showToast();
      }
    });
  }

  /* hàm showToast gửi thông báo*/
  public void showToast(){
    Toast.makeText(getContext(), "Get data failed!", Toast.LENGTH_LONG).show();
  }



  /* xử lý ghi nhận tổng tiền hóa đơn*/
    public BroadcastReceiver rsult = new BroadcastReceiver() {
      @Override
      public void onReceive(Context context, Intent intent) {
          int totalBill = intent.getIntExtra("totalAmount",0);
          txt_TotalAmount.setText("TotalBill:  " + totalBill+" $ ");
      }
    };

}