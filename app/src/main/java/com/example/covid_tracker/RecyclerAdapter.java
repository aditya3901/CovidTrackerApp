package com.example.covid_tracker;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {

    ArrayList<StateModel> list;
    Context context;
    public RecyclerAdapter(ArrayList<StateModel> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @org.jetbrains.annotations.NotNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull @org.jetbrains.annotations.NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.state_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @org.jetbrains.annotations.NotNull RecyclerAdapter.ViewHolder holder, int position) {
        holder.state.setText(list.get(position).getState());
        holder.activeCase.setText(list.get(position).getActive());
        holder.recoCase.setText(list.get(position).getRecovered());
        holder.deadCase.setText(list.get(position).getDead());
        holder.totalCase.setText(list.get(position).getTotal());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView state, activeCase, recoCase, deadCase, totalCase;
        public ViewHolder(@NonNull @org.jetbrains.annotations.NotNull View itemView) {
            super(itemView);
            state = itemView.findViewById(R.id.stateName);
            activeCase = itemView.findViewById(R.id.active);
            recoCase = itemView.findViewById(R.id.rev);
            deadCase = itemView.findViewById(R.id.dead);
            totalCase = itemView.findViewById(R.id.total);
        }
    }
}
