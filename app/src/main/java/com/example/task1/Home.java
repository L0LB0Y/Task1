package com.example.task1;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.task1.RecyclerAdapter.Adapter;
import com.example.task1.Retrofit.Author;
import com.example.task1.Retrofit.RetrofitCore;
import com.example.task1.Retrofit.Services;
import com.example.task1.databinding.ActivityHomeBinding;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Home extends AppCompatActivity {


    ActivityHomeBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityHomeBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        Services services = RetrofitCore.getRetrofitInstance().create(Services.class);
        Call<List<Author>> call = services.getAllData();
        call.enqueue(new Callback<List<Author>>() {
            @Override
            public void onResponse(@NonNull Call<List<Author>> call, @NonNull Response<List<Author>> response) {
                setAdapterData(response.body());
            }

            @Override
            public void onFailure(@NonNull Call<List<Author>> call, @NonNull Throwable t) {
                Toast.makeText(Home.this, "Connection Failure !!!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setAdapterData(List<Author> body) {
        Adapter adapter = new Adapter(Home.this, body, getLayoutInflater());
        binding.recyclerView.setAdapter(adapter);
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        binding.recyclerView.setItemViewCacheSize(body.size());

    }
}