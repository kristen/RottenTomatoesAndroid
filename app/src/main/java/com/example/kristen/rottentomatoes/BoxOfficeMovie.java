package com.example.kristen.rottentomatoes;

import android.text.TextUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by kristen on 8/8/15.
 */
public class BoxOfficeMovie implements Serializable {
    private static final long serialVersionUID = -8959832007991513854L;
    private String title;
    private int year;
    private String synopsis;
    private String posterUrl;
    private int criticsScore;
    private ArrayList<String> castList;
    private String criticsConsensus;
    private int audienceScore;

    public String getTitle() {
        return title;
    }

    public int getYear() {
        return year;
    }

    public String getSynopsis() {
        return synopsis;
    }

    public String getPosterUrl() {
        // http://resizing.flixster.com/gbCU2qc5Edq9j-eJWBL9VN0BmZA=/52x81/dkpu1ddg7pbsk.cloudfront.net/movie/11/19/07/11190760_ori.jpg
        return posterUrl;
    }

    public String getLargePosterUrl() {
        // http://content6.flixster.com/movie/11/19/07/11190760_ori.jpg
        int movie = posterUrl.indexOf("/movie");
        return "http://content6.flixster.com" + posterUrl.substring(movie, posterUrl.length());
    }

    public int getCriticsScore() {
        return criticsScore;
    }

    public String getCastList() {
        return TextUtils.join(", ", castList);
    }

    public String getCriticsConsensus() {
        return criticsConsensus;
    }

    public int getAudienceScore() {
        return audienceScore;
    }

    public static BoxOfficeMovie fromJson(JSONObject jsonObject) {
        BoxOfficeMovie b = new BoxOfficeMovie();
        try {
            b.title = jsonObject.getString("title");
            b.year = jsonObject.getInt("year");
            b.synopsis = jsonObject.getString("synopsis");
            b.posterUrl = jsonObject.getJSONObject("posters").getString("thumbnail");
            b.criticsScore = jsonObject.getJSONObject("ratings").getInt("critics_score");
            b.criticsConsensus = jsonObject.getString("critics_consensus");
            b.audienceScore = jsonObject.getJSONObject("ratings").getInt("audience_score");

            b.castList = new ArrayList<>();
            JSONArray abridgedCast = jsonObject.getJSONArray("abridged_cast");
            for (int i = 0; i < abridgedCast.length(); i++) {
                b.castList.add(abridgedCast.getJSONObject(i).getString("name"));
            }

        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }

        return b;
    }

    public static ArrayList<BoxOfficeMovie> fromJson(JSONArray jsonArray) {
        ArrayList<BoxOfficeMovie> movies = new ArrayList<>(jsonArray.length());

        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject moviesJson = null;
            try {
                moviesJson = jsonArray.getJSONObject(i);
            } catch (Exception e) {
                e.printStackTrace();
                continue;
            }

            BoxOfficeMovie movie = BoxOfficeMovie.fromJson(moviesJson);
            if (movie != null) {
                movies.add(movie);
            }
        }

        return movies;
    }
}
