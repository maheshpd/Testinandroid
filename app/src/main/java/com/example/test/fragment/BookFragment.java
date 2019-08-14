package com.example.test.fragment;


import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.test.adapter.BookAdapter;
import com.example.test.model.BookModel;
import com.example.test.MySingleton;
import com.example.test.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class BookFragment extends Fragment {

    ArrayList<BookModel> list;
    Context context;

    BookAdapter adapter;
    RecyclerView book_rv;

    StringRequest request;

    String bookUrl = "https://www.wits-interactive.com/ftp/test/books.json";
    ProgressDialog dialog;
    public BookFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_book, container, false);
        list = new ArrayList<>();

        context = view.getContext();
        dialog = new ProgressDialog(context);
        getBookData();

        book_rv = view.findViewById(R.id.bookRV);
        adapter = new BookAdapter(context, list);
        LinearLayoutManager manager = new LinearLayoutManager(context);
        book_rv.setLayoutManager(manager);
        book_rv.setAdapter(adapter);
        book_rv.setHasFixedSize(true);
        return view;
    }

    private void getBookData() {
        dialog.setMessage("Please wait...");
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();
        request = new StringRequest(Request.Method.GET, bookUrl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);

                        JSONObject json = jsonObject.getJSONObject("BookData");
                        String book = json.getString("book");
                        JSONArray array = new JSONArray(book);

                        for (int i = 0; i <array.length() ; i++) {
                            JSONObject c = array.getJSONObject(i);
                            String booktitle = c.getString("Type");
                            String bookdes = c.getString("Title");
                            String booktype = c.getString("AutorLName1");
                            String bookImgUrl = c.getString("ThumbURL");

                            BookModel bookModel = new BookModel(bookImgUrl,booktitle,bookdes,booktype);
                            list.add(bookModel);
                            dialog.dismiss();
                    }


                    adapter.notifyDataSetChanged();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                dialog.dismiss();
                Toast.makeText(context, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        MySingleton.getInstance(context).addToRequestQueue(request);
    }



}
