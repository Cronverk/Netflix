package com.example.hisaki.netflix.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.hisaki.netflix.R;
import com.example.hisaki.netflix.enteties.Movie;

/**
 * Created by hisaki on 25.10.2016.
 */

public class MovieContent extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.movie_fragment,container,false);
        ((TextView) layout.findViewById(R.id.summary)).setText(movie.getSummary());
        ((TextView) layout.findViewById(R.id.director)).setText(movie.getDirector());
        ((TextView) layout.findViewById(R.id.release)).setText(movie.getReleaseYear());
        ((TextView) layout.findViewById(R.id.rating)).setText(movie.getRating());

        return layout;
    }

    Movie movie;

    public void setMovie(Movie movie){
        this.movie = movie;
    }
}
