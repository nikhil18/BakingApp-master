package com.example.nikhi.bakingapp.data.source;

import com.example.nikhi.bakingapp.data.source.local.db.DbModule;
import com.example.nikhi.bakingapp.data.source.local.prefs.PreferencesModule;
import com.example.nikhi.bakingapp.BakingAppModule;

import dagger.Component;
import javax.inject.Singleton;

@Singleton
@Component(modules = {RecipeRepositoryModule.class, DbModule.class, PreferencesModule.class,
    BakingAppModule.class})
public interface RecipeRepositoryComponent {

  RecipeRepository getRecipeRepository();
}
