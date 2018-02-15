package com.poc.nycschools.volley;

import android.content.Context;

import com.android.volley.NetworkResponse;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;

public class AppVolleyApiManager {

    public static AppVolleyApiManager apiManager;
    private static RequestQueue requestQueue;


    public synchronized static void initVolley(Context context) {
        if (requestQueue == null) {
            requestQueue = Volley.newRequestQueue(context);
        }
    }

    public static AppVolleyApiManager instance() {
        if (apiManager == null) {
            apiManager = new AppVolleyApiManager();
        }
        return apiManager;
    }

    public synchronized static RequestQueue getRequestQueue() {
        return requestQueue;
    }


    public void getJsonArrayResponse(String url, final AppVolleyNetWorkResponse netWorkResponse) {

        if (url.contains(" ")) {
            url = url.replaceAll(" ", "%20");
        }

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(url, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                netWorkResponse.onSuccessResponse(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                String json = "";
                NetworkResponse response = error.networkResponse;
                if (response != null && response.data != null) {
                    json = new String(response.data);
                    netWorkResponse.onError("Error from volley");
                }
            }
        });

        jsonArrayRequest.setTag(url);
        getRequestQueue().add(jsonArrayRequest);
    }
}