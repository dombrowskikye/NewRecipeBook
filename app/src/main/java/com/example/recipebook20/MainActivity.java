package com.example.recipebook20;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.recipebook20.databinding.ActivityMainBinding;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity implements RecipeAdapter.OnRecipeClickListener {
    private ActivityMainBinding binding;
    private ArrayList<Recipe> list;
    private RecipeAdapter recipeAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setSupportActionBar(binding.toolbar);

        list = new ArrayList<Recipe>();

        list.add(new Recipe("Tacos","These are the Instructions","These are the Ingredients","Lunch"));
        recipeAdapter = new RecipeAdapter(this, list,this);


        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        binding.content.recyclerView.setLayoutManager(layoutManager);
        binding.content.recyclerView.addItemDecoration(new DividerItemDecoration(this,LinearLayoutManager.VERTICAL));
        binding.content.recyclerView.setAdapter(recipeAdapter);



        binding.fab.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                AddRecipeDialog addRecipeDialog = new AddRecipeDialog();
                addRecipeDialog.show(getSupportFragmentManager(),"");
            }
        });
    }

    public boolean onCreateOptionsMenu (Menu menu) {

        getMenuInflater().inflate(R.menu.menu_activity_main, menu);

        return true;

    }

    public boolean onOptionsItemSelected (MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_add) {
            AddRecipeDialog addContactDialog = new AddRecipeDialog();
            addContactDialog.show(getSupportFragmentManager(), "");
        }

        if (id == R.id.action_themes){
            ChangeThemes changeThemes = new ChangeThemes();
            changeThemes.show(getSupportFragmentManager(), "");
        }

        return true;
    }

    public void addRecipe (Recipe recipe) {
        list.add(recipe);
        recipeAdapter.notifyDataSetChanged();
    }

    public void showContactDialog(Recipe recipe) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        ViewRecipeDialog viewRecipeDialog = ViewRecipeDialog.newInstance(recipe);
        viewRecipeDialog.show(fragmentManager, "");
    }

    public void onRecipeClick(int position) {
        Recipe clickedRecipe = list.get(position);
        showContactDialog(clickedRecipe);
    }

    public void deleteRecipe(Recipe recipe) {
        int position = list.indexOf(recipe);
        list.remove(recipe);
        recipeAdapter.notifyItemRemoved(position);
        Toast.makeText(this, "Recipe Deleted", Toast.LENGTH_SHORT).show();
    }




} //End of class