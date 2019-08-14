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
import com.example.test.model.BookModel;

import java.util.ArrayList;

public class BookAdapter extends RecyclerView.Adapter<BookAdapter.MyBookViewHolder> {

    Context context;
    ArrayList<BookModel> list;


    public BookAdapter(Context context, ArrayList<BookModel> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyBookViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.book_item,parent,false);
        return new MyBookViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyBookViewHolder holder, int position) {
        BookModel im = list.get(position);
        holder.bookTitle.setText(im.getBooktitle());
        holder.bookdesc.setText(im.getBookdes());
        holder.booktype.setText(im.getBooktype());

        String imgeUrl = im.getBookimgUrl();

        Glide.with(context).load(imgeUrl).into(holder.gameImgUrl);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyBookViewHolder extends RecyclerView.ViewHolder {
        private TextView bookTitle, bookdesc, booktype;
        private ImageView gameImgUrl;
        private MyBookViewHolder(@NonNull View itemView) {
            super(itemView);

            bookTitle =itemView.findViewById(R.id.booktitle);
            bookdesc =itemView.findViewById(R.id.bookdesc);
            booktype =itemView.findViewById(R.id.booktype);
            gameImgUrl=itemView.findViewById(R.id.bookimg);
        }
    }
}
