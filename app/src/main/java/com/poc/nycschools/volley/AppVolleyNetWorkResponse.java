package com.poc.nycschools.volley;

import org.json.JSONArray;

public interface AppVolleyNetWorkResponse {

	public void onError(String error);

	void onSuccessResponse(JSONArray response);
}
