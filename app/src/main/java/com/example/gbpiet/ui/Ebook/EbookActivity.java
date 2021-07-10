  package com.example.gbpiet.ui.Ebook;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.gbpiet.R;
import com.facebook.shimmer.ShimmerFrameLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Document;

import java.util.ArrayList;
import java.util.List;

public class EbookActivity extends AppCompatActivity {

    private RecyclerView ebookRecycler;
    private DatabaseReference reference;
    private List<Edata> list;
    private EbookAdapter adapter;
    ShimmerFrameLayout shimmerFrameLayout;
    LinearLayout ShimmerLayout;
    EditText search;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ebook);

//        setting the back button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Ebooks");

        ebookRecycler = findViewById(R.id.ebookRecycler);
        reference = FirebaseDatabase.getInstance().getReference().child("Document");
        shimmerFrameLayout = findViewById(R.id.shimmerView);
        ShimmerLayout = findViewById(R.id.ShimmerLayout);
        search = findViewById(R.id.searchText);

        getData();

    }

    private void getData() {
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                list = new ArrayList<>();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Edata data = snapshot.getValue(Edata.class);
                    list.add(0,data);
                }
                adapter = new EbookAdapter(EbookActivity.this, list);
                ebookRecycler.setLayoutManager(new LinearLayoutManager(EbookActivity.this));

                ebookRecycler.setAdapter(adapter);

                shimmerFrameLayout.stopShimmer();
                ShimmerLayout.setVisibility(View.GONE);
                search.setVisibility(View.VISIBLE);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(EbookActivity.this, databaseError.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
        search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                filter(editable.toString());

            }
        });
    }

    private void filter(String text) {
        ArrayList<Edata> filterlist =new ArrayList<>();
        for (Edata item :list){
            if (item.getDocumentTitle().toLowerCase().contains(text.toLowerCase())){
                filterlist.add(item);
            }
        }
        adapter.Filterlist(filterlist);
    }


    @Override
    protected void onPause() {
        shimmerFrameLayout.stopShimmer();
        super.onPause();
    }

    @Override
    protected void onStart() {
        shimmerFrameLayout.startShimmer();
        super.onStart();
    }
}