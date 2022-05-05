package com.example.networking;

import android.os.Bundle;
import android.util.Log;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingDeque;

@SuppressWarnings("FieldCanBeLocal")
public class MainActivity extends AppCompatActivity implements JsonTask.JsonTaskListener {


    private final String JSON_URL = "https://mobprog.webug.se/json-api?login=brom";
    private final String JSON_FILE = "mountains.json";
    private ArrayList<Mountain> mountainList;
    private RecyclerViewAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mountainList = new ArrayList<>();
        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        adapter = new RecyclerViewAdapter(mountainList);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);



        new JsonTask(MainActivity.this).execute(JSON_URL);
    }

    @Override
    public void onPostExecute(String json) {
        Log.d("MainActivity", json);
        Gson gson = new Gson();
        Type type = new TypeToken<List<Mountain>>(){}.getType();
        List<Mountain> tmp = gson.fromJson(json,type);
        mountainList.addAll(tmp);
        adapter.notifyDataSetChanged();
        Log.d("MainActivity", String.valueOf(mountainList.size()));
        Log.d("MainActivity",mountainList.get(0).getName());

    }

}
