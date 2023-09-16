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
            txt_detailsPrice.setText(productModels.getProductPrice());

            // ghi nhận tien cộng dồn dựa trên quanlity mà mình chọn

        }

        // xử lý sự kiện thêm hay bớt quanlity khi mua sản phẩm
        btn_Add.setOnClickListener(view->{
            if(totalQuanlity<10){
                totalQuanlity++;
                txt_quanlity.setText(String.valueOf(totalQuanlity));
            }
        });
        btn_Plus.setOnClickListener(view->{
            if(totalQuanlity>0){
                totalQuanlity--;
                txt_quanlity.setText(String.valueOf(totalQuanlity));
            }
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

}