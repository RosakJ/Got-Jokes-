package cs.jokeapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.*;
import org.json.JSONObject;

public class RandomJoke extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_random_joke);

        configureJokeButton();
        configureHomeButton();
    }


    /**
     * This configures the home button to take you back to the home page.
     */
    private void configureHomeButton() {
        Button homeRandom = findViewById(R.id.backrandom);
        homeRandom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RandomJoke.this, MainActivity.class));
            }
        });
    }

    /**
     * This sets up the random joke button, it calls the get joke function.
     */
    private void configureJokeButton() {
        Button randomJoke = findViewById(R.id.newrandom);
        randomJoke.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getJoke();
            }
        });
    }

    /**
     * This class gets the joke, it sets up a volley request system and requests the
     *
     * After it makes the request it calls the setText() method and passes it the joke
     *
     */
    private void getJoke() {
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        try {
            //Creates a new request for a JSONObject
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
            Request.Method.GET, //Request type
                "http://api.icndb.com/jokes/random", //URL String
                null, //Put parameters here
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            //If this goes through then the app worked!
                            //This will parse the JSONObject to extract the joke and set the text.
                            String joke = response.getJSONObject("value").getString("joke");
                            setText(joke);
                        } catch (JSONException e) {
                            //If an error occurs this will make a toast or a little pop up message
                            //The pop up message will last for a bit and then go away, it's called
                            //a toast
                            //This error message will occur if the parsing fails or another JSON
                            //Exception occurs
                            Context context = getApplicationContext();
                            CharSequence text = "An Error Occurred!";
                            int duration = Toast.LENGTH_LONG;
                            Toast toast = Toast.makeText(context, text, duration);
                            toast.show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(final VolleyError error) {
                        //This will come up with a toast if an error in the Volly itself occurs.
                        setText(error.toString());
                        Context context = getApplicationContext();
                        CharSequence text = "An error occurred in post!";
                        int duration = Toast.LENGTH_SHORT;
                        Toast toast = Toast.makeText(context, text, duration);
                        toast.show();
                    }
            });
            requestQueue.add(jsonObjectRequest);
        } catch (Exception e) {
            e.printStackTrace();
            Log.i("Joke Site", "Catch block in API getJoke()");
        }
    }

    /**
     * This should set the text to whatever is passed to it.
     * @param joke This is the text passed to it, preferably a joke.
     */
    private void setText(String joke) {
        TextView jokeArea = findViewById(R.id.jokeArea);
        jokeArea.setText(joke);
    }
}
