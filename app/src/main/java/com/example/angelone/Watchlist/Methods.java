package com.example.angelone.Watchlist;

import retrofit2.Call;
import retrofit2.http.GET;


public interface Methods {
    @GET("categories.php")
    Call<Model> getAllData();
}
