package com.example.countynameapp;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

public interface MyWebService {


    String BASE_URL = "https://jsonplaceholder.typicode.com/";
    String FEED_URL = "posts";

    Retrofit retrofit = new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();

    @GET(FEED_URL)
    Call<List<Post>> getPost();


}
