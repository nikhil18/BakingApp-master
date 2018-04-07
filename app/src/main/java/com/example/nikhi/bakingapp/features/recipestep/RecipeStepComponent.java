package com.example.nikhi.bakingapp.features.recipestep;

import com.example.nikhi.bakingapp.data.source.RecipeRepositoryComponent;
import com.example.nikhi.bakingapp.utils.FragmentScoped;

import dagger.Component;

@FragmentScoped
@Component(dependencies = RecipeRepositoryComponent.class, modules = RecipeStepPresenterModule.class)
interface RecipeStepComponent {

  void inject(RecipeStepActivity recipeStepActivity);
}
