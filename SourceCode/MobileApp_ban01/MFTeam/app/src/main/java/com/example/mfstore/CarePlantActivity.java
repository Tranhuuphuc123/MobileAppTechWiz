package com.example.mfstore;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mfstore.R;
import com.example.mfstore.model.News;

import java.util.ArrayList;

public class CarePlantActivity extends AppCompatActivity {
    CarePlantApdapter newsAdapter;
    RecyclerView recyc;
    private ArrayList <News> newsArrayList;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_news);



        recyc = findViewById(R.id.newsRecyc);
        recyc.setLayoutManager(new LinearLayoutManager(this));
        newsArrayList = new ArrayList<>();
        newsAdapter = new CarePlantApdapter((Context) this, (ArrayList<News>) newsArrayList);
        recyc.setAdapter(newsAdapter);

        initialData();

    }

    protected void initialData(){
        newsArrayList.add(new News("Our Top 10 Plant Care Tips",
                "There are plenty of ways to care for your plant. With the help of our plant experts, we’ve weeded...",
                "https://www.thesill.com/blog/top-ten-plant-care-tips",R.drawable.pl1));

        newsArrayList.add(new News("Tips for Keeping Indoor Plants Happy and Healthy Year-Round",
                "Keep your houseplants happy and healthy! From knowing how often to water to providing the correct...'  ","https://www.almanac.com/content/houseplant-care-guide",R.drawable.pl2));

        newsArrayList.add(new News("9 Essential Tips for Keeping Your Houseplants Healthy",
                "No matter what plants you have in your collection, they all have similar basic needs. Here's how to keep...",
                "https://www.bhg.com/gardening/houseplants/care/houseplant-care-guide/",R.drawable.pl3));

        newsArrayList.add(new News("7 Indoor Plant Care Tools To Step Up Your Green Thumb Game",
                "It’s no secret that when it comes to indoor plant care, just two basic essentials ...",
                "https://blog.leonandgeorge.com/posts/indoor-plant-care-tools",R.drawable.pl4));

        newsArrayList.add(new News("17 EASIEST SEEDS TO START INDOORS",
                "Choosing the easiest seeds to start indoors is important, especially if you’re a new gardener. To help...",
                "https://getbusygardening.com/easiest-seeds-to-start-indoors/",R.drawable.pl5));

        newsArrayList.add(new News("12 Easy Plants To Grow From Seed & Grow Indoors",
                "Growing plants from seed is a hobby in it’s own right, with different seed varieties requiring different...",
                "https://www.plantflix.com/blogs/news/12-indoor-plants-that-are-easy-to-germinate-and-grow-from-seed",R.drawable.pl6));

        newsArrayList.add(new News("How to Start Seeds Indoors: The Complete Guide",
                "Gardening can be an expensive hobby if you purchase all your plants as potted nursery specimens. Fortunately, most vegetables...",
                "https://www.thespruce.com/growing-seeds-indoors-common-mistakes-847800",R.drawable.pl7));

        newsArrayList.add(new News("HOW TO START SEEDS INDOORS IN 4 EASY STEPS",
                "Coaxing new life out of a seed is one of the highlights of the year for gardeners, right up there with harvesting perfectly ripe ...",
                "https://www.swansonsnursery.com/blog/how-to-start-seeds-indoors",R.drawable.pl8));

        newsArrayList.add(new News( "How to Fertilize Houseplants...","Too many people overlook the importance of fertilizing indoor plants. However, proper feedings",
                "https://savvygardening.com/new-plants/",R.drawable.pl9));

//        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("News");
//
//        ref.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                for(DataSnapshot datasSnapshot : snapshot.getChildren()){
//                    News news = datasSnapshot.getValue(News.class);
//                    Toast.makeText(NewsActivity.this,"Nes: "+news.getImg()+" "+news.getSmallTitle(),Toast.LENGTH_LONG).show();
//                    Log.e("Error",news.toString());
//                    newsArrayList.add(news);
//                }
//                newsAdapter.notifyDataSetChanged();
//            }
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//                Toast.makeText(NewsActivity.this,"Get data failed!", Toast.LENGTH_LONG).show();
//            }
//        });
    }
}
