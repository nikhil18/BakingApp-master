package com.example.nikhi.bakingapp.features.recipelist;

import com.example.nikhi.bakingapp.BasePresenter;
import com.example.nikhi.bakingapp.BaseView;
import com.example.nikhi.bakingapp.data.idlingresource.RecipesIdlingResource;
import com.example.nikhi.bakingapp.data.model.Recipe;
import java.util.List;

public interface RecipeListContract {

  interface View extends BaseView<Presenter> {

    void showRecipeList(List<Recipe> recipeList);

    void showLoadingIndicator(boolean show);

    void showCompletedMessage();

    void showErrorMessage();

    void showRecipeDetails(int recipeId);
  }

  interface Presenter extends BasePresenter {

    void loadRecipesFromRepo(boolean forcedSync, RecipesIdlingResource resource);

    void openRecipeDetails(int recipeId);
  }
}
