package com.example.task1;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.task1.AddToCart.AddToCart;
import com.example.task1.RecyclerAdapter.Adapter;
import com.example.task1.Retrofit.Author;
import com.example.task1.Retrofit.RetrofitCore;
import com.example.task1.Retrofit.Services;
import com.example.task1.databinding.ActivityHomeBinding;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Home extends AppCompatActivity implements Adapter.DataTransferInterface {


    ActivityHomeBinding binding;
  public static   ArrayList<Author> list;

  
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityHomeBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        list = new ArrayList<>();
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
        binding.addTOCard.setOnClickListener(x -> {
            Intent intent = new Intent(this, AddToCart.class);
            Bundle bundle =new Bundle();
//            bundle.putArra
            intent.putParcelableArrayListExtra("List Item", list);
            startActivity(intent);
        });
    }

    private void setAdapterData(List<Author> body) {
        Adapter adapter = new Adapter(Home.this, body, getLayoutInflater(), this);
        binding.recyclerView.setAdapter(adapter);
        binding.recyclerView.setItemAnimator(new DefaultItemAnimator());
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        binding.recyclerView.setHasFixedSize(true);

    }

    @Override
    public void AddToCart(Author obj) {
        try {
            if (list.contains(obj))
                list.set(list.indexOf(obj), obj);
            else
                list.add(obj);
        } catch (Exception ex) {
            Log.e("Error    ", ex.getMessage());
        }
    }

    @Override
    public void RemoveFromCart(Author obj) {
        try {
            if (list.contains(obj))
                list.set(list.indexOf(obj), obj);
            if (obj.getQuantity() == 0)
                list.remove(obj);
        } catch (Exception ex) {
            Log.e("Error    ", ex.getMessage());
        }
    }
}