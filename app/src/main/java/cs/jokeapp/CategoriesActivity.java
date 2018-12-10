package cs.jokeapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

public class CategoriesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories);

        configureHomeButton();
        configureCategoryOneButton();
        configureCategoryTwoButton();
        configureCategoryThreeButton();
    }

    /**
     * This configures the home button to home page.
     */
    private void configureHomeButton() {
        Button homeCategories = findViewById(R.id.home);
        homeCategories.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CategoriesActivity.this, MainActivity.class));
            }
        });
    }

    /**
     * This configures the category one button to take you back to the home CategoryOne page.
     */
    private void configureCategoryOneButton() {
        Button categoryOne = findViewById(R.id.category_one);
        categoryOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CategoriesActivity.this, CategoryOne.class)); // change class
            }
        });
    }
    /**
     * This configures the category two button to take you back to the home CategoryTwo page.
     */
    private void configureCategoryTwoButton() {
        Button categoryTwo = findViewById(R.id.category_two);
        categoryTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CategoriesActivity.this, CategoryTwo.class)); // change class
            }
        });
    }

    /**
     * This configures the category three button to take you back to the CategoryThree page.
     */
    private void configureCategoryThreeButton() {
        Button categoryThree = findViewById(R.id.category_three);
        categoryThree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CategoriesActivity.this, CategoryThree.class)); // change class
            }
        });
    }


}
