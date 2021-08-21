package com.example.task1.AddToCart;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.task1.Retrofit.Author;
import com.example.task1.databinding.CartItemBinding;

import java.util.ArrayList;

public class AddToCartAdapter extends RecyclerView.Adapter<AddToCartAdapter.MyHolder> {

    ArrayList<Author> parcelableArrayListExtra;
    Context addToCart;
    LayoutInflater layoutInflater;

    public AddToCartAdapter(ArrayList<Author> parcelableArrayListExtra, Context addToCart, LayoutInflater layoutInflater) {
        this.addToCart = addToCart;
        this.layoutInflater = layoutInflater;
        this.parcelableArrayListExtra = parcelableArrayListExtra;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        CartItemBinding binding = CartItemBinding.inflate(layoutInflater);
        return new MyHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, int position) {
        holder.setData(parcelableArrayListExtra.get(position), addToCart);
    }

    @Override
    public int getItemCount() {
        return parcelableArrayListExtra.size();
    }

    public static class MyHolder extends RecyclerView.ViewHolder {

        CartItemBinding binding;

        public MyHolder(CartItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        void setData(Author author, Context context) {
            Glide.with(context).load(author.getUrl() + ".jpg").into(binding.imageViewItem);
            binding.quantatyCart.setText(String.valueOf(author.getQuantity()));
            binding.titleCart.setText(author.getTitle());
            binding.increesCart.setOnClickListener(x -> {
                int counter = author.getQuantity();
                ++counter;
                binding.quantatyCart.setText(String.valueOf(counter));
                author.setQuantity(counter);
            });
            binding.decreesCart.setOnClickListener(x -> {
                        int counter = author.getQuantity();
                        if (counter > 1) {
                            --counter;
                            binding.quantatyCart.setText(String.valueOf(counter));
                            author.setQuantity(counter);
                        } else
                            binding.quantatyCart.setText("0");
                    }
            );
        }
    }
}
