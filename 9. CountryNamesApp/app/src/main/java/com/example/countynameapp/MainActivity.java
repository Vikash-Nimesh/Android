package com.example.countynameapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        runRetrofit();
    }

    public void runRetrofit(){

        MyWebService myWebService = MyWebService.retrofit.create(MyWebService.class);
        Call<List<Post>> call = myWebService.getPost();

        call.enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {

                if (response.isSuccessful()){
                    for(Post post : response.body()){
                        Log.i("userID", String.valueOf(post.getUserId()));
                        Log.i("id", String.valueOf(post.getId()));
                        Log.i("title", String.valueOf(post.getTitle()));
                        Log.i("body", String.valueOf(post.getBody()));
                    }
                }

            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {

            }
        });

    }
}