package com.example.task1.RecyclerAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.task1.Retrofit.Author;
import com.example.task1.databinding.CardItemsBinding;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.MyViewHolder> {

    public DataTransferInterface transferInterface;
    Context context;
    public List<com.example.task1.Retrofit.Author> list;
    LayoutInflater layoutInflater;

    public Adapter(Context context, List<com.example.task1.Retrofit.Author> list, LayoutInflater layoutInflater, DataTransferInterface transferInterface) {
        this.context = context;
        this.list = list;
        this.layoutInflater = layoutInflater;
        this.transferInterface = transferInterface;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        CardItemsBinding binding = CardItemsBinding.inflate(layoutInflater);
        return new MyViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.setData(list.get(position), context, transferInterface);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    static class MyViewHolder extends RecyclerView.ViewHolder {

        CardItemsBinding binding;
        int counter;


        public MyViewHolder(CardItemsBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
            counter = 0;
        }

        public void setData(Author author, Context context, DataTransferInterface transferInterface) {
            author.setQuantity(0);
            Glide.with(context).load(author.getUrl() + ".jpg").into(binding.imageView);
            binding.title.setText(author.getTitle());
            binding.subtitle.setText(author.getThumbnailUrl());
            binding.increes.setOnClickListener(x -> {
                ++counter;
                binding.quantaty.setText(String.valueOf(counter));
                author.setQuantity(counter);
                transferInterface.AddToCart(author);
            });
            binding.decrees.setOnClickListener(x -> {
                        if (counter > 1) {
                            --counter;
                            binding.quantaty.setText(String.valueOf(counter));
                            author.setQuantity(counter);
                            transferInterface.RemoveFromCart(author);
                        } else {
                            binding.quantaty.setText("0");
                            author.setQuantity(0);
                            transferInterface.RemoveFromCart(author);
                        }
                    }
            );
        }
    }

    public interface DataTransferInterface {
        public void AddToCart(Author obj);

        public void RemoveFromCart(Author obj);
    }

}
