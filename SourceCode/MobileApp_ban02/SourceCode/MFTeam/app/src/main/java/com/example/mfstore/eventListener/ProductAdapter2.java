package com.example.mfstore.eventListener;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.mfstore.ProductDetailsActivity;
import com.example.mfstore.R;
import com.example.mfstore.model.ProductModels;

import java.util.List;

public class ProductAdapter2 extends RecyclerView.Adapter<ProductAdapter2.ViewHolder> {

     private Context context;
     private List<ProductModels> listProducts;

    public ProductAdapter2(Context context, List<ProductModels> listProducts) {
        this.context = context;
        this.listProducts = listProducts;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.alls_product_item, parent, false);
        return new ViewHolder(view);
    }


    // hàm dùng để bing dữ liệu lên
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        ProductModels productModels = listProducts.get(position);
        if(productModels == null){
                return;
        }
        Glide.with(context).load(productModels.getProductImg()).into(holder.product_img);
        holder.productName.setText(productModels.getProductName());
        holder.productPrice.setText(productModels.getProductPrice());

        // Xử lý sự kiện click vào mục trong RecyclerView từ trang product sang productdetails
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Chuyển sang giao diện ProductDetailsActivity và truyền thông tin sản phẩm qua Intent
                Intent intent = new Intent(context, ProductDetailsActivity.class);
                intent.putExtra("details", productModels);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        if(listProducts!= null ){
            return listProducts.size();
        }
        return 0;
    }



    public static class ViewHolder extends  RecyclerView.ViewHolder {
        private ImageView product_img;
        private TextView productName, productPrice;
        public ViewHolder(@NonNull View itemView){
            super(itemView);
            // ánh xạ các thông tin dl trên firebase với cardview_plants.xml(thẻ card thiết kế)
            product_img = itemView.findViewById(R.id.round_img_allproducts);
            productName = itemView.findViewById(R.id.txt_allproducts1);
            productPrice = itemView.findViewById(R.id.txt_allproducts2);
        }
    }
}
