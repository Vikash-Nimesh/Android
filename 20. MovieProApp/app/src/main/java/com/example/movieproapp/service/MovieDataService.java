package com.example.movieproapp.service;

import com.example.movieproapp.model.Result;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MovieDataService {


    //https://api.themoviedb.org/3/movie/popular?api_key=3b192655ee11086e6b4fe961b76c479d

    @GET("movie/popular")
    Call<Result> getPopularMovies(@Query("api_key") String api_key);

}
