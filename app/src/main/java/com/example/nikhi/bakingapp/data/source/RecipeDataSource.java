package com.example.nikhi.bakingapp.data.source;

import com.example.nikhi.bakingapp.data.model.Ingredient;
import com.example.nikhi.bakingapp.data.model.Step;
import com.example.nikhi.bakingapp.data.model.Recipe;

import io.reactivex.Observable;
import java.util.List;

public interface RecipeDataSource {

  Observable<List<Recipe>> getRecipes();

  Observable<List<Ingredient>> getRecipeIngredients(int recipeId);

  Observable<List<Ingredient>> getRecipeIngredients(String recipeName);

  Observable<List<Step>> getRecipeSteps(int recipeId);

  void saveRecipes(List<Recipe> recipes);
}
