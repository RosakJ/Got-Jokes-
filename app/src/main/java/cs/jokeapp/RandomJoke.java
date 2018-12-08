package cs.jokeapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
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

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import org.json.*;
import org.json.JSONObject;
import org.w3c.dom.Text;

import static com.android.volley.Request.Method.GET;

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
        Button homerandom = (Button) findViewById(R.id.backrandom);
        homerandom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RandomJoke.this, MainActivity.class));
            }
        });
    }

    /**
     * This sets up the random joke button, it calls the get joke function and that should work
     * itself out.
     */
    private void configureJokeButton() {
        Button randomJoke = (Button) findViewById(R.id.newrandom);
        randomJoke.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getJoke();
                System.out.println("Where even is this?");
            }
        });
    }

    /*
    private void getJoke() {
        try {
            URL url = new URL("http://api.icndb.com/jokes/random");
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("Get");

            Map<String, String> parameters = new HashMap<>();
            parameters.put("param1", "val");

            con.setDoOutput(true);
            DataOutputStream out = new DataOutputStream(con.getOutputStream());
            out.writeBytes(ParameterStringBuilder.getParamString(parameters));
            out.flush();
            out.close();

            //I'm not sure where this is supposed to go but I'm trying it right here:
            int status = con.getResponseCode();
            BufferedReader in = new BufferedReader( new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer content = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                content.append(inputLine);
            }
            in.close();
            setText(content.toString());

        } catch (Exception e) {
            setText("Uh Oh.");
        }


    }




    /*
    /**
     * This class gets the joke, it sets up the whole object request and volley system
     * I'm not sure this is the best way to do it, I saw some people make separate
     * java classes for this like a background function, but this should work...
     *
     * After it makes the request it calls the setText() method and passes it the joke
     * (I don't know how parsing or the json file works just yet, this is in theory)
     */

    private void getJoke() {
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        try {
            URL url = new URL("http://api.icndb.com/jokes/random");
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
            Request.Method.GET, //Request type
                "http://api.icndb.com/jokes/random", //URL string
                null, //put parameters here
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.i("Joke Site", "It's responding!" + response.toString());
                        try {
                            setText(response.getJSONObject("id").getString("joke"));
                            setText(response.toString());
                        } catch (JSONException e) {
                            Context context = getApplicationContext();
                            CharSequence text = "An error occurred pre!";
                            int duration = Toast.LENGTH_SHORT;
                            Toast toast = Toast.makeText(context, text, duration);
                            toast.show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(final VolleyError error) {
                        Log.i("Joke Site", "It's coming up with an error..." + error.toString());
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
        TextView jokeArea = (TextView) findViewById(R.id.jokeArea);
        jokeArea.setText(joke);
    }
}
