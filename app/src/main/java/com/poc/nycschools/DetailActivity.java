package com.poc.nycschools;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AutoCompleteTextView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.poc.nycschools.model.NYCInfo;
import com.poc.nycschools.model.SatScore;
import com.poc.nycschools.volley.AppVolleyApiManager;
import com.poc.nycschools.volley.AppVolleyNetWorkResponse;

import org.json.JSONArray;

import java.lang.reflect.Type;
import java.util.List;

public class DetailActivity extends AppCompatActivity {

    /** API  TO GET SAT SCORE INFO. PASS School DBN ID TO GET DETAILS> **/
    private static final  String DBN_INFO ="https://data.cityofnewyork.us/resource/734v-jeq5.json?dbn=%s";

    // Selected NYC INFO from List
    private NYCInfo info;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        getSupportActionBar().setTitle(R.string.title_info);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        info = (NYCInfo) getIntent().getSerializableExtra("info");

        // Set NYC Info to UI
        ((AutoCompleteTextView) findViewById(R.id.act_scl_name)).setText(info.getName());
        ((AutoCompleteTextView) findViewById(R.id.act_scl_count)).setText(info.getTotalStudent());
        ((AutoCompleteTextView) findViewById(R.id.act_scl_ste)).setText(info.getStateCode());
        ((AutoCompleteTextView) findViewById(R.id.act_scl_city)).setText(info.getCity());
        ((AutoCompleteTextView) findViewById(R.id.act_scl_mail)).setText(info.getEmail());
        ((AutoCompleteTextView) findViewById(R.id.act_scl_addr)).setText(info.getAddress());
        ((AutoCompleteTextView) findViewById(R.id.act_scl_mobile)).setText(info.getMobile());
        ((AutoCompleteTextView) findViewById(R.id.act_scl_web)).setText(info.getWebsite());
        ((AutoCompleteTextView) findViewById(R.id.act_scl_zip)).setText(info.getZipCode());

        getStaScores(info.getDbn());
    }

    /**
     * Make API and get SAT Score Info.
     * @param dbn
     */
    private void getStaScores(String dbn) {

        AppVolleyApiManager.initVolley(getApplicationContext());

        AppVolleyApiManager.instance().getJsonArrayResponse(String.format(DBN_INFO, dbn), new AppVolleyNetWorkResponse() {
            @Override
            public void onError(String error) {

            }

            @Override
            public void onSuccessResponse(JSONArray response) {


                Type collectionType = new TypeToken<List<SatScore>>() {}.getType();

                List<SatScore> list = new Gson().fromJson(response.toString(), collectionType);

                if(list!=null && list.size()>0){
                    SatScore score = list.get(0);
                    ((TextView) findViewById(R.id.test_takers)).setText("Test Takers Avg. : "+score.getNumOfSatTestTakers());
                    ((TextView) findViewById(R.id.critical_reading_avg_score)).setText("Critical Reading Avg. : "+score.getSatCriticalReadingAvgScore());
                    ((TextView) findViewById(R.id.math_avg_score)).setText("Math Avg. Score : "+score.getSatMathAvgScore());
                    ((TextView) findViewById(R.id.writing_avg_score)).setText("Writing Avg. Score : "+score.getSatWritingAvgScore());
                }

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.detail_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
            case R.id.action_direction:
                Uri gmmIntentUri = Uri.parse("google.navigation:q=" + info.getLat() + "," + info.getLng() + "&avoid=tf&mode=d");
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                mapIntent.setPackage("com.google.android.apps.maps");
                startActivity(mapIntent);
                break;
            case R.id.action_url:
                String url = info.getWebsite();
                if (!url.startsWith("http://") && !url.startsWith("https://"))
                    url = "http://" + url;
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                startActivity(browserIntent);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}