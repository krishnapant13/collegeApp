package com.example.gbpiet.ui.notice;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.gbpiet.R;
import com.example.gbpiet.fullImageView;


import java.util.ArrayList;

public class NoticeAdapter extends RecyclerView.Adapter<NoticeAdapter.NoticeViewAdapter> {

    private Context context;
    private ArrayList<NoticeData> list;

    public NoticeAdapter(Context context, ArrayList<NoticeData> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public NoticeViewAdapter onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.newsfeed_item_layout, parent, false);
        return new NoticeViewAdapter(view);

    }

    @Override
    public void onBindViewHolder(@NonNull NoticeViewAdapter holder, int position) {

        NoticeData currentItem = list.get(position);

        holder.gb_text.setText(currentItem.getTitle());
        holder.date.setText(currentItem.getDate());
        holder.time.setText(currentItem.getTime());


        try {
            if (currentItem.getImage() != null)
                Glide.with(context).load(currentItem.getImage()).into(holder.gb_image);
        } catch (Exception e) {
            e.printStackTrace();
        }
        holder.gb_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, fullImageView.class);
                intent.putExtra("image", currentItem.getImage());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class NoticeViewAdapter extends RecyclerView.ViewHolder {


        private final TextView gb_text;
        private final TextView date;
        private final TextView time;
        private final ImageView gb_image;

        public NoticeViewAdapter(@NonNull View itemView) {
            super(itemView);

            gb_text = itemView.findViewById(R.id.gb_text);
            gb_image = itemView.findViewById(R.id.gb_image);
            date = itemView.findViewById(R.id.date);
            time = itemView.findViewById(R.id.time);
        }
    }
}
