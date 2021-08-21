package com.example.task1.AddToCart;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.task1.Home;
import com.example.task1.Retrofit.Author;
import com.example.task1.databinding.ActivityAddToCartBinding;

import java.util.ArrayList;
import java.util.List;

public class AddToCart extends AppCompatActivity {

    ActivityAddToCartBinding binding;
    List<Author> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddToCartBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        list=Home.list;
        getSupportActionBar().setTitle("My Cart List");
        ArrayList<Author> parcelableArrayListExtra = getIntent().getParcelableArrayListExtra("List Item");
        initRecyclerView(parcelableArrayListExtra);
        binding.submitOrder.setOnClickListener(x -> {
            Toast.makeText(this, "Order Submitted Successful!!", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(this, Home.class));
            finish();
        });
    }

    private void initRecyclerView(ArrayList<Author> parcelableArrayListExtra) {
        AddToCartAdapter adapter = new AddToCartAdapter(parcelableArrayListExtra, AddToCart.this, getLayoutInflater());
        binding.recyclerViewCartItem.setAdapter(adapter);
        binding.recyclerViewCartItem.setHasFixedSize(false);
        binding.recyclerViewCartItem.setItemAnimator(new DefaultItemAnimator());
        binding.recyclerViewCartItem.setLayoutManager(new LinearLayoutManager(this));
    }
}