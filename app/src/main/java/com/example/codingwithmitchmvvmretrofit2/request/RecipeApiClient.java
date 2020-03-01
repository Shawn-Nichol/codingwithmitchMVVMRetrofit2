package com.example.codingwithmitchmvvmretrofit2.request;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.codingwithmitchmvvmretrofit2.models.Recipe;

import java.util.List;

public class RecipeApiClient {
    private static RecipeApiClient INSTANCE;
    private MutableLiveData<List<Recipe>> mRecipes;

    public static RecipeApiClient getInstance() {
        if(INSTANCE == null) {
            INSTANCE = new RecipeApiClient();
        }
        return INSTANCE;
    }

    private RecipeApiClient() {
        mRecipes = new MutableLiveData<>();
    }

    public LiveData<List<Recipe>> getRecipes() {
        return mRecipes;
    }

}
