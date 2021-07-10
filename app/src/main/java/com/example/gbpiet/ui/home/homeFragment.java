package com.example.gbpiet.ui.home;

import android.content.Intent;
import android.graphics.text.LineBreaker;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.gbpiet.R;
import com.smarteist.autoimageslider.DefaultSliderView;
import com.smarteist.autoimageslider.IndicatorAnimations;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderLayout;

public class homeFragment extends Fragment {

    private SliderLayout sliderLayout;
    private  ImageView map;
    private TextView reach, tail ,cd0 , cd1,ab;


    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        sliderLayout = view.findViewById(R.id.slider);
        sliderLayout.setIndicatorAnimation(IndicatorAnimations.THIN_WORM);
        sliderLayout.setSliderTransformAnimation(SliderAnimations.HORIZONTALFLIPTRANSFORMATION);
        sliderLayout.setScrollTimeInSec(3);
        getActivity().setTitle("G B P I E T");

        setSliderViews();

        cd0 = view.findViewById(R.id.cd0);

        cd0 = view.findViewById(R.id.cd0);
        cd1 = view.findViewById(R.id.cd1);
        tail = view.findViewById(R.id.tail);
        map = view.findViewById(R.id.map);
        map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMap();
            }
        });
        cd0.setJustificationMode(LineBreaker.JUSTIFICATION_MODE_INTER_WORD);



        cd1.setJustificationMode(LineBreaker.JUSTIFICATION_MODE_INTER_WORD);
        tail.setJustificationMode(LineBreaker.JUSTIFICATION_MODE_INTER_WORD);
        reach = view.findViewById(R.id.reach);
        reach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMap();
            }
        });
        return view;
    }

    private void openMap() {
        Uri uri = Uri.parse("geo:0,0?q=Govind Ballabh Pant Institute of Engineering & Technology, Ghurdauri, Ghurdauri, Pauri, Garhwal, Uttarakhand  246194");
        Intent intent = new Intent(Intent.ACTION_VIEW,uri);
        intent.setPackage("com.google.android.apps.maps");
        startActivity(intent);
    }

    private void setSliderViews() {

        for (int i=0; i<8; i++){
            DefaultSliderView sliderView = new DefaultSliderView(getContext());

            switch (i){
                case 0:
                    sliderView.setImageUrl("https://firebasestorage.googleapis.com/v0/b/gbpiet-13b5a.appspot.com/o/slider%2Fgbp.jpg?alt=media&token=2fc26c21-5306-4912-8e50-5e79e51a69f4");
                    break;
                case 1:
                    sliderView.setImageUrl("https://firebasestorage.googleapis.com/v0/b/gbpiet-13b5a.appspot.com/o/slider%2Ffaculty.jpg?alt=media&token=c7738d82-f937-432c-b8ce-21b44491791d");
                    break;
                case 2:
                    sliderView.setImageUrl("https://firebasestorage.googleapis.com/v0/b/gbpiet-13b5a.appspot.com/o/slider%2Fgoonj.jpg?alt=media&token=6c014ab3-22d3-40cc-8fc8-197e72df23b7");
                    break;
                case 3:
                    sliderView.setImageUrl("https://firebasestorage.googleapis.com/v0/b/gbpiet-13b5a.appspot.com/o/slider%2Flamp.jpg?alt=media&token=85ca2a21-7f03-418f-86aa-cafcf6550aa3");
                    break;
                case 4:
                    sliderView.setImageUrl("https://firebasestorage.googleapis.com/v0/b/gbpiet-13b5a.appspot.com/o/slider%2Fhod%20sir.jpg?alt=media&token=de30522f-fe4c-4f16-adeb-9b2ba211563a");
                    break;
                case 5:
                    sliderView.setImageUrl("https://firebasestorage.googleapis.com/v0/b/gbpiet-13b5a.appspot.com/o/slider%2FkailashHostel.jpg?alt=media&token=e9be8810-596c-4575-b57e-39bd514c3843");
                    break;
                case 6:
                    sliderView.setImageUrl("https://firebasestorage.googleapis.com/v0/b/gbpiet-13b5a.appspot.com/o/slider%2Ftechno.jpg?alt=media&token=ca645c12-70a0-4592-86cb-c03bde38ef7e");
                    break;
                case 7:
                    sliderView.setImageUrl("https://firebasestorage.googleapis.com/v0/b/gbpiet-13b5a.appspot.com/o/slider%2Faudy.jpg?alt=media&token=f6dacad4-53de-4372-ac67-b24f76c1bb8f");
                    break;
                case 8:
                    sliderView.setImageUrl("https://firebasestorage.googleapis.com/v0/b/gbpiet-13b5a.appspot.com/o/slider%2Fcloud.jpg?alt=media&token=0e37cfda-571a-418b-a351-4fd6b34d5e05");
                    break;
            }
            sliderView.setImageScaleType(ImageView.ScaleType.FIT_XY);

            sliderLayout.addSliderView(sliderView);
        }
    }
}