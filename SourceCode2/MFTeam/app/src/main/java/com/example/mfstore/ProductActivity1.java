//package com.example.mfstore;
//
//import android.os.Bundle;
//
//import androidx.annotation.Nullable;
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.recyclerview.widget.LinearLayoutManager;
//import androidx.recyclerview.widget.RecyclerView;
//
//import com.example.mfstore.eventListener.Event_AllProductsItem;
//import com.example.mfstore.eventListener.Event_ExlusivesItem;
//import com.example.mfstore.eventListener.Event_OfferItem;
//import com.example.mfstore.model.AllProductModels;
//import com.example.mfstore.model.ExclusivesModels;
//import com.example.mfstore.model.OfferModels;
//
//import java.util.ArrayList;
//import java.util.List;
//
//
//public class ProductActivity1 extends AppCompatActivity {
//
//
//    // nhóm properties của OfferItem
//    RecyclerView recyclerView;
//    List<OfferModels> offerModels;
//    Event_OfferItem event_offerItem;
//
//    // nhóm properties của Exclusives
//    RecyclerView recyclerViewExclusives;
//    List<ExclusivesModels> exclusivesModels;
//    Event_ExlusivesItem event_exlusivesItem;
//
//    // nhóm properties của Product alls
//    RecyclerView recyclerViewAllproducts;
//    List<AllProductModels> allProductModels;
//    Event_AllProductsItem event_allProductsItem;
//
//    @Override
//    protected void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_product);
//
//        /*nhóm xử lý sự kiện cho Product Fragment*/
//        getOffers();
//
//        getExclusives();
//        getAllProducts();
//    }
//
//
//
//    /*method getOffers xử lý sản phầm product*/
//    public void getOffers(){
//        // ánh xạ id của Recycle_product_id ccuartrang product_fragment.xml
//        recyclerView = findViewById(R.id.recyclerview_product_id);
//        recyclerView.setLayoutManager(new LinearLayoutManager(this,RecyclerView.HORIZONTAL,false));
//        recyclerView.setHasFixedSize(true);
//
//        offerModels = new ArrayList<>();
//        offerModels.add(new OfferModels(R.drawable.bonsai_bestseller1));
//        offerModels.add(new OfferModels(R.drawable.bonsai_bestseller1));
//        offerModels.add(new OfferModels(R.drawable.bonsai_bestseller2));
//        offerModels.add(new OfferModels(R.drawable.bonsai_bestseller2));
//        offerModels.add(new OfferModels(R.drawable.bonsai_bestseller2));
//        offerModels.add(new OfferModels(R.drawable.bonsai_bestseller2));
//
//        event_offerItem = new Event_OfferItem(offerModels);
//        recyclerView.setAdapter(event_offerItem);
//    }
//
//    public void getExclusives(){
//        // ánh xạ id của Recycle_product_id ccuartrang product_fragment.xml
//        recyclerViewExclusives = findViewById(R.id.recyclerview_product_cayanqua);
//        recyclerViewExclusives.setLayoutManager(new LinearLayoutManager(this,RecyclerView.HORIZONTAL,false));
//        recyclerViewExclusives.setHasFixedSize(true);//?
//
//        exclusivesModels = new ArrayList<>();
//        exclusivesModels.add(new ExclusivesModels(R.drawable.page1));
//        exclusivesModels.add(new ExclusivesModels(R.drawable.page2));
//        exclusivesModels.add(new ExclusivesModels(R.drawable.page3));
//        exclusivesModels.add(new ExclusivesModels(R.drawable.page1));
//        exclusivesModels.add(new ExclusivesModels(R.drawable.page2));
//        exclusivesModels.add(new ExclusivesModels(R.drawable.page3));
//        exclusivesModels.add(new ExclusivesModels(R.drawable.page1));
//        exclusivesModels.add(new ExclusivesModels(R.drawable.page2));
//
//        event_exlusivesItem = new Event_ExlusivesItem(exclusivesModels);
//        recyclerViewExclusives.setAdapter(event_exlusivesItem); //Adapter?
//    }
//
//    public void getAllProducts(){
//        // ánh xạ id của Recycle_product_id ccuartrang product_fragment.xml
//        recyclerViewAllproducts = findViewById(R.id.recyclerview_product_caycanh);
//        recyclerViewAllproducts.setLayoutManager(new LinearLayoutManager(this,RecyclerView.VERTICAL,false));
//        recyclerViewAllproducts.setHasFixedSize(true);
//
//        allProductModels = new ArrayList<>();
//        allProductModels.add(new AllProductModels(R.drawable.bonsai_bestseller1));
//        allProductModels.add(new AllProductModels(R.drawable.bonsai_bestseller2));
//        allProductModels.add(new AllProductModels(R.drawable.bonsai_bestseller3));
//        allProductModels.add(new AllProductModels(R.drawable.bonsai_bestseller1));
//        allProductModels.add(new AllProductModels(R.drawable.bonsai_bestseller2));
//        allProductModels.add(new AllProductModels(R.drawable.bonsai_bestseller3));
//        allProductModels.add(new AllProductModels(R.drawable.bonsai_bestseller1));
//        allProductModels.add(new AllProductModels(R.drawable.bonsai_bestseller2));
//        allProductModels.add(new AllProductModels(R.drawable.bonsai_bestseller3));
//        allProductModels.add(new AllProductModels(R.drawable.bonsai_bestseller1));
//        allProductModels.add(new AllProductModels(R.drawable.bonsai_bestseller2));
//        allProductModels.add(new AllProductModels(R.drawable.bonsai_bestseller3));
//
//        event_allProductsItem = new Event_AllProductsItem(allProductModels);
//        recyclerViewAllproducts.setAdapter(event_allProductsItem);
//    }
//
//
//}
