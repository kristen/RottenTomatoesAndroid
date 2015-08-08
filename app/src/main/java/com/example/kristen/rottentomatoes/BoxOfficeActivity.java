package com.example.kristen.rottentomatoes;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.loopj.android.http.JsonHttpResponseHandler;

import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class BoxOfficeActivity extends Activity {
    private ListView lvMovies;
    private BoxOfficeMoviesAdapter adapterMovies;
    RottenTomatoesClient client;
    public static final String MOVIE_DETAIL_KEY = "movie";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_box_office);

        lvMovies = (ListView) findViewById(R.id.lvMovies);
        ArrayList<BoxOfficeMovie> aMovies = new ArrayList<>();
        adapterMovies = new BoxOfficeMoviesAdapter(this, aMovies);
        lvMovies.setAdapter(adapterMovies);

        fetchBoxOfficeMovies();
        setupMovieSelectedListener();
    }

    private void fetchBoxOfficeMovies() {
        client = new RottenTomatoesClient();
        client.getBoxOfficeMovies(new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject responseBody) {
                JSONArray items = null;
                try {
                    items = responseBody.getJSONArray("movies");
                    ArrayList<BoxOfficeMovie> movies = BoxOfficeMovie.fromJson(items);

                    for (BoxOfficeMovie movie : movies) {
                        adapterMovies.add(movie);
                    }
                    adapterMovies.notifyDataSetChanged();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void setupMovieSelectedListener() {
        lvMovies.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(BoxOfficeActivity.this, BoxOfficeDetailActivity.class);
                i.putExtra(MOVIE_DETAIL_KEY, adapterMovies.getItem(position));
                startActivity(i);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_box_office, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
