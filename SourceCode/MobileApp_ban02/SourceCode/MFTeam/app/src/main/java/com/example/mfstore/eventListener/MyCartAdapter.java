package com.example.mfstore.eventListener;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mfstore.R;
import com.example.mfstore.model.CartModel;

import java.util.List;

public class MyCartAdapter extends RecyclerView.Adapter<MyCartAdapter.ViewHolder> {
    private Context context;
    private List<CartModel> listCart;
    int totalPrices = 0;

    public MyCartAdapter(Context context, List<CartModel> listCart) {
        this.context = context;
        this.listCart = listCart;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cartview_cart,parent,false);
        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull MyCartAdapter.ViewHolder holder, int position) {
        CartModel cartModel = listCart.get(position);
        if(cartModel == null){
            return;
        }
        holder.name.setText(cartModel.getProductName());
        holder.price.setText(String.valueOf(cartModel.getProductPrice()));
        holder.date.setText(cartModel.getCurrentDate());
        holder.time.setText(cartModel.getCurrentTime());
        holder.quantity.setText(cartModel.getTotalQuantity());
        holder.totalprice.setText(String.valueOf(cartModel.getTotalPrice() + " $ "));

        // ghi nhận tổng tiền hóa đơn
        totalPrices += cartModel.getTotalPrice();
        Intent intent = new Intent("MyTotalAmount");
        intent.putExtra("totalAmount", totalPrices);
        LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
    }


    @Override
    public int getItemCount() {
        if(listCart!= null ){
            return listCart.size();
        }
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView name, price, date, time, quantity, totalprice;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            // bind các id tương ứng bên cardView_cart
            name = itemView.findViewById(R.id.txt_cartview_cartName);
            price = itemView.findViewById(R.id.txt_cartview_cartPrice);
            date = itemView.findViewById(R.id.txt_cartview_cartDate);
            time = itemView.findViewById(R.id.txt_cartview_cartTime);
            quantity = itemView.findViewById(R.id.txt_cartview_cartQuantity);
            totalprice = itemView.findViewById(R.id.txt_cartview_cartTotalPrice);
        }
    }
}
