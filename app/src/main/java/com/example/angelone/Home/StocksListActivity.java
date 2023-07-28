package com.example.angelone.Home;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.example.angelone.R;
import com.example.angelone.Watchlist.Methods;
import com.example.angelone.Watchlist.Model;
import com.example.angelone.Watchlist.RetrofitClient;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class StocksListActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private static final String TAG = "StocksListActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stocks_list);
        recyclerView = findViewById(R.id.recyclerView);
        fetchDataAndDisplay();
    }

    private void fetchDataAndDisplay() {
        Methods methods = RetrofitClient.getRetrofitInstance().create(Methods.class);
        Call<Model> call = methods.getAllData();
        call.enqueue(new Callback<Model>() {
            @Override
            public void onResponse(Call<Model> call, Response<Model> response) {
                if (response.isSuccessful()) {
                    Log.e(TAG, "OnResponse: code : " + response.code());
                    ArrayList<Model.categories> responseData = response.body().getCategories();

                    for (Model.categories data1 : responseData) {
                        Log.e(TAG, "OnResponse: email : " + data1.getStrCategory());
                    }

                    RecyclerAdapterSL recyclerAdapter = new RecyclerAdapterSL(responseData);
                    recyclerView.setAdapter(recyclerAdapter);
                    recyclerView.setLayoutManager(new LinearLayoutManager(StocksListActivity.this));
                } else {
                    Log.e(TAG, "Unsuccessful response: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<Model> call, Throwable t) {
                Log.e(TAG, "OnFailure: " + t.getMessage());
            }
        });
    }
}
