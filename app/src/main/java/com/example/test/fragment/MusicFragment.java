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
import com.example.test.adapter.MusicAdapter;
import com.example.test.MySingleton;
import com.example.test.R;
import com.example.test.model.MusicModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class MusicFragment extends Fragment {

    ArrayList<MusicModel> list;
    Context context;

    MusicAdapter adapter;
    RecyclerView music_rv;

    StringRequest request;

    String musicUrl = "https://www.wits-interactive.com/ftp/test/music.json";
    ProgressDialog dialog;

    public MusicFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_music, container, false);

        list = new ArrayList<>();

        context = view.getContext();
        dialog = new ProgressDialog(context);
        getMusicData();

        music_rv = view.findViewById(R.id.musicRV);
        adapter = new MusicAdapter(context, list);
        LinearLayoutManager manager = new LinearLayoutManager(context);
        music_rv.setLayoutManager(manager);
        music_rv.setAdapter(adapter);
        music_rv.setHasFixedSize(true);
        return view;
    }

    private void getMusicData() {
        dialog.setMessage("Please wait...");
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();
        request = new StringRequest(Request.Method.GET, musicUrl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);

                    JSONObject json = jsonObject.getJSONObject("iTunes");
                    String itune = json.getString("itune");
                    JSONArray array = new JSONArray(itune);

                    for (int i = 0; i <array.length() ; i++) {
                        JSONObject c = array.getJSONObject(i);
                        String booktitle = c.getString("Artist");
                        String bookdes = c.getString("Title");
                        String booktype = c.getString("FolgeNo");
                        String bookImgUrl = c.getString("ThumbURL");

                        MusicModel bookModel = new MusicModel(bookImgUrl,booktitle,bookdes,booktype);
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
