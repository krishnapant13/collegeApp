package com.example.gbpiet.ui.about;

import android.graphics.text.LineBreaker;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.gbpiet.R;

import java.util.ArrayList;
import java.util.List;

public class aboutFragment extends Fragment {

    private ViewPager coursesViewPager;
    private CourseAdapter adapter;
    private List<CourseModel> list;
    private TextView aboutus;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_about, container, false);

        list = new ArrayList<>();
        list.add(new CourseModel(R.drawable.ic_mca,"Master of Computer Science","The Master of Computer Applications course was started inthe year 2001 with experienced faculty hascome out a long way on the frontierlines of software research and development. The course keeps itself in pace with the IT changes and regularly updates its laboratories with the latest computing facilities and software technologies. Master of Computer Applications (3 Yrs., 6 Semesters)."));
        list.add(new CourseModel(R.drawable.ic_cs,"Computer Science","The department of Computer Science and Engineering was established in the year 1992, has come out a long way on the frontier lines of software research and development. The department keeps itself in pace with the IT changes and regularly updates its laboratories with the latest computing facilities, licensed software and IT infrastructures to give exposure to the students to the state of the art technologies."));
        list.add(new CourseModel(R.drawable.ic_civil,"Civil Engineering","The civil engineering department started in the year in 2005 with an aim to develop professional civil engineers who can shape the infrastructure development and contribute to the public's health, safety and standard of living. Civil engineering is considered a broad field of engineering with lot of diversity right from structural to transportation engineering, environmental to hydraulics engineering, geotechnical engineering to hydrology etc. "));
        list.add(new CourseModel(R.drawable.ic_applied,"Applied Science & Humanities","The Humanities section of the Department offers core courses in English Language, Professional Communication, and Industrial Management. Keeping pace with the specific industrial demands, the section has also introduced Industrial Economics & Principles of Management, Organizational Behaviour including Interpersonal & Group Processes and Dynamics, with courses as diverse as Value Education."));
        list.add(new CourseModel(R.drawable.ic_biotech,"Biotech","Biotechnology Engineering at G B Pant Engineering College was founded as an academic unit during the academic session 2005-2006, and with the objective of integrating life sciences with engineering to develop cutting-edge technology through research, training and technical innovation. The department at the time of establishment offered only Masters in Biotechnology. "));
        list.add(new CourseModel(R.drawable.ic_ecs,"Electronics & Communication","Welcome to the Department of Electronics & Communication Engineering at G. B Pant Engineering College, Pauri-Garhwal (Uttarakhand). The Department of Electronics & Communication Engineering is proud to be first department of the college established in 1991 to offer Bachelor’s degree in Electronics & Communication Engineering with initial intake of 20 students. Presently, B. Tech. degree program has an intake of 60 students. "));
        list.add(new CourseModel(R.drawable.ic_mechanical,"Mechanical Engineering","The Department of Mechanical Engineering, in 16 years of its establishment, has come a long way developing along modern lines by laying emphasis upon Computational Tools, Simulation, and Analysis, Designing and Manufacturing Techniques."));
        list.add(new CourseModel(R.drawable.ic_electrical,"Electrical Engineering","The Department started B.E. program in Electrical Engineering in 1997. It is envisaged to prepare Electrical Engineering graduates with adequate information on large-scale systems simulation and analysis tools, design capabilities and conceptual background for handling industrial process, embedded instrumentation and control strategies."));

        adapter = new CourseAdapter(getContext(),list);
        getActivity().setTitle("About Us");
        coursesViewPager = view.findViewById(R.id.coursesViewPager);
        aboutus = view.findViewById(R.id.aboutus);
        aboutus.setJustificationMode(LineBreaker.JUSTIFICATION_MODE_INTER_WORD);

        coursesViewPager.setAdapter(adapter);

        ImageView imageView = view.findViewById(R.id.collegeImage);

        Glide.with(getContext())
                .load("https://firebasestorage.googleapis.com/v0/b/gbpiet-13b5a.appspot.com/o/slider%2FCmpsi.jpg?alt=media&token=bfd8a14b-dff2-43ef-8e6f-8896d756fc93").into(imageView);


        return  view;
    }
}