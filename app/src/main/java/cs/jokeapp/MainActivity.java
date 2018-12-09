package cs.jokeapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        configureRandomButton();
        configureCategoryButton();
    }

    /**
     * Configures the button to go to the random page.
     */
    private void configureRandomButton() {
        Button randomButton = (Button) findViewById(R.id.randomjoke);
        randomButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, RandomJoke.class));
            }
        });
    }

    /**
     * Configures the button to go to the categories page.
     */
    private void configureCategoryButton() {
        Button nameButton = (Button) findViewById(R.id.inputjoke);
        nameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, CategoriesActivity.class));
            }
        });
    }
}
