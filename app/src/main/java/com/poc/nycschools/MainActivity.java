package com.poc.nycschools;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.poc.nycschools.adapter.NYCListAdapter;
import com.poc.nycschools.model.NYCInfo;
import com.poc.nycschools.volley.AppVolleyApiManager;
import com.poc.nycschools.volley.AppVolleyNetWorkResponse;

import org.json.JSONArray;

import java.lang.reflect.Type;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    /** API CALL TO GET LIST OF SCHOOLS**/
    public static final String API_NYC_SCHOOL = "https://data.cityofnewyork.us/resource/97mf-9njv.json";

    /*
    UI Elements
     */
    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        mRecyclerView.addItemDecoration(new DividerItemDecoration(MainActivity.this, DividerItemDecoration.VERTICAL));
        mRecyclerView.setClipToPadding(false);

        // Init. volley and make api call to get School List
        AppVolleyApiManager.initVolley(getApplicationContext());
        AppVolleyApiManager.instance().getJsonArrayResponse(API_NYC_SCHOOL, new AppVolleyNetWorkResponse() {
                    @Override
                    public void onError(String error) {
                        Log.e("response", "Error in response " + error);
                    }

                    @Override
                    public void onSuccessResponse(JSONArray response) {
                        Log.e("response", "response Success" + response.toString());


                        Type collectionType = new TypeToken<List<NYCInfo>>() {
                        }.getType();

                        List<NYCInfo> list = new Gson().fromJson(response.toString(), collectionType);

                        NYCListAdapter adapter = new NYCListAdapter(list, new NYCListAdapter.OnOptionClickListner() {
                            @Override
                            public void onOption(NYCInfo info, String option, View view) {
                                // call back from adaptor
                                if (option.equalsIgnoreCase("details")) {
                                    Intent intent = new Intent(getApplicationContext(), DetailActivity.class);
                                    intent.putExtra("info", info);
                                    startActivity(intent);
                                }
                            }
                        });
                        mRecyclerView.setAdapter(adapter);
                    }
                });
    }
}