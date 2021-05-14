package com.example.covid_tracker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.covid_tracker.databinding.ActivityStateWiseBinding;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class State_wise extends AppCompatActivity {

    ActivityStateWiseBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityStateWiseBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        binding.recyclerView.setLayoutManager(linearLayoutManager);

        ArrayList<StateModel> list = new ArrayList<>();
        RecyclerAdapter adapter = new RecyclerAdapter(list, State_wise.this);
        binding.recyclerView.setAdapter(adapter);

        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "https://api.apify.com/v2/key-value-stores/toDWvRj1JpTXiM8FF/records/LATEST?disableRedirect=true";

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray array = response.getJSONArray("regionData");
                            for(int i=0; i<array.length(); i++){
                                JSONObject object = array.getJSONObject(i);
                                String region = object.getString("region");
                                String active = object.getString("activeCases");
                                String rev = object.getString("recovered");
                                String dead = object.getString("deceased");
                                String total = object.getString("totalInfected");
                                list.add(new StateModel(region,active,rev,dead,total));
                            }
                            adapter.notifyDataSetChanged();
                            binding.progressBAR.setVisibility(View.GONE);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("Error", "Error123");
                    }
                });
        queue.add(jsonObjectRequest);

    }
}