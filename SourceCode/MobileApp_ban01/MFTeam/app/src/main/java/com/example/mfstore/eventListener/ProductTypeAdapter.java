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
import com.example.mfstore.model.ProductTypeModels;

import java.util.List;

public class ProductTypeAdapter extends RecyclerView.Adapter<ProductTypeAdapter.ViewHolder> {
    private Context context;
    private List<ProductTypeModels> listProductTypeModels;

    public ProductTypeAdapter(Context context, List<ProductTypeModels> listProductTypeModels) {
        this.context = context;
        this.listProductTypeModels = listProductTypeModels;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_product_type, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            ProductTypeModels productTypeModels = listProductTypeModels.get(position);
            if(productTypeModels == null){
                return;
            }
        Glide.with(context).load(productTypeModels.getImg()).into(holder.imageView);
        holder.txt_NameType.setText(productTypeModels.getName());

        // Xử lý sự kiện click vào mục trong RecyclerView từ trang product sang productdetails
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Chuyển sang giao diện ProductDetailsActivity và truyền thông tin sản phẩm qua Intent
                Intent intent = new Intent(context, ProductDetailsActivity.class);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        if(listProductTypeModels !=null) {
            return listProductTypeModels.size();
        }
        return 0;

    }

    public static class ViewHolder extends RecyclerView.ViewHolder  {
        private ImageView imageView;
        private TextView txt_NameType, txt_id;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            // ánh xạ các thông tin id trong cardView_ProductType
            imageView = itemView.findViewById(R.id.img_productType);
            txt_NameType = itemView.findViewById(R.id.txt_productType);

        }
    }
}
