package com.example.nikhi.bakingapp.features.recipelist;

import com.example.nikhi.bakingapp.data.source.RecipeRepositoryComponent;
import com.example.nikhi.bakingapp.utils.FragmentScoped;
import dagger.Component;

@FragmentScoped
@Component(dependencies = RecipeRepositoryComponent.class, modules = RecipeListPresenterModule.class)
interface RecipeListComponent {

  void inject(RecipeListActivity recipeListActivity);
}
