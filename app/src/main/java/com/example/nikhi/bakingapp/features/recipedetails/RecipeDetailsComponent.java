package com.example.nikhi.bakingapp.features.recipedetails;

import com.example.nikhi.bakingapp.data.source.RecipeRepositoryComponent;
import com.example.nikhi.bakingapp.utils.FragmentScoped;

import dagger.Component;

@FragmentScoped
@Component(dependencies = RecipeRepositoryComponent.class, modules = RecipeDetailsPresenterModule.class)
public interface RecipeDetailsComponent {

  void inject(RecipeDetailsActivity recipeDetailsActivity);
}
