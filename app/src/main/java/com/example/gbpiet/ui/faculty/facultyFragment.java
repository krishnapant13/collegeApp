package com.example.gbpiet.ui.faculty;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.gbpiet.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class facultyFragment extends Fragment {
    private RecyclerView csDepartment,MechanicalDepartment,ASHDepartment,BioTechDepartment,CivilDepartment,
            MCADepartment,ECDepartment,EEDepartment;
    private LinearLayout csNoData,MechanicalNoData,ASHNoData,BioTechNoData,CivilNoData,MCANoData,ECNoData,EENoData;
    private List<TeacherData> list1, list2, list3,list4,list5,list6,list7,list8;
    private TeacherAdapter adapter;

    private DatabaseReference reference, dbRef;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_faculty, container, false);
        getActivity().setTitle("Faculties");


        csDepartment = view.findViewById(R.id.csDepartment);
        MechanicalDepartment = view.findViewById(R.id.MechanicalDepartment);
        ASHDepartment = view.findViewById(R.id.ASHDepartment);
        BioTechDepartment = view.findViewById(R.id.BioTechDepartment);
        CivilDepartment = view.findViewById(R.id.CivilDepartment);
        MCADepartment = view.findViewById(R.id.MCADepartment);
        ECDepartment = view.findViewById(R.id.ECDepartment);
        EEDepartment = view.findViewById(R.id.EEDepartment);

        csNoData = view.findViewById(R.id.csNoData);
        MechanicalNoData = view.findViewById(R.id.MechanicalNoData);
        ASHNoData = view.findViewById(R.id.ASHNoData);
        BioTechNoData = view.findViewById(R.id.BioTechNoData);
        CivilNoData = view.findViewById(R.id.CivilNoData);
        MCANoData = view.findViewById(R.id.MCANoData);
        ECNoData = view.findViewById(R.id.ECNoData);
        EENoData = view.findViewById(R.id.EENoData);

        reference = FirebaseDatabase.getInstance().getReference().child("teacher");
        csDepartment();
        MechanicalDepartment();
        ASHDepartment();
        BioTechDepartment();
        BioTechDepartment();
        CivilDepartment();
        MCADepartment();
        ECDepartment();
        EEDepartment();


        return  view;
    }

    private void csDepartment() {
        dbRef = reference.child("Computer Science");
        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                list1 = new ArrayList<>();
                if (!dataSnapshot.exists()) {
                    csNoData.setVisibility(View.VISIBLE);
                    csDepartment.setVisibility(View.GONE);
                }else{

                    csNoData.setVisibility(View.GONE);
                    csDepartment.setVisibility(View.VISIBLE);
                    for(DataSnapshot snapshot:dataSnapshot.getChildren()){
                        TeacherData data = snapshot.getValue(TeacherData.class);
                        list1.add(data);

                    }
                    csDepartment.setHasFixedSize(true);
                    csDepartment.setLayoutManager(new LinearLayoutManager(getContext()));
                    adapter = new TeacherAdapter(list1,getContext());
                    csDepartment.setAdapter(adapter);
                }getContext();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getContext(), error.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
    }

    private void MechanicalDepartment() {
        dbRef = reference.child("Mechanical Engineering");
        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                list2 = new ArrayList<>();
                if (!dataSnapshot.exists()) {
                    MechanicalNoData.setVisibility(View.VISIBLE);
                    MechanicalDepartment.setVisibility(View.GONE);
                }else{

                    MechanicalNoData.setVisibility(View.GONE);
                    MechanicalDepartment.setVisibility(View.VISIBLE);
                    for(DataSnapshot snapshot:dataSnapshot.getChildren()){
                        TeacherData data = snapshot.getValue(TeacherData.class);
                        list2.add(data);

                    }
                    MechanicalDepartment.setHasFixedSize(true);
                    MechanicalDepartment.setLayoutManager(new LinearLayoutManager(getContext()));
                    adapter = new TeacherAdapter(list2,getContext());
                    MechanicalDepartment.setAdapter(adapter);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getContext(),error.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
    }

    private void ASHDepartment() {
        dbRef = reference.child("Applied Science & Humanities");
        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                list3 = new ArrayList<>();
                if (!dataSnapshot.exists()) {
                    ASHNoData.setVisibility(View.VISIBLE);
                    ASHDepartment.setVisibility(View.GONE);
                }else{

                    ASHNoData.setVisibility(View.GONE);
                    ASHDepartment.setVisibility(View.VISIBLE);
                    for(DataSnapshot snapshot:dataSnapshot.getChildren()){
                        TeacherData data = snapshot.getValue(TeacherData.class);
                        list3.add(data);

                    }
                    ASHDepartment.setHasFixedSize(true);
                    ASHDepartment.setLayoutManager(new LinearLayoutManager(getContext()));
                    adapter = new TeacherAdapter(list3,getContext());
                    ASHDepartment.setAdapter(adapter);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getContext(), error.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
    }

    private void BioTechDepartment() {
        dbRef = reference.child("BioTech");
        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                list4 = new ArrayList<>();
                if (!dataSnapshot.exists()) {
                    BioTechNoData.setVisibility(View.VISIBLE);
                    BioTechDepartment.setVisibility(View.GONE);
                }else{

                    BioTechNoData.setVisibility(View.GONE);
                    BioTechDepartment.setVisibility(View.VISIBLE);
                    for(DataSnapshot snapshot:dataSnapshot.getChildren()){
                        TeacherData data = snapshot.getValue(TeacherData.class);
                        list4.add(data);

                    }
                    BioTechDepartment.setHasFixedSize(true);
                    BioTechDepartment.setLayoutManager(new LinearLayoutManager(getContext()));
                    adapter = new TeacherAdapter(list4,getContext());
                    BioTechDepartment.setAdapter(adapter);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getContext(), error.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
    }

    private void CivilDepartment() {
        dbRef = reference.child("Civil Engineering");
        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                list5 = new ArrayList<>();
                if (!dataSnapshot.exists()) {
                    CivilNoData.setVisibility(View.VISIBLE);
                    CivilDepartment.setVisibility(View.GONE);
                }else{

                    CivilNoData.setVisibility(View.GONE);
                    CivilDepartment.setVisibility(View.VISIBLE);
                    for(DataSnapshot snapshot:dataSnapshot.getChildren()){
                        TeacherData data = snapshot.getValue(TeacherData.class);
                        list5.add(data);

                    }
                    CivilDepartment.setHasFixedSize(true);
                    CivilDepartment.setLayoutManager(new LinearLayoutManager(getContext()));
                    adapter = new TeacherAdapter(list5,getContext());
                    CivilDepartment.setAdapter(adapter);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getContext(), error.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
    }

    private void MCADepartment() {
        dbRef = reference.child("Master of Computer Applications");
        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                list6 = new ArrayList<>();
                if (!dataSnapshot.exists()) {
                    MCANoData.setVisibility(View.VISIBLE);
                    MCADepartment.setVisibility(View.GONE);
                }else{

                    MCANoData.setVisibility(View.GONE);
                    MCADepartment.setVisibility(View.VISIBLE);
                    for(DataSnapshot snapshot:dataSnapshot.getChildren()){
                        TeacherData data = snapshot.getValue(TeacherData.class);
                        list6.add(data);

                    }
                    MCADepartment.setHasFixedSize(true);
                    MCADepartment.setLayoutManager(new LinearLayoutManager(getContext()));
                    adapter = new TeacherAdapter(list6,getContext());
                    MCADepartment.setAdapter(adapter);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getContext(), error.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
    }

    private void ECDepartment() {
        dbRef = reference.child("Electronics & Communication");
        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                list7 = new ArrayList<>();
                if (!dataSnapshot.exists()) {
                    ECNoData.setVisibility(View.VISIBLE);
                    ECDepartment.setVisibility(View.GONE);
                }else{

                    ECNoData.setVisibility(View.GONE);
                    ECDepartment.setVisibility(View.VISIBLE);
                    for(DataSnapshot snapshot:dataSnapshot.getChildren()){
                        TeacherData data = snapshot.getValue(TeacherData.class);
                        list7.add(data);

                    }
                    ECDepartment.setHasFixedSize(true);
                    ECDepartment.setLayoutManager(new LinearLayoutManager(getContext()));
                    adapter = new TeacherAdapter(list7,getContext());
                    ECDepartment.setAdapter(adapter);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getContext(), error.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
    }

    private void EEDepartment() {
        dbRef = reference.child("Electrical Engineering");
        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                list8  = new ArrayList<>();
                if (!dataSnapshot.exists()) {
                    EENoData.setVisibility(View.VISIBLE);
                    csDepartment.setVisibility(View.GONE);
                }else{

                    EENoData.setVisibility(View.GONE);
                    EEDepartment.setVisibility(View.VISIBLE);
                    for(DataSnapshot snapshot:dataSnapshot.getChildren()){
                        TeacherData data = snapshot.getValue(TeacherData.class);
                        list8.add(data);

                    }
                    EEDepartment.setHasFixedSize(true);
                    EEDepartment.setLayoutManager(new LinearLayoutManager(getContext()));
                    adapter = new TeacherAdapter(list8,getContext());
                    EEDepartment.setAdapter(adapter);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getContext(), error.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
    }

}
