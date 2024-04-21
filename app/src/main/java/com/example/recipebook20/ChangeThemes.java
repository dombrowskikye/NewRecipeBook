package com.example.recipebook20;


import static android.graphics.Color.MAGENTA;

import android.app.AlertDialog;
import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;

import android.app.AlertDialog;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.DialogFragment;

import com.example.recipebook20.databinding.ChangeThemesBinding;
import com.example.recipebook20.databinding.DialogAddRecipeBinding;

public class ChangeThemes extends DialogFragment {

    private ChangeThemesBinding binding;

    public Dialog onCreateDialog (Bundle savedInstanceState) {

        binding = ChangeThemesBinding.inflate(LayoutInflater.from(getContext()));

        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setView(binding.getRoot());

        binding.returnButton.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick (View v) {
                        dismiss();
                    }
                }
        );

        binding.applyTheme.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick (View v) {
                        applyThemes(v);
                        dismiss();
                    }
                }
        );


        return builder.create();
    }


    public void applyThemes(View v){

        int backgroundColor = Color.TRANSPARENT;
        int toolbarColor = Color.TRANSPARENT;


        if(binding.purpleBack.isChecked()){
            backgroundColor = ContextCompat.getColor(getContext(), R.color.purple);
        } else if(binding.blueBack.isChecked()){
            backgroundColor = ContextCompat.getColor(getContext(), R.color.blue);
        } else if(binding.whiteBack.isChecked()){
            backgroundColor = ContextCompat.getColor(getContext(), R.color.white);
        } else if(binding.redBack.isChecked()){
            backgroundColor = ContextCompat.getColor(getContext(), R.color.red);
        } else if(binding.greenBack.isChecked()){
            backgroundColor = ContextCompat.getColor(getContext(), R.color.green);
        }


        if(binding.blueBanner.isChecked()){
            toolbarColor = ContextCompat.getColor(getContext(), R.color.blue);
            binding.backColor.setBackgroundColor(toolbarColor);
            binding.radioGroup.setBackgroundColor(toolbarColor);
            binding.applyTheme.setBackgroundColor(toolbarColor);
            binding.returnButton.setBackgroundColor(toolbarColor);
        } else if(binding.purpleBanner.isChecked()){
            toolbarColor = ContextCompat.getColor(getContext(), R.color.purple);
            binding.backColor.setBackgroundColor(toolbarColor);
            binding.radioGroup.setBackgroundColor(toolbarColor);
            binding.applyTheme.setBackgroundColor(toolbarColor);
            binding.returnButton.setBackgroundColor(toolbarColor);
        } else if(binding.redBanner.isChecked()){
            toolbarColor = ContextCompat.getColor(getContext(), R.color.red);
            binding.backColor.setBackgroundColor(toolbarColor);
            binding.radioGroup.setBackgroundColor(toolbarColor);
            binding.applyTheme.setBackgroundColor(toolbarColor);
            binding.returnButton.setBackgroundColor(toolbarColor);
        } else if(binding.greenBanner.isChecked()){
            toolbarColor = ContextCompat.getColor(getContext(), R.color.green);
            binding.backColor.setBackgroundColor(toolbarColor);
            binding.radioGroup.setBackgroundColor(toolbarColor);
            binding.applyTheme.setBackgroundColor(toolbarColor);
            binding.returnButton.setBackgroundColor(toolbarColor);
        }

        getActivity().getWindow().getDecorView().setBackgroundColor(backgroundColor);

        if(getActivity() instanceof AppCompatActivity){
            ActionBar actionBar = ((AppCompatActivity) getActivity()).getSupportActionBar();
            if(actionBar != null){
                actionBar.setBackgroundDrawable(new ColorDrawable(toolbarColor));
            }
        }


    }

}
