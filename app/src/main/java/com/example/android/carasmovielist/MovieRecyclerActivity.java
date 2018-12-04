package com.example.android.carasmovielist;

// API ENDPOINT: https://data.sfgov.org/resource/wwmu-gmzc.json
// use this url- https://data.sfgov.org/api/views/yitu-d5am/rows.json?accessType=DOWNLOAD
//Have to do a little more with the lifecycle and do an OnClickListener


import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MovieRecyclerActivity extends AppCompatActivity {

    private static final String URL_DATA = "https://data.sfgov.org/resource/wwmu-gmzc.json";
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;

    private ProgressDialog pDialog;
    private List<ListItem> listItems;
    private List<MovieFact> movieFacts;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_recycler);

        recyclerView = findViewById(R.id.recyclerView);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(manager);
        recyclerView.setHasFixedSize(true);



        listItems = new ArrayList<>();
        movieFacts = new ArrayList<>();

        loadRecyclerView();

    }


    /**
    * Method to fetch json using Volley
    */
    private void loadRecyclerView(){
        pDialog = new ProgressDialog(MovieRecyclerActivity.this);
        pDialog.setMessage("Loading...");
        pDialog.setCancelable(false);
        pDialog.show();

        // This comes from Volley
        StringRequest stringRequest = new StringRequest(Request.Method.GET,
                URL_DATA,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        pDialog.dismiss();
                        try {
                            JSONArray json = new JSONArray(response);

                            //May have to parse more here
                            for(int i = 0; i < json.length(); i++){
                                JSONObject object = json.getJSONObject(i);
                                String title = "N/A";
                                String year = "N/A";
                                String location = "N/A";
                                String actor1 = "N/A";
                                String actor2 = "N/A";
                                String actor3 = "N/A";
                                String director = "N/A";
                                String writer = "N/A";
                                String prod_company = "N/A";
                                String distributor = "N/A";
                                String fun_fact = "N/A";

                                if(object.has("title")){
                                    title = object.getString("title");
                                }
                                if(object.has("release_year")){
                                    year = object.getString("release_year");
                                }
                                if(object.has("locations")){
                                    location = object.getString("locations");
                                }
                                if(object.has("actor_1")){
                                    actor1 = object.getString("actor_1");
                                }
                                if(object.has("actor_2")){
                                    actor2 = object.getString("actor_2");
                                }
                                if(object.has("actor_3")){
                                    actor3 = object.getString("actor_3");
                                }
                                if(object.has("director")){
                                    director = object.getString("director");
                                }
                                if(object.has("writer")){
                                    writer = object.getString("writer");
                                }
                                if(object.has("production_company")){
                                    prod_company = object.getString("production_company");
                                }
                                if(object.has("distributor")){
                                    distributor = object.getString("distributor");
                                }
                                if(object.has("fun_facts")){
                                    fun_fact = object.getString("fun_facts");
                                }


                                ListItem item = new ListItem(
                                        title,
                                        year,
                                        location
                                );
                                listItems.add(item);

                                MovieFact facts = new MovieFact(
                                        title, year, location,
                                        actor1, actor2, actor3,
                                        director, writer, prod_company,
                                        distributor, fun_fact
                                );
                                movieFacts.add(facts);


                            }
                            adapter = new MovieRecyclerAdapter(listItems, movieFacts, getApplicationContext());
                            recyclerView.setAdapter(adapter);



                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        pDialog.dismiss();
                        Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show();

                    }
                });
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
}
