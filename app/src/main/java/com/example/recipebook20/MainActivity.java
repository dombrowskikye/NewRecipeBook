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

        list.add(new Recipe("Tacos","Add ground beef and a chopped\n" +
                "onion to a skillet over\n"+
                "medium-high heat...",
                "1 chopped onion\n" +
                "2 TBSP minced garlic\n" +
                "1 tsp each of salt and pepper\n" +
                "1/2 tsp cayenne\n" +
                "4 tsp chili powder\n" +
                "2 tsp each of cumin\n" +
                "1 1/2 cups of beef broth...","Lunch"));

        list.add(new Recipe("Turkey Wrap","In a medium bowl,\n" +
                "combine scallion,\n" +
                "goat cheese, yogurt, \n" +
                "and 2 teaspoons oil; \n" +
                "season with salt and pepper.",
                "1 scallion, thinly sliced \n" +
                "4 oz. crumbled goat cheese \n" +
                "4 oz. plain whole-milk Greek \n" +
                        "yogurt \n" +
                "3 tsp. extra-virgin olive oil, \n" +
                        "divided \n" +
                "Kosher salt \n" +
                "Freshly ground black pepper \n" +
                "4 loosely packed arugula...","Dinner"));

        list.add(new Recipe("Hamburger","Add ground beef and a chopped\n" +
                "onion to a skillet over\n"+
                "medium-high heat...",
                "1 chopped onion\n" +
                        "2 TBSP minced garlic\n" +
                        "1 tsp each of salt and pepper\n" +
                        "1/2 tsp cayenne\n" +
                        "4 tsp chili powder\n" +
                        "2 tsp each of cumin\n" +
                        "1lb of 80% lean beef...","Dinner"));

        list.add(new Recipe("Pancakes","Add batter to large\n" +
                "mixing bowl\n"+
                "along with 1 egg...",
                "flour\n" +
                        "baking powder\n" +
                        "Sugar\n" +
                        "Salt\n" +
                        "Milk...","Breakfast"));

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