package com.example.hisaki.netflix.fragments;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hisaki.netflix.MyApp;
import com.example.hisaki.netflix.R;
import com.example.hisaki.netflix.enteties.DaoSession;
import com.example.hisaki.netflix.enteties.Movie;
import com.squareup.picasso.Picasso;

/**
 * Created by hisaki on 25.10.2016.
 */

public class MovieContent extends Fragment {

    Movie movie;
    DaoSession session;
    boolean menu_enable;



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        session = ((MyApp)getActivity().getApplicationContext()).getDaoSession();
        View layout = inflater.inflate(R.layout.movie_fragment,container,false);
        if(savedInstanceState != null) {
            movie = (Movie) savedInstanceState.get("movie");
            menu_enable = (boolean) savedInstanceState.get("menu_enable");
        }
            ((TextView) layout.findViewById(R.id.summary)).setText(movie.getSummary());
            ((TextView) layout.findViewById(R.id.director)).setText(movie.getDirector());
            ((TextView) layout.findViewById(R.id.release)).setText(movie.getReleaseYear());
            ((TextView) layout.findViewById(R.id.rating)).setText(movie.getRating());

            ImageView image = (ImageView) layout.findViewById(R.id.movie_poster);

            Picasso.with(getActivity().getApplicationContext())
                    .load(movie.getPoster())
                    .into(image);
        setHasOptionsMenu(true);
        return layout;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {

        if(menu_enable)
            inflater.inflate(R.menu.movie_save_menu, menu);
        else inflater.inflate(R.menu.movie_base_menu, menu);

        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.action_save)
            saveMovie();
        if (id == R.id.action_close)
            closeMovie();

        return super.onOptionsItemSelected(item);
    }

    public void saveMovie(){
        session.insertOrReplace(movie);
        getActivity().onBackPressed();
    }
    public void closeMovie(){
        getActivity().onBackPressed();
    }
    public void setMovie(Movie movie){
        this.movie = movie;
    }
    public void setMenuEnable(boolean enable){
        menu_enable = enable;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable("movie", movie);
        outState.putBoolean("menu_enable", menu_enable);
    }
}
