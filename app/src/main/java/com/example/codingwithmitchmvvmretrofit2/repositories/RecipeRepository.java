package com.example.codingwithmitchmvvmretrofit2.repositories;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.codingwithmitchmvvmretrofit2.models.Recipe;
import com.example.codingwithmitchmvvmretrofit2.request.RecipeApiClient;

import java.util.List;

public class RecipeRepository {

    private static RecipeRepository INSTANCE;
    private RecipeApiClient mRecipeApiClient;


    public static RecipeRepository getInstance() {
        if(INSTANCE == null) {
            INSTANCE = new RecipeRepository();
        }
        return INSTANCE;
    }

    private RecipeRepository() {
        mRecipeApiClient = RecipeApiClient.getInstance();
    }

    public LiveData<List<Recipe>> getRecipes() {
        return mRecipeApiClient.getRecipes();
    }
}
