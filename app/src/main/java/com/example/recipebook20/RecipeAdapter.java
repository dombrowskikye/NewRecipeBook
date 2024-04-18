package com.example.recipebook20;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecipeAdapter extends RecyclerView.Adapter<RecipeAdapter.ListItemHolder> {

    private MainActivity mainActivity;
    private ArrayList<Recipe> list;
    private OnRecipeClickListener recipeClickListener;
    public interface OnRecipeClickListener {
        void onRecipeClick(int position);
    }

    public RecipeAdapter (MainActivity mainActivity, ArrayList<Recipe> list, OnRecipeClickListener listener) {
        this.mainActivity = mainActivity;
        this.list = list;
        this.recipeClickListener = listener;
    }
    public RecipeAdapter.ListItemHolder onCreateViewHolder (ViewGroup parent, int viewType ){
        View listItem = LayoutInflater.from (parent.getContext())
                .inflate(R.layout.list_layout, parent, false);

        return new ListItemHolder(listItem);
    }

    public void onBindViewHolder (RecipeAdapter.ListItemHolder holder, int position) {
        Recipe recipe = list.get(position);
        holder.textViewName.setText(recipe.getName());

    }

    public int getItemCount () {
        return  list.size();
    }

    public class ListItemHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView textViewName;

        public ListItemHolder (View itemView) {
            super(itemView);
            textViewName = itemView.findViewById(R.id.textViewName);
            textViewName.setClickable(true);
            textViewName.setOnClickListener(this);

        }

        public void onClick (View view) {
            int position = getAdapterPosition();
            if (position != RecyclerView.NO_POSITION) {
                recipeClickListener.onRecipeClick(position);
            }
        }
    }


}
