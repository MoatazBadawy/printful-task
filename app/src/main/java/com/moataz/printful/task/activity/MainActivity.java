package com.moataz.printful.task.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.moataz.printful.task.R;
import com.moataz.printful.task.adapter.RecyclerViewAdapter;
import com.moataz.printful.task.model.Data;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    RecyclerViewAdapter imagesAdapter;
    List<Data> imagesList;
    private static String JSON_KEY = "https://run.mocky.io/v3/463e89c8-7053-469a-b801-afc7e383b048";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //make the status bar white with black icons
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        recyclerView = findViewById(R.id.recyclerView_images);
        imagesList = new ArrayList<>();
        extractContent();
    }

    public void extractContent() {
        RequestQueue queue = Volley.newRequestQueue(this);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, JSON_KEY,
                null, response -> {
                    for (int i =0; i<response.length(); i++){
                        try {
                            JSONObject jsonObject = response.getJSONObject(i);

                            Data images = new Data();
                            images.setTitle(jsonObject.getString("title"));
                            images.setImageURL(jsonObject.getString("imageURL"));
                            images.setDescription(jsonObject.getString("description"));
                            imagesList.add(images);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                    recyclerView.setLayoutManager(new LinearLayoutManager(this));
                    imagesAdapter = new RecyclerViewAdapter(this,imagesList);
                    recyclerView.setAdapter(imagesAdapter);
                    // Calculate the items and auto-fit it on the screen
                    int mNoOfColumns = RecyclerViewAdapter.Utility.calculateNoOfColumns(this, 150);
                    recyclerView.setLayoutManager(new GridLayoutManager(this, mNoOfColumns));
                }, error -> Log.d("tag", "onErrorResponse: " + error.getMessage()));
        queue.add(jsonArrayRequest);
    }
}