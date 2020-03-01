package com.example.codingwithmitchmvvmretrofit2.repositories;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.codingwithmitchmvvmretrofit2.models.Recipe;

import java.util.List;

public class RecipeRepository {

    private static RecipeRepository INSTANCE;
    private MutableLiveData<List<Recipe>> mRecipes;


    public static RecipeRepository getInstance() {
        if(INSTANCE == null) {
            INSTANCE = new RecipeRepository();
        }
        return INSTANCE;
    }

    public RecipeRepository() {
        mRecipes = new MutableLiveData<>();
    }

    public LiveData<List<Recipe>> getRecipes() {
        return mRecipes;
    }
}
