package cs.jokeapp;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

import org.json.JSONException;
import org.json.JSONObject;

public class CategoryTwo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_two);

        configureHomeButton();
        configureCategoryBack();
        configureJokeButton();
    }

    /**
     * Configures the home button to the MainActivity.
     */
    private void configureHomeButton() {
        Button home = findViewById(R.id.homeTwo);
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CategoryTwo.this, MainActivity.class));
            }
        });
    }

    /**
     * Configures the button back to the categories page.
     */
    private void configureCategoryBack() {
        Button categoryBack = findViewById(R.id.categoryHomeTwo);
        categoryBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CategoryTwo.this, CategoriesActivity.class));
            }
        });
    }

    /**
     * This sets up the random joke button, it calls the get joke function.
     */
    private void configureJokeButton() {
        Button newCategoryTwoJoke = findViewById(R.id.jokeCategoryTwo);
        newCategoryTwoJoke.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getJoke();
            }
        });
    }

    //=========================== CATEGORY TWO CONTAINS CHUCK NORRIS JOKES =============================

    /**
     * This class gets the joke, it sets up a volley request system and requests the
     *
     * After it makes the request it calls the setText() method and passes it the joke
     *
     */
    private void getJoke() {
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        try {
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                    Request.Method.GET,
                    "http://api.icndb.com/jokes/random",
                    null,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                String joke = response.getJSONObject("value").getString("joke");
                                setText(joke);
                            } catch (JSONException e) {
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
        }
    }

    /**
     * This should set the text to whatever is passed to it.
     * @param joke This is the text passed to it, preferably a joke.
     */
    private void setText(String joke) {
        TextView jokeArea = findViewById(R.id.jokeTextTwo);
        jokeArea.setText(joke);
    }
}
