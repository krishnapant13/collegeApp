package com.example.gbpiet.ui.about;

import android.content.Context;
import android.graphics.text.LineBreaker;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.viewpager.widget.PagerAdapter;

import com.example.gbpiet.R;

import java.util.List;

public class CourseAdapter extends PagerAdapter {

    private Context context;
    private List<CourseModel> list;

    public CourseAdapter(Context context, List<CourseModel> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view.equals(object);

    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View view = LayoutInflater.from(context).inflate(R.layout.course_item_layout,container,false);


        ImageView courseIcon;
        TextView courseTitle , courseDescription;

        courseIcon = view.findViewById(R.id.courseIcon);
        courseTitle = view.findViewById(R.id.courseTitle);
        courseDescription = view.findViewById(R.id.courseDescription);

        courseIcon.setImageResource(list.get(position).getImg());
        courseTitle.setText(list.get(position).getTitle());
        courseDescription.setText(list.get(position).getDescription());
        courseDescription.setJustificationMode(LineBreaker.JUSTIFICATION_MODE_INTER_WORD);

        container.addView(view,0);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }
}
