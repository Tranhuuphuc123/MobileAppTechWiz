package com.example.mfstore;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.mfstore.fragment.CartFragment;
import com.example.mfstore.model.ProductModels;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;

/*thực thi đổ sản phẩm chi tiết từ csdl một cách chuản xác*/
public class ProductDetailsActivity extends AppCompatActivity {
    private Button btn_productdetails;
    private ImageButton btn_Add, btn_Plus;
    private ImageView img_detailsProduct;
    private TextView txt_detailsName,txt_detailsPrice,txt_detailsDecription, txt_quanlity;
    private ProductModels productModels = null;

    // xây dựng biến ghj nhận giá trị sl chọn
    int totalQuanlity = 1;
    //tạo biến  ghi nhận giá tiền cộng dồn
    int totalPrice = 0;

    // khai báo csdl firebase
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    FirebaseAuth auth = FirebaseAuth.getInstance();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);
        // ánh xạ id init()
        init();

        // nhận value putExtra của Adapter truyền qua
        final Object object = getIntent().getSerializableExtra("details");
        // kiểm tra xem object có thuộc ProductModels hay không -> có thì ép kiểu object thành productModels
        if(object instanceof ProductModels){
            productModels =(ProductModels) object;
        }
        if(productModels != null){
            Glide.with(getApplicationContext()).load(productModels.getProductImg()).into(img_detailsProduct);
            txt_detailsName.setText(productModels.getProductName());
            txt_detailsPrice.setText(String.valueOf(productModels.getProductPrice()));
        }

        // xử lý sự kiện thêm hay bớt quanlity khi mua sản phẩm
        btn_Add.setOnClickListener(view->{
            if(totalQuanlity<10){
                totalQuanlity++;
                // ghi nhận tien cộng dồn dựa trên quanlity mà mình chọn
                totalPrice = productModels.getProductPrice() * totalQuanlity;
                txt_quanlity.setText(String.valueOf(totalQuanlity));
            }
        });
        btn_Plus.setOnClickListener(view->{
            if(totalQuanlity>0){
                totalQuanlity--;
                // ghi nhận tien cộng dồn dựa trên quanlity mà mình chọn
                totalPrice = productModels.getProductPrice() * totalQuanlity;
                txt_quanlity.setText(String.valueOf(totalQuanlity));
            }
        });

        // xử lý nút nhấn Add to card(đổ values vào giỏ hàng)
        btn_productdetails.setOnClickListener(view->{
            String saveCurrentDate, saveCurrentTime;
            Calendar calendar = Calendar.getInstance();

            SimpleDateFormat curentDate = new SimpleDateFormat("MM dd, yyyy");
            saveCurrentDate = curentDate.format(calendar.getTime());

            SimpleDateFormat curentTime = new SimpleDateFormat("HH:mm:ss a");
            saveCurrentTime = curentTime.format(calendar.getTime());

            final HashMap<String, Object> cartMap = new HashMap<>();

            cartMap.put("productName", productModels.getProductName());
            cartMap.put("productPrice",productModels.getProductPrice());
            cartMap.put("currentDate", saveCurrentDate);
            cartMap.put("currentTime", saveCurrentTime);
            cartMap.put("totalQuantity",txt_quanlity.getText().toString());
            cartMap.put("totalPrice", totalPrice);

            DatabaseReference myRealtime = database.getReference("Orders").push(); // Sử dụng push để tạo một key tự động
            myRealtime.setValue(cartMap).addOnCompleteListener(task -> {
                if (task.isSuccessful()) {
                    showToast("Add to Cart");
                    finish();
                } else {
                    showToast("Failled!");
                }
            });
        });
    }


    /*method ánh xạ id*/
    public void init(){
        btn_productdetails = (Button) findViewById(R.id.btn_productDetails);
        img_detailsProduct = (ImageView) findViewById(R.id.cardview_details_img);
        txt_detailsName = (TextView) findViewById(R.id.cardview_details_name);
        txt_detailsPrice = (TextView) findViewById(R.id.cardview_details_price);
        txt_detailsDecription = (TextView) findViewById(R.id.cardview_details_description);
        btn_Add =(ImageButton) findViewById(R.id.img_add_productDetails);
        btn_Plus = (ImageButton) findViewById(R.id.img_plus_productDetails);
        txt_quanlity = (TextView) findViewById(R.id.txt_hienthi_productDeatils);
    }

    /*hàm showToast*/
    public void showToast(String msg){
        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG).show();
    }

}