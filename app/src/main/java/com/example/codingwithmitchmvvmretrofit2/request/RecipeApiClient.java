package com.example.codingwithmitchmvvmretrofit2.request;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.codingwithmitchmvvmretrofit2.AppExecutors;
import com.example.codingwithmitchmvvmretrofit2.models.Recipe;

import java.util.List;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import static com.example.codingwithmitchmvvmretrofit2.Util.Constants.NETWORK_TIMEOUT;

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

    /**
     * Get data from website.
     */
    public void searchRecipesApi() {
        final Future handler = AppExecutors.getInstance().networkIO().submit(new Runnable() {
            @Override
            public void run() {
                // Retrieve data from rest api.
//                mRecipes.postValue();

            }
        });

        AppExecutors.getInstance().networkIO().schedule(new Runnable() {
            @Override
            public void run() {
                handler.cancel(true);
            }
        }, NETWORK_TIMEOUT, TimeUnit.MILLISECONDS);
    }

}
