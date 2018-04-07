package com.example.nikhi.bakingapp.data.source.local;

import com.example.nikhi.bakingapp.data.model.Ingredient;
import com.example.nikhi.bakingapp.data.model.Recipe;
import com.example.nikhi.bakingapp.data.model.Step;
import com.example.nikhi.bakingapp.data.source.RecipeDataSource;
import com.example.nikhi.bakingapp.data.source.local.db.IngredientPersistenceContract;
import com.example.nikhi.bakingapp.data.source.local.db.RecipePersistenceContract;
import com.example.nikhi.bakingapp.data.source.local.db.StepPersistenceContract;
import com.example.nikhi.bakingapp.utils.DbUtils;
import com.squareup.sqlbrite.BriteDatabase;
import hu.akarnokd.rxjava.interop.RxJavaInterop;
import io.reactivex.Observable;
import java.util.List;
import java.util.Objects;
import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class RecipeLocalDataSource implements RecipeDataSource {

  private final BriteDatabase databaseHelper;

  @Inject
  public RecipeLocalDataSource(BriteDatabase briteDatabase) {
    this.databaseHelper = briteDatabase;
  }

  @Override
  public Observable<List<Recipe>> getRecipes() {

    rx.Observable<List<Recipe>> listObservable = databaseHelper
        .createQuery(RecipePersistenceContract.RecipeEntry.TABLE_NAME, DbUtils.getSelectAllQuery(RecipePersistenceContract.RecipeEntry.TABLE_NAME))
        .mapToOne(DbUtils::recipesFromCursor);

    return RxJavaInterop.toV2Observable(listObservable);
  }

  @Override
  public Observable<List<Ingredient>> getRecipeIngredients(int recipeId) {

    rx.Observable<List<Ingredient>> listObservable = databaseHelper
        .createQuery(IngredientPersistenceContract.IngredientEntry.TABLE_NAME,
            DbUtils.getSelectByIdQuery(IngredientPersistenceContract.IngredientEntry.TABLE_NAME,
                IngredientPersistenceContract.IngredientEntry.COLUMN_RECIPE_ID),
            String.valueOf(recipeId))
        .mapToOne(DbUtils::ingredientsFromCursor);

    return RxJavaInterop.toV2Observable(listObservable);
  }

  @Override
  public Observable<List<Ingredient>> getRecipeIngredients(String recipeName) {
    return getRecipes()
        .flatMap(Observable::fromIterable)
        .filter(recipe -> Objects.equals(recipe.name(), recipeName))
        .map(Recipe::id)
        .flatMap(this::getRecipeIngredients);
  }

  @Override
  public Observable<List<Step>> getRecipeSteps(int recipeId) {

    rx.Observable<List<Step>> listObservable = databaseHelper
        .createQuery(StepPersistenceContract.StepEntry.TABLE_NAME,
            DbUtils.getSelectByIdQuery(StepPersistenceContract.StepEntry.TABLE_NAME,
                StepPersistenceContract.StepEntry.COLUMN_RECIPE_ID),
            String.valueOf(recipeId))
        .mapToOne(DbUtils::stepsFromCursor);

    return RxJavaInterop.toV2Observable(listObservable);
  }

  @Override
  public void saveRecipes(List<Recipe> recipes) {

    BriteDatabase.Transaction transaction = databaseHelper.newTransaction();

    try {
      deleteAllRecipes();

      for (Recipe recipe : recipes) {

        int id = recipe.id();

        for (Ingredient ingredient : recipe.ingredients()) {
          databaseHelper.insert(IngredientPersistenceContract.IngredientEntry.TABLE_NAME,
              DbUtils.ingredientToContentValues(ingredient, id));
        }

        for (Step step : recipe.steps()) {
          databaseHelper.insert(StepPersistenceContract.StepEntry.TABLE_NAME,
              DbUtils.stepToContentValues(step, id));
        }

        databaseHelper.insert(RecipePersistenceContract.RecipeEntry.TABLE_NAME,
            DbUtils.recipeToContentValues(recipe));
      }

      transaction.markSuccessful();

    } finally {
      transaction.end();
    }
  }

  private void deleteAllRecipes() {
    databaseHelper.delete(RecipePersistenceContract.RecipeEntry.TABLE_NAME, null);
    databaseHelper.delete(StepPersistenceContract.StepEntry.TABLE_NAME, null);
    databaseHelper.delete(IngredientPersistenceContract.IngredientEntry.TABLE_NAME, null);
  }
}
