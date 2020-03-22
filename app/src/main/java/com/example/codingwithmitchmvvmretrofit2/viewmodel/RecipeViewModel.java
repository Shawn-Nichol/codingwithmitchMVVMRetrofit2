package com.example.codingwithmitchmvvmretrofit2.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.codingwithmitchmvvmretrofit2.models.Recipe;
import com.example.codingwithmitchmvvmretrofit2.repositories.RecipeRepository;

public class RecipeViewModel extends ViewModel {

    private RecipeRepository mRecipeRepository;
    private String mRecipeId;
    private boolean mDidRetrievedRecipe;

    public RecipeViewModel() {
        mRecipeRepository = RecipeRepository.getInstance();
        mDidRetrievedRecipe = false;
    }

    public LiveData<Recipe> getRecipe() {
        return mRecipeRepository.getRecipe();
    }

    public LiveData<Boolean> isRecipeRequestTimedOut() {
        return mRecipeRepository.isRecipeRequestTimedOut();
    }

    public void searchRecipeById(String recipeId) {
        mRecipeId = recipeId;
        mRecipeRepository.searchRecipeById(recipeId);
    }

    public String getRecipeId() {
        return mRecipeId;
    }

    public void setRetrievedRecipe(boolean retrievedRecipe) {
        mDidRetrievedRecipe = mDidRetrievedRecipe;
    }

    public boolean didRetrieveRecipe() {
        return mDidRetrievedRecipe;
    }
}
