package com.example.mfstore;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;



import com.example.mfstore.eventListener.ProductAdapter;
import com.example.mfstore.eventListener.ProductAdapter2;
import com.example.mfstore.eventListener.ProductTypeAdapter;
import com.example.mfstore.model.ProductModels;
import com.example.mfstore.model.ProductTypeModels;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


public class ProductActivity extends AppCompatActivity {
    // properties product
    private List<ProductModels> listProducts;
    private ProductAdapter productAdapter;
    private RecyclerView recyclerView_product;


    // properties của productType
    private List<ProductTypeModels> listProductType;
    private ProductTypeAdapter productTypeAdapter;
    private RecyclerView recyclerView_productType;


    // properties product2
    private List<ProductModels> listProducts2;
    private ProductAdapter2 productAdapter2;
    private RecyclerView recyclerView_product2;


    /// phàn khai báo biến cho mục search
     private EditText search_box;
     private  List<ProductModels> list_search;
     private RecyclerView recyclerView_search;
    private ProductAdapter2 search_Adapter;

    // biến toàn cục cu lin FirebaseDatabase
    FirebaseDatabase database = FirebaseDatabase.getInstance();


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);

        // gọi class ánh xạ các id
        init();
        init1();
        init2();
        // mục init cho search
        init3();

        // gọi method xử lý load database từ realtime về Activity
        getListProductFromRealtimeDatabase();

        // gọi method xử lý load tù realtime cho mục recycleview
        getListProductTypeFromRrealtimeDatabase();

        // nhóm product2
        getListProduct2FromRealtimedatabase();

        // thực hiện chức năng search
        getSearchRealtimeDatabase();

        /*Thêm sự kiện khi nút mũi tên được nhấn*/
        ImageView btn_back = findViewById(R.id.img_toolbar_id);
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Khi nút mũi tên được nhấn,  onBackPressed(); có chức năng quay lại giao diện activity_main.xml
                onBackPressed();
                /*
                * // Tạo Intent để chuyển đến giao diện khác (điều chỉnh tên Activity mục tiêu)
                Intent intent = new Intent(ProductActivity.this, AnotherActivity.class);
                startActivity(intent);
                * */
            }
        });
    }


    /* hàm ánh xạ id của product */
    public void init(){
        recyclerView_product =  findViewById(R.id.recyclerview_product1);

        // xử lý cho recycleView chuyển ảnh theo chiều ngang, chiều dọc
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerView_product.setLayoutManager(layoutManager); // xét reclycle layout của banner quảng cáo

        // khởi tạo list của các lớp model
        listProducts = new ArrayList<>();

        // khởi tạo lơp adapter(sự kiện)
        productAdapter = new ProductAdapter(this,listProducts);

        // truyền xử lý sự kiện ch recyleView products..
        recyclerView_product.setAdapter(productAdapter);
    }


    /* thuộc tính khởi tạo init1 củ productType*/
    public void init1(){
        recyclerView_productType =  findViewById(R.id.recyclerview_product_menu);

        // xử lý cho recycleView chuyển ảnh theo chiều ngang, chiều dọc
        LinearLayoutManager layoutManager_productType = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerView_productType.setLayoutManager(layoutManager_productType);  // xét recycle cho producttype menu

        // khởi tạo list của các lớp model
        listProductType = new ArrayList<>();

        // khởi tạo lơp adapter(sự kiện)
        productTypeAdapter = new ProductTypeAdapter(this,listProductType);

        // truyền xử lý sự kiện ch recyleView products..
        recyclerView_productType.setAdapter(productTypeAdapter);
    }

    public void init2(){
        recyclerView_product2 =  findViewById(R.id.recyclerview_product_product2);

        // xử lý cho recycleView chuyển ảnh theo chiều ngang, chiều dọc
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView_product2.setLayoutManager(layoutManager); // xét reclycle layout của banner quảng cáo

        // khởi tạo list của các lớp model
        listProducts2 = new ArrayList<>();

        // khởi tạo lơp adapter(sự kiện)
        productAdapter2= new ProductAdapter2(this,listProducts2);

        // truyền xử lý sự kiện ch recyleView products..
        recyclerView_product2.setAdapter(productAdapter2);
    }

    // method init ánh xạ id cho muục Search
    public void init3(){
        recyclerView_search =  findViewById(R.id.recyclerview_searchList);
        search_box = (EditText)findViewById(R.id.search_box);

        // xử lý cho recycleView chuyển ảnh theo chiều ngang, chiều dọc
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView_search.setLayoutManager(layoutManager);
        // khởi tạo list của các lớp model
        list_search = new ArrayList<>();
        // khởi tạo lơp adapter(sự kiện)
        search_Adapter= new ProductAdapter2(this,list_search);
        //thiết lập kích thước cố định và không thay đổi trong quá trình chạy ứng dụng cho recycleView.
        recyclerView_search.setHasFixedSize(true);

        // truyền xử lý sự kiện ch recyleView products..
        recyclerView_search.setAdapter(search_Adapter);
    }





    /*hàm chức năng lấy dữ liệu từ realtime database đổ về cho listProducts*/
    private void getListProductFromRealtimeDatabase(){
        DatabaseReference myRealtime = database.getReference("Products");
        myRealtime.addValueEventListener(new ValueEventListener() {
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


    /*xây dựng method chức năng lấy dữ liệu realtime từ database list*/
    private void getListProductTypeFromRrealtimeDatabase(){
        DatabaseReference realTime_ProductType = database.getReference("ProductType");
        realTime_ProductType.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                // vòng lặp for giúp ta lấy từng item tp trong realtime
                for(DataSnapshot dataSnapshot:snapshot.getChildren()){
                    ProductTypeModels productTypeModels = dataSnapshot.getValue(ProductTypeModels.class);
                    listProductType.add(productTypeModels);
                }
                // tiến hành refresh trạng thái sau khi load
                productTypeAdapter.notifyDataSetChanged();
            }

            // onCacelled thông báo cho người dùng nếu có lỗi xảy ra
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                showToast();
            }
        });
    }



    /*Nhóm 03*/
    private void getListProduct2FromRealtimedatabase(){
        DatabaseReference realTime_product2 = database.getReference("Products");
        realTime_product2.addValueEventListener(new ValueEventListener() {
            // nếu hàm thực thi đc nó sẽ text qua onDataChange
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                // vòng lặp for này nó giúp ta lấy từng item trong tổng cái list của  load từ realtime về
                for(DataSnapshot datasSnapshot : snapshot.getChildren()){
                    ProductModels productModels = datasSnapshot.getValue(ProductModels.class);
                    listProducts2.add(productModels);
                }

                //sau khi load từng item trong tổng thể của list realtime về ta tiên hành refresh cập nhật thay đổi
                productAdapter2.notifyDataSetChanged();
            }

            // gặp lỗi sẽ thông báo cho người dùng thông qua hàm onCacelled
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                showToast();
            }
        });
    }

    //////////////////////////////////////////////
    /*function xử lý search_box*/
   public void getSearchRealtimeDatabase(){
        search_box.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                String type = editable.toString().trim().toLowerCase();
                if(type.isEmpty()){
                    list_search.clear();
                    search_Adapter.notifyDataSetChanged();
                }else{
                    searchProduct(editable.toString());
                }
            }
            private void searchProduct(String type){
                if(!type.isEmpty()){
                    DatabaseReference myRealtime = database.getReference("Products");
                    myRealtime.orderByChild("productName").startAt(type).endAt(type + "\uf8ff")
                            .addValueEventListener(new ValueEventListener() {
                        // nếu hàm thực thi đc nó sẽ text qua onDataChange
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            list_search.clear();
                            for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                                ProductModels productModels = dataSnapshot.getValue(ProductModels.class);
                                list_search.add(productModels);
                            }
                            search_Adapter.notifyDataSetChanged();
                        }

                        // gặp lỗi sẽ thông báo cho người dùng thông qua hàm onCacelled
                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {
                            showToast();
                        }

                    });
                }
            }
        });
     }



    // hàm showToast gửi thông báo
    public void showToast(){
        Toast.makeText(ProductActivity.this,"get data Failid!", Toast.LENGTH_LONG).show();
    }

}
