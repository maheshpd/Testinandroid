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
import com.example.test.MySingleton;
import com.example.test.R;
import com.example.test.adapter.GameAdapter;
import com.example.test.model.GameModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class GameFragment extends Fragment {

    ArrayList<GameModel> list;
    Context context;

    GameAdapter adapter;
    RecyclerView game_rv;

    StringRequest request;

    String gameurl = "https://www.wits-interactive.com/ftp/test/games.json";
    ProgressDialog dialog;

    public GameFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_game, container, false);

        list = new ArrayList<>();

        context = view.getContext();
        dialog = new ProgressDialog(context);
        getGameData();

        game_rv = view.findViewById(R.id.gameRV);
        adapter = new GameAdapter(context, list);
        LinearLayoutManager manager = new LinearLayoutManager(context);
        game_rv.setLayoutManager(manager);
        game_rv.setAdapter(adapter);
        game_rv.setHasFixedSize(true);
        return view;
    }

    private void getGameData() {
        dialog.setMessage("Please wait...");
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();
        request = new StringRequest(Request.Method.GET, gameurl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);

                    JSONObject json = jsonObject.getJSONObject("GamesData");
                    String games = json.getString("game");
                    JSONArray array = new JSONArray(games);

                    for (int i = 0; i <array.length() ; i++) {
                        JSONObject c = array.getJSONObject(i);
                        String gameShortname = c.getString("ShortName");
                        String gameTitle = c.getString("Title");
                        String gamePlatform = c.getString("Platform");
                        String gameImgUrl = c.getString("ThumbURL");

                        GameModel gameModel = new GameModel(gameShortname,gameTitle,gamePlatform,gameImgUrl);
                        list.add(gameModel);
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
