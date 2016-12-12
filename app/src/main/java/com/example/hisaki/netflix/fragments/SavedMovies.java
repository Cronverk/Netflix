package com.example.hisaki.netflix.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.LinearLayout;

import com.example.hisaki.netflix.MyApp;
import com.example.hisaki.netflix.R;
import com.example.hisaki.netflix.adapters.MovieAdapter;
import com.example.hisaki.netflix.adapters.MovieClickListener;
import com.example.hisaki.netflix.enteties.DaoSession;
import com.example.hisaki.netflix.enteties.Movie;

import java.util.List;

/**
 * Created by hisaki on 25.10.2016.
 */

public class SavedMovies extends Fragment {
    MovieAdapter adapter;
    List<Movie> movies;
    DaoSession session;

    MovieClickListener movieClick;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        session = ((MyApp)getActivity().getApplicationContext()).getDaoSession();
        movies = session.loadAll(Movie.class);

        movieClick = new MovieClickListener(getFragmentManager(),movies,false);

        View layout = inflater.inflate(R.layout.movies_list_fragment,container,false);
        GridView list = (GridView) layout.findViewById(R.id.list);
        View edit = layout.findViewById(R.id.option);
        edit.setVisibility(View.INVISIBLE);
        edit.setLayoutParams(new LinearLayout.LayoutParams(1,1));

        adapter = new MovieAdapter(getActivity(),R.layout.movie_item_fragment,movies);
        list.setOnItemClickListener(movieClick);
        list.setAdapter(adapter);
        return layout;
    }
}
