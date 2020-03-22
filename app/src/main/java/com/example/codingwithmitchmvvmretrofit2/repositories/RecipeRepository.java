package com.example.codingwithmitchmvvmretrofit2.repositories;

import android.util.Log;

import androidx.lifecycle.LiveData;

import com.example.codingwithmitchmvvmretrofit2.models.Recipe;
import com.example.codingwithmitchmvvmretrofit2.request.RecipeApiClient;

import java.util.List;

public class RecipeRepository {
    private static final String TAG = "RecipeRepository";

    private static RecipeRepository INSTANCE;
    private RecipeApiClient mRecipeApiClient;
    private String mQuery;
    private int mPageNumber;


    /**
     * Singleton
     */
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

    public LiveData<Recipe> getRecipe() {
        return mRecipeApiClient.getRecipe();
    }

    public void searchRecipeById(String recipeId) {
        mRecipeApiClient.searchRecipeById(recipeId);
    }

    public void searchRecipesApi(String query, int pageNumber) {
        Log.d(TAG, "searchRecipesApi: query " + query + ", pageNumber " + pageNumber);
        if(pageNumber == 0) {
            pageNumber = 1;
        }

        mQuery = query;
        mPageNumber = pageNumber;
        mRecipeApiClient.searchRecipesApi(query, pageNumber);
    }

    public void searchNextPage() {
        searchRecipesApi(mQuery, mPageNumber + 1);
    }

    public void cancelRequest() {
        mRecipeApiClient.cancelRequest();
    }

}
