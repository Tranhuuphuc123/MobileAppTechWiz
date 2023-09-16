package com.example.mfstore.eventListener;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mfstore.R;
import com.example.mfstore.model.ExclusivesModels;
import com.makeramen.roundedimageview.RoundedImageView;

import java.util.List;

public class Event_Recycleview_home extends RecyclerView.Adapter<Event_Recycleview_home.ViewHolder>{
    List<ExclusivesModels> listExclusives;

    public Event_Recycleview_home(List<ExclusivesModels> listExclusives) {
        this.listExclusives = listExclusives;
    }

    @NonNull
    @Override
    public Event_Recycleview_home.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.exclusives_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull Event_Recycleview_home.ViewHolder holder, int position) {
        holder.roundedImageView.setImageResource(listExclusives.get(position).getImg());
    }

    @Override
    public int getItemCount() {
        return listExclusives.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private RoundedImageView roundedImageView;
        public ViewHolder(@NonNull View itemview){
            super(itemview);
            roundedImageView = itemview.findViewById(R.id.round_img_exclusives);
        }
    }
}
