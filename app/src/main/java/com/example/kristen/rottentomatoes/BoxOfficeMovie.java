package com.example.kristen.rottentomatoes;

import android.text.TextUtils;

import java.util.ArrayList;

/**
 * Created by kristen on 8/8/15.
 */
public class BoxOfficeMovie {
    private String title;
    private int year;
    private String synopsis;
    private String posterUrl;
    private int criticsScore;
    private ArrayList<String> castList;

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
        return posterUrl;
    }

    public int getCriticsScore() {
        return criticsScore;
    }

    public String getCastList() {
        return TextUtils.join(", ", castList);
    }
}
