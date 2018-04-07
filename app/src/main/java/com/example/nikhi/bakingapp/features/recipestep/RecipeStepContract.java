package com.example.nikhi.bakingapp.features.recipestep;

import com.example.nikhi.bakingapp.BasePresenter;
import com.example.nikhi.bakingapp.BaseView;
import com.example.nikhi.bakingapp.data.model.Step;
import java.util.List;

class RecipeStepContract {

  interface View extends BaseView<Presenter> {

    void showStepsInViewpager(List<Step> steps);

    void showErrorMessage();

    void moveToCurrentStepPage();
  }

  interface Presenter extends BasePresenter {

    void loadStepsToAdapter();
  }
}
