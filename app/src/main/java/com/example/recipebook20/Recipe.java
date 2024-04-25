package com.example.recipebook20;

import java.io.Serializable;

public class Recipe implements Serializable {

    private int id;
    private String name;

    private String instructions;
    private String ingredients;
    private String recipeType;

    public Recipe(String name, String instructions, String ingredients, String recipeType){
        this.id = 0;
        this.name=name;
        this.instructions=instructions;
        this.ingredients=ingredients;
        this.recipeType=recipeType;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getInstructions() {
        return instructions;
    }
    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }
    public String getIngredients() {
        return ingredients;
    }
    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }
    public String getRecipeType() {
        return recipeType;
    }
    public void setRecipeType(String recipeType) {
        this.recipeType = recipeType;
    }
}
