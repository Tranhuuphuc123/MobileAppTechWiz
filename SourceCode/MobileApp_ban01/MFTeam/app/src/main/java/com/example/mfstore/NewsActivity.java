package com.example.mfstore;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mfstore.R;
import com.example.mfstore.eventListener.NewsAdapter;
import com.example.mfstore.model.News;

import java.util.ArrayList;

public class NewsActivity extends AppCompatActivity{
    NewsAdapter newsAdapter;
    RecyclerView recyc;
    private ArrayList <News> newsArrayList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_news);


        recyc = findViewById(R.id.newsRecyc);
        recyc.setLayoutManager(new LinearLayoutManager(this));
        newsArrayList = new ArrayList<>();
        newsAdapter = new NewsAdapter((Context) this, (ArrayList<News>) newsArrayList);
        recyc.setAdapter(newsAdapter);

        initialData();

    }

    protected void initialData(){
        newsArrayList.add(new News("The 20 Most Innovative Plant Varieties of 2020",
    "Let’s face it, not many people can make reliable predictions about the future. But do you know who...",
                "https://european-seed.com/2021/04/the-20-most-innovative-plant-varieties-2020-1-5/",R.drawable.tp1));

        newsArrayList.add(new News("Plant Breeding – How new varieties are created?",
                "Plants can reproduce either vegetatively (asexually) or from seed (sexually). When reproducing...",
                "https://plantui.com/blog/plantui-technology/plant-breeding-how-new-varieties-are-created/",R.drawable.tp2));

        newsArrayList.add(new News("16 New and Unusual Plant Varieties",
                "Grow your ketchup and fries on the same plant or start a mini-vineyard. Science is adding...",
                "https://www.hgtv.com/outdoors/flowers-and-plants/16-new-and-unusual-plant-varieties-for-2015-pictures",R.drawable.tp3));

        newsArrayList.add(new News("Ten Remarkable New Plants and Fungi Discovered in 2022",
                "The world’s largest waterlily, a long-lost relative of the sweet potato, and an herb that grows exclusively in rapids and waterfalls are...",
                "https://e360.yale.edu/digest/new-plants-discovered-2022-kew-gardens",R.drawable.tp4));

        newsArrayList.add(new News("Two New Plant Species Discovered in Central China",
                        "Chinese researchers have discovered two new plant species in central China's Hubei Province, according to a paper published...",
                "https://english.cas.cn/newsroom/cas_media/202303/t20230303_327867.shtml",R.drawable.tp6));

        newsArrayList.add(new News("Beautiful New Species of Flowering Plant Discovered",
                "Botanists from the Universitas Samudra have described a new species of the flowering plant...",
                "https://www.sci.news/biology/thottea-beungongtanoeh-11480.html",R.drawable.tp7));

        newsArrayList.add(new News("New plant species that grows in saline conditions of Kutch discovered",
                "Previously, Salsola kali, Salsola hatmanii, Salsola monoptera, Caroxylon imbricatum...",
                "https://indianexpress.com/article/cities/ahmedabad/new-plant-species-that-grows-in-saline-conditions-of-kutch-discovered-8828631/",R.drawable.tp8));

        newsArrayList.add(new News("New plants for your 2023 garden: Interesting annuals, perennials, fruit, and veggies",
                "My introductions to new plants come from an array of sources—trial garden visits, emails from growers ...",
                "https://savvygardening.com/new-plants/",R.drawable.tp9));

        newsArrayList.add(new News("https://savvygardening.com/new-plants/",
                "One of the best-blooming petunias yet, four striking new begonias, and a pair of disease-resistant...",
                "https://savvygardening.com/new-plants/",R.drawable.tp10));

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
