package cs.jokeapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import static com.android.volley.Request.Method.GET;

public class RandomJoke extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_random_joke);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        String URL = "https://api.icndb.com";
        RequestQueue requestQueue = Volley.newRequestQueue(this);

        JsonObjectRequest objectRequest = new JsonObjectRequest(
                Request.Method.GET, //Request type
                URL, //URL string
                null, //put parameters here
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.i("Joke Site", "It's responding!");
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.i("Joke Site", "It's coming up with an error...");
                    }
                }
        );
        requestQueue.add(objectRequest)

        configureHomeButton();
    }

    private void configureHomeButton() {
        Button homerandom = (Button) findViewById(R.id.backrandom);
        homerandom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RandomJoke.this, MainActivity.class));
            }
        });
    }

    private void configureJokeButton() {
        Button randomJoke = (Button) findViewById(R.id.randomjoke);
        randomJoke.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    /*
    String URL = "https://api.icndb.com";
        RequestQueue requestQueue = Volley.newRequestQueue(this);

        JsonObjectRequest objectRequest = new JsonObjectRequest(
                Request.Method.GET, //Request type
                URL, //URL string
                null, //put parameters here
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.i("Joke Site", "It's responding!");
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.i("Joke Site", "It's coming up with an error...");
                    }
                }
        );
        requestQueue.add(objectRequest)
     */

}
