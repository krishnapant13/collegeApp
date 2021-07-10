package com.example.gbpiet.ui.gallery;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.gbpiet.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class galleryFragment extends Fragment {

    RecyclerView josh , farewell ,meetGreet , goonj , spandan , otherEvents;
    GalleryAdapter adapter;
    DatabaseReference reference;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_gallery, container, false);

        getActivity().setTitle("Our Gallery");
        josh = view.findViewById(R.id.josh);
        farewell = view.findViewById(R.id.farewell);
        meetGreet = view.findViewById(R.id.meetGreet);
        goonj = view.findViewById(R.id.goonj);
        spandan = view.findViewById(R.id.spandan);
        otherEvents = view.findViewById(R.id.otherEvents);

        reference= FirebaseDatabase.getInstance().getReference().child("gallery");
        getJoshImage();
        getFarewellImage();
        getMeet();
        getGoonjImages();
        getSpandanImages();
        getOtherEventsImages();
        return  view;

    }

    private void getOtherEventsImages() {
        reference.child("Other Events").addValueEventListener(new ValueEventListener() {
            List<String> imageList = new ArrayList<>();
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot snapshot: dataSnapshot.getChildren()){
                    String data = (String) snapshot.getValue();
                    imageList.add(data);
                }
                adapter = new GalleryAdapter(getContext(), imageList);
                otherEvents.setLayoutManager(new GridLayoutManager(getContext(),3));
                otherEvents.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    private void getSpandanImages() {
        reference.child("SPANDAN").addValueEventListener(new ValueEventListener() {
            List<String> imageList = new ArrayList<>();
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot snapshot: dataSnapshot.getChildren()){
                    String data = (String) snapshot.getValue();
                    imageList.add(data);
                }
                adapter = new GalleryAdapter(getContext(), imageList);
                spandan.setLayoutManager(new GridLayoutManager(getContext(),3));
                spandan.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void getGoonjImages() {
        reference.child("GOONJ").addValueEventListener(new ValueEventListener() {
            List<String> imageList = new ArrayList<>();
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot snapshot: dataSnapshot.getChildren()){
                    String data = (String) snapshot.getValue();
                    imageList.add(data);
                }
                adapter = new GalleryAdapter(getContext(), imageList);
                goonj.setLayoutManager(new GridLayoutManager(getContext(),3));
                goonj.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void getMeet() {
        reference.child("Meet&Greet").addValueEventListener(new ValueEventListener() {
            List<String> imageList = new ArrayList<>();
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot snapshot: dataSnapshot.getChildren()){
                    String data = (String) snapshot.getValue();
                    imageList.add(data);
                }
                adapter = new GalleryAdapter(getContext(), imageList);
                meetGreet.setLayoutManager(new GridLayoutManager(getContext(),3));
                meetGreet.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void getJoshImage() {
        reference.child("JOSH").addValueEventListener(new ValueEventListener() {
            List<String> imageList = new ArrayList<>();
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot snapshot: dataSnapshot.getChildren()){
                    String data = (String) snapshot.getValue();
                    imageList.add(data);
                }
                adapter = new GalleryAdapter(getContext(), imageList);
                josh.setLayoutManager(new GridLayoutManager(getContext(),3));
                josh.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }


    private void getFarewellImage() {
        reference.child("Farewell").addValueEventListener(new ValueEventListener() {
            List<String> imageList = new ArrayList<>();
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot snapshot: dataSnapshot.getChildren()){
                    String data = (String) snapshot.getValue();
                    imageList.add(data);
                }
                adapter = new GalleryAdapter(getContext(), imageList);
                farewell.setLayoutManager(new GridLayoutManager(getContext(),3));
                farewell.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        reference.child("");
    }
}