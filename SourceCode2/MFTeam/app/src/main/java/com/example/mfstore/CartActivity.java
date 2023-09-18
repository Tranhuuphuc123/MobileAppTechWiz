//package com.example.mfstore;
//
//import androidx.annotation.NonNull;
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.recyclerview.widget.LinearLayoutManager;
//import androidx.recyclerview.widget.RecyclerView;
//
//import android.os.Bundle;
//import android.view.View;
//import android.widget.ImageView;
//import android.widget.Toast;
//
//import com.example.mfstore.eventListener.MyCartAdapter;
//import com.example.mfstore.model.CartModel;
//import com.example.mfstore.model.ProductTypeModels;
//import com.google.firebase.auth.FirebaseAuth;
//import com.google.firebase.database.DataSnapshot;
//import com.google.firebase.database.DatabaseError;
//import com.google.firebase.database.DatabaseReference;
//import com.google.firebase.database.FirebaseDatabase;
//import com.google.firebase.database.ValueEventListener;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class CartActivity extends AppCompatActivity {
//    RecyclerView recyclerView;
//    MyCartAdapter myCartAdapter;
//    List<CartModel> cartModelList;
//
//    // khai báo cơ sở dữ liệu
//    FirebaseDatabase database = FirebaseDatabase.getInstance();
//    FirebaseAuth auth = FirebaseAuth.getInstance();
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_cart);
//        init();
//
//        // xử lý cho recycleView chuyển ảnh theo chiều ngang, chiều dọc
//        LinearLayoutManager layoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL, false);
//        recyclerView.setLayoutManager(layoutManager);
//
//        // xử lý sự kiện đổ dữ lieu vào recycles View
//        cartModelList = new ArrayList<>();
//        // khởi tạo lơp adapter(sự kiện)
//        myCartAdapter = new MyCartAdapter(this,cartModelList);
//        // truyền xử lý sự kiện ch recyleView products..
//        recyclerView.setAdapter(myCartAdapter);
//
//        // xử lý sự kiện nút nhấn back
//        /*Thêm sự kiện khi nút mũi tên được nhấn*/
//        ImageView btn_back = findViewById(R.id.img_cart);
//        btn_back.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                // Khi nút mũi tên được nhấn,  onBackPressed(); có chức năng quay lại giao diện activity_main.xml
//                onBackPressed();
//            }
//        });
//
//        // gọi hàm xử lý sự kiện đổ dữ liệu từ firebase về recycleview của cardview_cart
//       getListCartFromRealtimeDatabase();
//
//    }
//
//
//    /*method ánh xạ id*/
//    public void init(){
//        recyclerView = (RecyclerView) findViewById(R.id.recyclerview_cart);
//    }
//
//    /*method xử lý đổ dl từ firebase về recycleview của cardview_cart*/
//    public void getListCartFromRealtimeDatabase(){
//        DatabaseReference realTime_Cart = database.getReference("Orders");
//        realTime_Cart.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                // vòng lặp for giúp ta lấy từng item tp trong realtime
//                for(DataSnapshot dataSnapshot:snapshot.getChildren()){
//                   CartModel cartModel = dataSnapshot.getValue(CartModel.class);
//                    cartModelList.add(cartModel);
//                }
//                // tiến hành refresh trạng thái sau khi load
//                myCartAdapter.notifyDataSetChanged();
//            }
//
//            // onCacelled thông báo cho người dùng nếu có lỗi xảy ra
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//                showToast();
//            }
//        });
//    }
//
//    /* hàm showToast gửi thông báo*/
//    public void showToast(){
//        Toast.makeText(CartActivity.this,"get data Failid!", Toast.LENGTH_LONG).show();
//    }
//}