package com.example.recipebook20;


import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.DialogFragment;

import com.example.recipebook20.databinding.DialogAddRecipeBinding;

public class AddRecipeDialog extends DialogFragment {

    private DialogAddRecipeBinding binding;
    public Dialog onCreateDialog (Bundle savedInstanceState) {

        binding = DialogAddRecipeBinding.inflate(LayoutInflater.from(getContext()));

        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setView(binding.getRoot());
        binding.addRecipe.inflateMenu(R.menu.menu_add_recipe);

        binding.addRecipe.setOnMenuItemClickListener(
                new Toolbar.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        int id = item.getItemId();

                        if (id == R.id.action_add) {
                            String name = binding.inputRecipeName.getText().toString();
                            String instructions = binding.inputRecipeInstructions.getText().toString();
                            String ingredients = binding.inputRecipeIngredients.getText().toString();

                            String recipeType = "";

                            if (binding.breakfastButton.isChecked())
                                recipeType = "Breakfast";
                            else if (binding.lunchButton.isChecked())
                                recipeType = "Lunch";
                            else if (binding.dinnerButton.isChecked())
                                recipeType = "Dinner";


                            Recipe recipe = new Recipe (name, instructions, ingredients, recipeType);

                            MainActivity mainActivity = (MainActivity) getActivity();
                            mainActivity.addRecipe(recipe);

                            dismiss();

                        }
                        else if (id == R.id.action_clear) {
                            binding.inputRecipeName.setText("");
                            binding.inputRecipeInstructions.setText("");
                            binding.inputRecipeIngredients.setText("");

                            binding.breakfastButton.setChecked(true);
                            binding.inputRecipeName.requestFocus();

                        }
                        else if (id == R.id.action_cancel) {
                            dismiss();
                        }

                        return false;
                    }
                }
        );

        binding.cancelButton.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick (View v) {
                        dismiss();
                    }
                }
        );

        binding.clearButton.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick (View v) {
                        binding.inputRecipeName.setText("");
                        binding.inputRecipeInstructions.setText("");
                        binding.inputRecipeIngredients.setText("");

                        binding.breakfastButton.setChecked(true);
                        binding.inputRecipeName.requestFocus();

                    }
                }

        );
        binding.addButton.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick (View v) {

                        String name = binding.inputRecipeName.getText().toString();
                        String instructions = binding.inputRecipeInstructions.getText().toString();
                        String ingredients = binding.inputRecipeIngredients.getText().toString();

                        String recipeType = "";

                        if (binding.breakfastButton.isChecked())
                            recipeType = "Breakfast";
                        else if (binding.lunchButton.isChecked())
                            recipeType = "Lunch";
                        else if (binding.dinnerButton.isChecked())
                            recipeType = "Dinner";


                        Recipe recipe = new Recipe (name, instructions, ingredients, recipeType);

                        MainActivity mainActivity = (MainActivity) getActivity();
                        mainActivity.addRecipe (recipe);

                        dismiss();


                    }
                }
        );


        return builder.create();

    }
}
