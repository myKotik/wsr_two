package com.example.wsr_two;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    List<Dishes> dishesList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        dishesList = new ArrayList<>();

        GsonConverterFactory factory = GsonConverterFactory.create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://food.madskill.ru/")
                .addConverterFactory(factory)
                .build();

        DishesApi dishesApi = retrofit.create(DishesApi.class);

        Call<List<Dishes>> call = dishesApi.getDishes("1.01");

        call.enqueue(new Callback<List<Dishes>>() {
            @Override
            public void onResponse(Call<List<Dishes>> call, Response<List<Dishes>> response) {
                if (response.code() != 200) {
                    return;
                }
                List<Dishes> dishes = response.body();


                //StringBuilder text = new StringBuilder();
                for (Dishes dish : dishes) {
                    dishesList.add(dish);
                }
                PutDataIntoRecycleView(dishesList);

            }

            @Override
            public void onFailure(Call<List<Dishes>> call, Throwable t) {
                Log.i("-------", t.getMessage());
            }
        });
    }

    private void PutDataIntoRecycleView(List<Dishes> dishesList) {
        Adaptery adaptery = new Adaptery(this, dishesList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adaptery);
    }
}