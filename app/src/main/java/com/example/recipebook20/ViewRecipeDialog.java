package com.example.recipebook20;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import androidx.fragment.app.DialogFragment;

import com.example.recipebook20.databinding.DialogViewRecipeBinding;
public class ViewRecipeDialog extends DialogFragment {

    private DialogViewRecipeBinding binding;
    private Recipe recipe;

    public static ViewRecipeDialog newInstance(Recipe recipe) {
        ViewRecipeDialog dialog = new ViewRecipeDialog();
        Bundle args = new Bundle();
        args.putSerializable("recipe", recipe);
        dialog.setArguments(args);
        return dialog;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            recipe = (Recipe) getArguments().getSerializable("recipe");
        }
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        binding = DialogViewRecipeBinding.inflate(LayoutInflater.from(getContext()));

        binding.textViewName.setText(recipe.getName());
        binding.textViewInstructions.setText(recipe.getInstructions());
        binding.textViewIngredients.setText(recipe.getIngredients());
        binding.textViewType.setText(recipe.getRecipeType());

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setView(binding.getRoot());

        setupButtonListeners();

        return builder.create();
    }

    private void setupButtonListeners() {
        binding.buttonMainMenu.setOnClickListener(v -> dismiss());
        binding.buttonDelete.setOnClickListener(v -> deleteRecipe());
    }

    private void deleteRecipe() {
        MainActivity mainActivity = (MainActivity) getActivity();
        mainActivity.deleteRecipe(recipe);
        dismiss();
    }

}
