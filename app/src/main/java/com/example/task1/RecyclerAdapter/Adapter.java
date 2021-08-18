package com.example.task1.RecyclerAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.task1.R;
import com.example.task1.Retrofit.Author;
import com.example.task1.databinding.CardItemsBinding;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Adapter extends RecyclerView.Adapter<Adapter.MyViewHolder> {

    Context context;
    List<com.example.task1.Retrofit.Author> list;
    LayoutInflater layoutInflater;

    public Adapter(Context context, List<com.example.task1.Retrofit.Author> list, LayoutInflater layoutInflater) {
        this.context = context;
        this.list = list;
        this.layoutInflater = layoutInflater;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        CardItemsBinding binding = CardItemsBinding.inflate(layoutInflater);
        return new MyViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.setData(list.get(position), context);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    static class MyViewHolder extends RecyclerView.ViewHolder {

        CardItemsBinding binding;

        public MyViewHolder(CardItemsBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void setData(Author author, Context context) {
            Glide.with(context).load(author.getUrl() + ".jpg").into(binding.imageView);
            binding.title.setText(author.getTitle());
            binding.subtitle.setText(author.getThumbnailUrl());
        }
    }
}
