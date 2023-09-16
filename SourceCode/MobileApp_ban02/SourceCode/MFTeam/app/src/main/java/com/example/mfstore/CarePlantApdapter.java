package com.example.mfstore;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mfstore.R;
import com.example.mfstore.model.News;

import java.util.ArrayList;

public class CarePlantApdapter extends RecyclerView.Adapter<CarePlantApdapter.NewsViewHold> {
    private ArrayList<News> newsArrayList = new ArrayList<>();
    private Context context;
    CardView cardView ;

    public CarePlantApdapter(Context context, ArrayList<News>newsArrayList){
        this.context = context;
        this.newsArrayList = newsArrayList;
    }

    @NonNull
    @Override
    public NewsViewHold onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View rowView = inflater.inflate(R.layout.care_plant, parent,false);
        return new NewsViewHold(rowView);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsViewHold holder, int position) {
        News newsObj = newsArrayList.get(position);
        if(newsObj == null){
            return;
        }
        holder.imgvh.setImageResource(newsObj.getImg());
        holder.bigTitle.setText(newsObj.getBigTitle());
        holder.smallTitle.setText(newsObj.getSmallTitle());
    }

    @Override
    public int getItemCount() {
        if(newsArrayList != null){
            return newsArrayList.size();
        }
        return 0;
    }

    public class NewsViewHold extends RecyclerView.ViewHolder{
        private TextView bigTitle;
        private TextView smallTitle;
        private ImageView imgvh;

        public NewsViewHold(View itemView){
            super(itemView);
            bigTitle = itemView.findViewById(R.id.newsBigTitle);
            smallTitle = itemView.findViewById(R.id.newsSmallTitle);
            imgvh = itemView.findViewById(R.id.newsImg);
            cardView = itemView.findViewById(R.id.newsCardDetails);


            itemView.setOnClickListener(v -> {
                int position = getAdapterPosition();
                if (position != RecyclerView.NO_POSITION) {
                    News clickedItem = newsArrayList.get(position);
                    String articleUrl = clickedItem.getLink();
                    openWebBrowser(v.getContext(), articleUrl);
                }
            });
        }
    }

    private void openWebBrowser(Context context, String url) {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        context.startActivity(intent);
    }
}
