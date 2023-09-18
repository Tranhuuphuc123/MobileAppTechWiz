package com.example.mfstore;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mfstore.eventListener.ProductAdapter2;
import com.example.mfstore.model.ProductModels;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
/*trang đổ dl các sp theo type loại của mục Product Types*/

public class ProductTypeDetailsActivity extends AppCompatActivity {

    private List<ProductModels> listProducts;
    private ProductAdapter2 productAdapter;
    private RecyclerView recyclerView_product;
    private TextView txt_typeDetails;

    // biến toàn cục cu lin FirebaseDatabase
    FirebaseDatabase database = FirebaseDatabase.getInstance();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_type_details);
        init();

        // xử lý cho recycleView chuyển ảnh theo chiều ngang, chiều dọc
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView_product.setLayoutManager(layoutManager); // xét reclycle layout của banner quảng cáo

        // xử lý cập nhật tên loại sp
//        setSupportActionBar(txt_typeDetails);
//        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);

        // khởi tạo list của các lớp model
        listProducts = new ArrayList<>();
        // khởi tạo lơp adapter(sự kiện)
        productAdapter = new ProductAdapter2(this,listProducts);
        // truyền xử lý sự kiện ch recyleView products..
        recyclerView_product.setAdapter(productAdapter);

        // xử lý sự kiện nút nhấn back
        /*Thêm sự kiện khi nút mũi tên được nhấn*/
        ImageView btn_back = findViewById(R.id.img_typeDetails);
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Khi nút mũi tên được nhấn,  onBackPressed(); có chức năng quay lại giao diện activity_main.xml
                onBackPressed();
            }
        });

        // gọi lớp xử lý sự kiện đổ values vào recycleView
        getListTypeDetailsDatabase();
    }




    /* method ánh xạ id*/
    public void init(){
        recyclerView_product =  findViewById(R.id.recyclerview_typeDetails);
        txt_typeDetails = (TextView) findViewById(R.id.txt_typeDetails);
    }




    /* method xu lý sự kiện lk firebase và đổ dl từ firebase về recycleViews**/
    private void getListTypeDetailsDatabase(){
        String type = getIntent().getStringExtra("type");
        // lựa chọn product của type Indoor
        if(type!=null && type.equalsIgnoreCase("Indoor")){
            DatabaseReference myRealtime = database.getReference("Products");
            myRealtime.orderByChild("type").equalTo("Indoor").addValueEventListener(new ValueEventListener() {
                // nếu hàm thực thi đc nó sẽ text qua onDataChange
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    // vòng lặp for này nó giúp ta lấy từng item trong tổng cái list của  load từ realtime về
                    for(DataSnapshot datasSnapshot : snapshot.getChildren()){
                        ProductModels productModels = datasSnapshot.getValue(ProductModels.class);
                        listProducts.add(productModels);
                    }
                    //sau khi load từng item trong tổng thể của list realtime về ta tiên hành refresh cập nhật thay đổi
                    productAdapter.notifyDataSetChanged();
                }

                // gặp lỗi sẽ thông báo cho người dùng thông qua hàm onCacelled
                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    showToast();
                }
            });
        }

        // lựa chọn product của type Outdoor
        if(type!=null && type.equalsIgnoreCase("Outdoor")){
            DatabaseReference myRealtime = database.getReference("Products");
            myRealtime.orderByChild("type").equalTo("Outdoor").addValueEventListener(new ValueEventListener() {
                // nếu hàm thực thi đc nó sẽ text qua onDataChange
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    // vòng lặp for này nó giúp ta lấy từng item trong tổng cái list của  load từ realtime về
                    for(DataSnapshot datasSnapshot : snapshot.getChildren()){
                        ProductModels productModels = datasSnapshot.getValue(ProductModels.class);
                        listProducts.add(productModels);
                    }
                    //sau khi load từng item trong tổng thể của list realtime về ta tiên hành refresh cập nhật thay đổi
                    productAdapter.notifyDataSetChanged();
                }

                // gặp lỗi sẽ thông báo cho người dùng thông qua hàm onCacelled
                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    showToast();
                }
            });
        }

        // lựa chọn product của type Garden
        if(type!=null && type.equalsIgnoreCase("Garden")){
            DatabaseReference myRealtime = database.getReference("Products");
            myRealtime.orderByChild("type").equalTo("Garden").addValueEventListener(new ValueEventListener() {
                // nếu hàm thực thi đc nó sẽ text qua onDataChange
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    // vòng lặp for này nó giúp ta lấy từng item trong tổng cái list của  load từ realtime về
                    for(DataSnapshot datasSnapshot : snapshot.getChildren()){
                        ProductModels productModels = datasSnapshot.getValue(ProductModels.class);
                        listProducts.add(productModels);
                    }
                    //sau khi load từng item trong tổng thể của list realtime về ta tiên hành refresh cập nhật thay đổi
                    productAdapter.notifyDataSetChanged();
                }

                // gặp lỗi sẽ thông báo cho người dùng thông qua hàm onCacelled
                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    showToast();
                }
            });
        }
    }



    /* hàm showToast gửi thông báo*/
    public void showToast(){
        Toast.makeText(ProductTypeDetailsActivity.this,"get data Failid!", Toast.LENGTH_LONG).show();
    }

}