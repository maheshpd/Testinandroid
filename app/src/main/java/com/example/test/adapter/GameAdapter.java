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
import com.example.test.model.GameModel;

import java.util.ArrayList;

public class GameAdapter extends RecyclerView.Adapter<GameAdapter.MyGameViewHolder> {

    Context context;
    ArrayList<GameModel> list;


    public GameAdapter(Context context, ArrayList<GameModel> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyGameViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.game_item,parent,false);
        return new MyGameViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyGameViewHolder holder, int position) {
        GameModel im = list.get(position);
        holder.Short_Name_txt.setText(im.getShort_name());
        holder.Title.setText(im.getTitle());
        holder.Platform.setText(im.getPlatform());

        String imgeUrl = im.getThunburl();

        Glide.with(context).load(imgeUrl).into(holder.gameImgUrl);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyGameViewHolder extends RecyclerView.ViewHolder {
        private TextView Short_Name_txt,Title,Platform;
        private ImageView gameImgUrl;
        private MyGameViewHolder(@NonNull View itemView) {
            super(itemView);

            Short_Name_txt=itemView.findViewById(R.id.gametitle);
            Title=itemView.findViewById(R.id.gamedesc);
            Platform=itemView.findViewById(R.id.gametype);
            gameImgUrl=itemView.findViewById(R.id.gameimg);
        }
    }
}
