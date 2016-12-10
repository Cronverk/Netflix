package com.example.hisaki.netflix.retrofit;


import com.example.hisaki.netflix.enteties.Movie;

import java.util.List;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by hisaki on 08.12.2016.
 */

public interface NetflixApi {
    @GET("api.php")
    Call<Movie> getMovieByTitle(@Query("title") String title);

    @POST("api.php")
    Call<List<Movie>> getMovieByDirector(@Query("director") String director);
}
