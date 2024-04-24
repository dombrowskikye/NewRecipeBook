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

    private ArrayList<Recipe> originalList;
    private RecipeAdapter recipeAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setSupportActionBar(binding.toolbar);

        list = new ArrayList<Recipe>();

        originalList = new ArrayList<Recipe>();

        originalList.add(new Recipe("Tacos","These are the Instructions","These are the Ingredients","Lunch"));

        list.add(new Recipe("Tacos","Add ground beef and a chopped onion to a skillet over medium high heat. Once beef is cooked through drain the grease grease. Add all seasonings and beef broth.\n" +
                "Bring to a boil then lower the heat. Cover and let simmer for about 20 minutes until the liquid has reduced.\n" +
                "In a separate pan, heat the skillet over medium heat and add some oil to the skillet. Lay out a few of the tortillas and tip them with shredded Monterey Jack cheese. Top each one with a spoonful of taco meat. Fold the tortillas over and cook on both sides until golden brown and crispy.\n" +
                "Lay the tacos out on paper towels to absorb any extra grease. Top tacos with your favorite toppings and enjoy!","2 lb ground beef\n" +
                "1 chopped onion\n" +
                "2 TBSP minced garlic\n" +
                "1 tsp each of salt and pepper\n" +
                "1/2 tsp cayenne\n" +
                "4 tsp chili powder\n" +
                "2 tsp each of cumin and dried cilantro\n" +
                "1 1/2 cups of beef broth\n" +
                "White corn tortillas\n" +
                "1 tbsp oil\n" +
                "Shredded Monterey Jack cheese\n" +
                "Taco Toppings sour cream,\n" +
                "Pico de Gallo, taco sauce, shredded lettuce","Lunch"));
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

        if (id == R.id.action_all){
            list.clear();
            list.addAll(originalList);
            recipeAdapter.notifyDataSetChanged();
        }

        if (id == R.id.action_breakfast){
            filterRecipes("Breakfast");
        }

        if (id == R.id.action_lunch){
            filterRecipes("Lunch");
        }

        if (id == R.id.action_dinner){
            filterRecipes("Dinner");
        }

        return true;
    }

    public void filterRecipes(String recipeType){
        ArrayList<Recipe> filteredList = new ArrayList<>();
        for (Recipe recipe : originalList){
            if (recipe.getRecipeType().equals(recipeType)){
                filteredList.add(recipe);
            }
        }
        list.clear();
        list.addAll(filteredList);
        recipeAdapter.notifyDataSetChanged();
    }

    public void addRecipe (Recipe recipe) {
        list.add(recipe);
        originalList.add(recipe);
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