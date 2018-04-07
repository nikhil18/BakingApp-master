package com.example.nikhi.bakingapp.features.widget;

import com.example.nikhi.bakingapp.BakingAppModule;
import com.example.nikhi.bakingapp.data.source.RecipeRepositoryComponent;
import com.example.nikhi.bakingapp.utils.ProviderScoped;
import dagger.Component;

@ProviderScoped
@Component(dependencies = RecipeRepositoryComponent.class, modules = BakingAppModule.class)
interface WidgetDataHelperComponent {

  void inject(WidgetProvider provider);
  void inject(WidgetConfigurationActivity activity);
}
