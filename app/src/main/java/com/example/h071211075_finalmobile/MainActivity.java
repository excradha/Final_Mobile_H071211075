package com.example.h071211075_finalmobile;


import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

public class MainActivity extends AppCompatActivity {

    ImageView movie_iv, series_iv, favorites_iv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        movie_iv = findViewById(R.id.film);
        series_iv = findViewById(R.id.show);
        favorites_iv = findViewById(R.id.favorite);

        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager
                .beginTransaction()
                .replace(R.id.frame_container, new MovieFragment(),
                        MovieFragment.class.getSimpleName())
                .addToBackStack(null)
                .commit();

        movie_iv.setOnClickListener(btn -> {
            MovieFragment movieFragment = new MovieFragment();
            fragmentManager
                    .beginTransaction()
                    .replace(R.id.frame_container, movieFragment,
                            MovieFragment.class.getSimpleName())
                    .addToBackStack(null)
                    .commit();
        });

        series_iv.setOnClickListener(btn -> {
            TvShowFragment tvShow = new TvShowFragment();
            fragmentManager
                    .beginTransaction()
                    .replace(R.id.frame_container, tvShow,
                            TvShowFragment.class.getSimpleName())
                    .addToBackStack(null)
                    .commit();
        });

        favorites_iv.setOnClickListener(btn -> {
            FavoriteFragment favorite = new FavoriteFragment();
            fragmentManager
                    .beginTransaction()
                    .replace(R.id.frame_container, favorite,
                            FavoriteFragment.class.getSimpleName())
                    .addToBackStack(null)
                    .commit();
        });
    }

}