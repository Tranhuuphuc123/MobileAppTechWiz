//package com.example.mfstore.fragment;
//
//import android.content.Context;
//import android.os.Bundle;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.Toast;
//
//import androidx.annotation.NonNull;
//import androidx.annotation.Nullable;
//import androidx.fragment.app.Fragment;
//import androidx.recyclerview.widget.LinearLayoutManager;
//import androidx.recyclerview.widget.RecyclerView;
//
//import com.example.mfstore.R;
//import com.example.mfstore.eventListener.Event_Products_Plants;
//import com.example.mfstore.model.ProductModels;
//import com.google.android.gms.tasks.OnCompleteListener;
//import com.google.android.gms.tasks.Task;
//import com.google.firebase.firestore.FirebaseFirestore;
//import com.google.firebase.firestore.QueryDocumentSnapshot;
//import com.google.firebase.firestore.QuerySnapshot;
//
//
//import java.util.ArrayList;
//import java.util.List;
//
//
//public class ProductFragment extends Fragment {
//    List<ProductModels> productModelsList;
//    Event_Products_Plants event_products_plants;
//
//    RecyclerView recyclerView_product;
//    FirebaseFirestore db;
//
//
//
//    @Nullable
//    @Override
//    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//
//        View root = inflater.inflate(R.layout.activity_product, container, false);
//        db = FirebaseFirestore.getInstance();
//
//        recyclerView_product = root.findViewById(R.id.recyclerview_product_Indoor);
//
//        // item products
//        recyclerView_product.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.HORIZONTAL,false));
//        productModelsList = new ArrayList<>();
//        event_products_plants = new Event_Products_Plants(getActivity(),productModelsList);
//        recyclerView_product.setAdapter(event_products_plants);
//
//
//        // đoạn code mẫu load firebase- xử lý kết nôi firebase
//        db.collection("Plants_Products")
//                .get()
//                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
//                    @Override
//                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
//                        if (task.isSuccessful()) {
//                            for (QueryDocumentSnapshot document : task.getResult()) {
//                                ProductModels productModels = document.toObject(ProductModels.class);
//                                productModelsList.add(productModels);
//                                event_products_plants.notifyDataSetChanged();
//                            }
//                        } else {
//                            Toast.makeText(getActivity(), "Error!"+task.getException(), Toast.LENGTH_LONG).show();
//                        }
//                    }
//                });
//
//        // trả về root cũng chính là giao diện activity_product.xml
//        return root;
//    }
//}
