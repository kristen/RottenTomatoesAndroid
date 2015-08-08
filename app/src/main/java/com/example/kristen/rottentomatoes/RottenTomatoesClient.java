package com.example.kristen.rottentomatoes;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

/**
 * Created by kristen on 8/8/15.
 */
public class RottenTomatoesClient {
    private final String API_KEY = "95wsptzedh7yfpfx4r537zvu";
    private final String API_BASE_URL = "http://api.rottentomatoes.com/api/public/v1.0/";
    private AsyncHttpClient client;

    public RottenTomatoesClient() {
        this. client = new AsyncHttpClient();
    }

    private String getApiUrl(String relativeUrl) {
        return API_BASE_URL + relativeUrl;
    }

    public void getBoxOfficeMovies(JsonHttpResponseHandler handler, int limit) {
        String url = getApiUrl("lists/movies/box_office.json");
        RequestParams params = new RequestParams("apikey", API_KEY);
        params.put("limit", limit);
        client.get(url, params, handler);
    }

    public void getBoxOfficeMovies(JsonHttpResponseHandler handler) {
        getBoxOfficeMovies(handler, 50);
    }
}
