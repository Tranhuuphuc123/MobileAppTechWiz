package com.example.mfstore.eventListener;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mfstore.R;
import com.example.mfstore.model.AllProductModels;
import com.makeramen.roundedimageview.RoundedImageView;

import java.util.List;

public class Event_FragmentCart extends RecyclerView.Adapter<Event_FragmentCart.ViewHolder>  {

    List<AllProductModels> listCart_img;

    public Event_FragmentCart(List<AllProductModels> list) {
        this.listCart_img = list;
    }

    @NonNull
    @Override
    public Event_FragmentCart.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.card_fragmentcart,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull Event_FragmentCart.ViewHolder holder, int position) {
        holder.roundedImageView.setImageResource(listCart_img.get(position).getImg());
    }

    @Override
    public int getItemCount() {
        return listCart_img.size();
    }

    public static class ViewHolder extends  RecyclerView.ViewHolder{
         RoundedImageView roundedImageView;
        public ViewHolder(@NonNull View itemView){
            super(itemView);
            roundedImageView = itemView.findViewById(R.id.roundimg_card_fragmentcart);
        }
    }
}

