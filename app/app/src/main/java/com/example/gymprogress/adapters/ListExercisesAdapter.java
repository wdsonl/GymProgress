package com.example.gymprogress.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gymprogress.R;
import com.example.gymprogress.db.exercises;
import com.example.gymprogress.see_exercise;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ListExercisesAdapter extends RecyclerView.Adapter<ListExercisesAdapter.ExerciseViewHolder> {

    ArrayList<exercises> listExercises;
    ArrayList<exercises> listOriginal;

    public ListExercisesAdapter(ArrayList<exercises> listExercises) {
        this.listExercises = listExercises;
        listOriginal = new ArrayList<>();
        listOriginal.addAll(listExercises);
    }

    @NonNull
    @Override
    public ExerciseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_exercise, null, false);
        return new ExerciseViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ExerciseViewHolder holder, int position) {
        holder.viewName.setText(listExercises.get(position).getexercise_name());
        holder.viewWeight.setText(listExercises.get(position).getexercise_weight());
    }

    @Override
    public int getItemCount() {
        return listExercises.size();
    }

    public class ExerciseViewHolder extends RecyclerView.ViewHolder {

        TextView viewName, viewWeight;
        Button btnProgress;

        public ExerciseViewHolder(@NonNull View itemView) {
            super(itemView);

            viewName = itemView.findViewById(R.id.viewItemName);
            viewWeight = itemView.findViewById(R.id.viewItemWeight);
            btnProgress = itemView.findViewById(R.id.btnProgressXML);


            btnProgress.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Context context = view.getContext();
                    Intent intent = new Intent(context, see_exercise.class);
                    intent.putExtra("ID", listExercises.get(getAdapterPosition()).getId());
                    context.startActivity(intent);
                }
            });
        }
    }
}
