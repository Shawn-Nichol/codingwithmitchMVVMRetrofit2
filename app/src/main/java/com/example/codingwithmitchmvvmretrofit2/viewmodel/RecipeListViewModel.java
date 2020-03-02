package com.example.codingwithmitchmvvmretrofit2.viewmodel;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.codingwithmitchmvvmretrofit2.models.Recipe;
import com.example.codingwithmitchmvvmretrofit2.repositories.RecipeRepository;

import java.util.List;

public class RecipeListViewModel extends ViewModel {

    private static final String TAG = "RecipeListViewModel";

    private RecipeRepository mRecipeRepository;


    public RecipeListViewModel() {
        mRecipeRepository = RecipeRepository.getInstance();
    }

    public LiveData<List<Recipe>> getRecipes() {
        return mRecipeRepository.getRecipes();
    }

    public void searchRecipesApi(String query, int pageNumber) {
        Log.d(TAG, "searchRecipesApi: Query " + query + ", pageNumber " + pageNumber);
        mRecipeRepository.searchRecipesApi(query, pageNumber);
    }
}
