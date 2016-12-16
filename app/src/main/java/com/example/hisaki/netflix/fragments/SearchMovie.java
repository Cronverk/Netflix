package com.example.hisaki.netflix.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.hisaki.netflix.MyApp;
import com.example.hisaki.netflix.R;
import com.example.hisaki.netflix.adapters.MovieAdapter;
import com.example.hisaki.netflix.adapters.MovieClickListener;
import com.example.hisaki.netflix.enteties.Movie;
import com.example.hisaki.netflix.retrofit.NetflixApi;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by hisaki on 25.10.2016.
 */

public class SearchMovie extends Fragment {
    MovieAdapter adapter;
    List<Movie> movies;
    int         type;

    NetflixApi  api;

    TextView errorText;

    Callback<Movie> movieCall = new Callback<Movie>() {
        @Override
        public void onResponse(Call<Movie> call, Response<Movie> response) {
            if(response.code() == 200){

                Movie movie = response.body();
                List<Movie> movies = new ArrayList<>();
                movies.add(movie);
                updateAdapter(movies);
                setErrorMessage("", View.GONE);
            }
            else {
                setErrorMessage("Sorry! We couldn't find a movie with title…", View.VISIBLE);
                updateAdapter(new ArrayList<Movie>());
            }
        }

        @Override
        public void onFailure(Call<Movie> call, Throwable t) {

        }
    };

    private void setErrorMessage(String message, int visible){
        errorText.setText(message);
        errorText.setVisibility(visible);
        if(View.VISIBLE == visible)
            errorText.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT));
        else
            errorText.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                    1));

    }
    Callback<List<Movie>> moviesCall = new Callback<List<Movie>>() {
        @Override
        public void onResponse(Call<List<Movie>> call, Response<List<Movie>> response) {
            if(response.code() == 200){
                List<Movie> movies = response.body();
                updateAdapter(movies);
                setErrorMessage("", View.GONE);
            }
            else{
                setErrorMessage("Sorry! We couldn't find a movie by director…",View.VISIBLE);
                updateAdapter(new ArrayList<Movie>());
            }
        }

        @Override
        public void onFailure(Call<List<Movie>> call, Throwable t) {

        }
    };
    TextWatcher editCall = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int start, int cnt, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable editable) {
            if( editable.toString().length() > 2 ){
                if( type == 1 )
                    api.getMovieByTitle(editable.toString()).enqueue(movieCall);
                else api.getMovieByDirector(editable.toString()).enqueue(moviesCall);
            }
            if(editable.toString().length() == 0){
                setErrorMessage("",View.GONE);
            }
        }
    };

    MovieClickListener movieClick;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        movieClick = new MovieClickListener(getFragmentManager(),movies,true);
        MyApp app   = (MyApp) getActivity().getApplicationContext();
        api         = app.getNetflixAPI();

        View layout = inflater.inflate(R.layout.movies_list_fragment,container,false);
        GridView list = (GridView) layout.findViewById(R.id.list);
        EditText edit = (EditText)layout.findViewById(R.id.option);
        errorText = (TextView) layout.findViewById(R.id.error);


        if(type == 1) edit.setHint("Enter movie name");
        else edit.setHint("Enter director name");

        edit.addTextChangedListener(editCall);
        list.setOnItemClickListener(movieClick);

        movies = new ArrayList<>();
        adapter = new MovieAdapter(getActivity(),R.layout.movie_item_fragment,movies);

        list.setAdapter(adapter);
        return layout;
    }
    public void updateAdapter(List<Movie> new_movies){
        movies = new_movies;
        adapter.updateMovies(new_movies);
        movieClick.setMovies(movies);
        adapter.notifyDataSetChanged();
    }
    public void setType(int type) {
        this.type = type;
    }
}
