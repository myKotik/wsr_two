package com.example.wsr_two;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class Adaptery extends RecyclerView.Adapter<Adaptery.MyViewHolder> {

    private Context mContext;
    private List<Dishes> dishesList;

    public Adaptery(Context mContext, List<Dishes> dishesList) { 
        this.mContext = mContext;
        this.dishesList = dishesList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v;
        LayoutInflater layoutInflater = LayoutInflater.from(mContext);
        v = layoutInflater.inflate(R.layout.dishes_item, parent, false);

         return new MyViewHolder(v);

    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.id.setText(dishesList.get(position).getDishId());
        holder.name.setText(dishesList.get(position).getNameDish());

        Glide.with(mContext)
                .load(dishesList.get(position).getIcon())
                .into(holder.img);

    }

    @Override
    public int getItemCount() {
        return dishesList.size() ;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView id;
        TextView name;
        ImageView img;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            id = itemView.findViewById(R.id.id);
            name = itemView.findViewById(R.id.name);
            img =  itemView.findViewById(R.id.img);

        }
    }
}
