package com.example.test.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.test.R;
import com.example.test.model.MusicModel;

import java.util.ArrayList;

public class MusicAdapter extends RecyclerView.Adapter<MusicAdapter.MyMusicViewHolder> {

    Context context;
    ArrayList<MusicModel> list;


    public MusicAdapter(Context context, ArrayList<MusicModel> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyMusicViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.music_item,parent,false);
        return new MyMusicViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyMusicViewHolder holder, int position) {
        MusicModel im = list.get(position);
        holder.musictitle.setText(im.getBooktitle());
        holder.musicdesc.setText(im.getBookdes());
        holder.musictype.setText(im.getBooktype());

        String imgeUrl = im.getBookimgUrl();

        Glide.with(context).load(imgeUrl).into(holder.musicImgUrl);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyMusicViewHolder extends RecyclerView.ViewHolder {
        private TextView musictitle, musicdesc, musictype;
        private ImageView musicImgUrl;
        private MyMusicViewHolder(@NonNull View itemView) {
            super(itemView);

            musictitle =itemView.findViewById(R.id.musictitle);
            musicdesc =itemView.findViewById(R.id.musicdesc);
            musictype =itemView.findViewById(R.id.musictype);
            musicImgUrl =itemView.findViewById(R.id.musicimg);
        }
    }
}
