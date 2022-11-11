package com.example.gymprogress;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class PeitoAdapter extends RecyclerView.Adapter<PeitoAdapter.PeitoViewHolder> {

    @NonNull
    @Override
    public PeitoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemlista = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_peitoadapter, parent, false);
        return new PeitoViewHolder(itemlista);
    }

    @Override
    public void onBindViewHolder(@NonNull PeitoViewHolder holder, int position) {
        holder.exercise.setText("");
        holder.peso.setText("");
        holder.kg.setText("");
    }

    @Override
    public int getItemCount() {
        return 5;
    }

    public class PeitoViewHolder extends RecyclerView.ViewHolder {

        TextView exercise;
        TextView peso;
        TextView kg;

        public PeitoViewHolder(@NonNull View itemView) {
            super(itemView);

            exercise = itemView.findViewById(R.id.exercisetv);
            peso = itemView.findViewById(R.id.pesotv);
            kg = itemView.findViewById(R.id.kgtv);
        }
    }
}
