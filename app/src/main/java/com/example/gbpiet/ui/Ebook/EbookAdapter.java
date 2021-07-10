package com.example.gbpiet.ui.Ebook;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gbpiet.R;
import com.example.gbpiet.ui.notice.NoticeData;

import java.util.ArrayList;
import java.util.List;

public class EbookAdapter extends RecyclerView.Adapter<EbookAdapter.EbookViewHolder> {

    private Context context;
    private List<Edata> list;

    public EbookAdapter(Context context, List<Edata> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public EbookViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.ebook_item_layout,parent,false);

        return new EbookViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EbookViewHolder holder,final int position) {


        Edata currentItem = list.get(position);

        holder.ebook_date.setText(currentItem.getDate());
        holder.ebook_time.setText(currentItem.getTime());


        holder.eBook_name.setText(list.get(position).getDocumentTitle());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, list.get(position).getDocumentTitle(), Toast.LENGTH_SHORT).show();
                Intent intent  = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(list.get(position).getDocumentUrl()));
                context.startActivity(intent);
                Toast.makeText(context, "Downloading...", Toast.LENGTH_SHORT).show();
            }
        });


        holder.ebook_download.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent  = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(list.get(position).getDocumentUrl()));
                context.startActivity(intent);
                Toast.makeText(context, "Downloading...", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void Filterlist(ArrayList<Edata> filterlist) {
        list = filterlist;
        notifyDataSetChanged();
    }

    public class EbookViewHolder extends RecyclerView.ViewHolder {

        private TextView eBook_name;
        private ImageView ebook_download;
        private final TextView ebook_date;
        private final TextView ebook_time;

        public EbookViewHolder(@NonNull View itemView) {
            super(itemView);

            ebook_download = itemView.findViewById(R.id.ebook_download);
            eBook_name = itemView.findViewById(R.id.eBook_name);

            ebook_date = itemView.findViewById(R.id.ebook_date);
            ebook_time = itemView.findViewById(R.id.ebook_time);
        }
    }
}
