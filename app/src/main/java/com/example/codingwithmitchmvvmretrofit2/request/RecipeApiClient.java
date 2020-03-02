package com.example.codingwithmitchmvvmretrofit2.request;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.codingwithmitchmvvmretrofit2.AppExecutors;
import com.example.codingwithmitchmvvmretrofit2.Util.Constants;
import com.example.codingwithmitchmvvmretrofit2.models.Recipe;
import com.example.codingwithmitchmvvmretrofit2.request.responses.RecipeSearchResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import retrofit2.Call;
import retrofit2.Response;

import static com.example.codingwithmitchmvvmretrofit2.Util.Constants.NETWORK_TIMEOUT;

public class RecipeApiClient {

    private static final String TAG = "RecipeApiClient";
    
    private static RecipeApiClient INSTANCE;
    private MutableLiveData<List<Recipe>> mRecipes;
    private RetrieveRecipesRunnable mRetrieveRecipesRunnable;

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
    public void searchRecipesApi(String query, int pageNumber) {

        if(mRetrieveRecipesRunnable != null) {
            mRetrieveRecipesRunnable = null;
        }
        mRetrieveRecipesRunnable = new RetrieveRecipesRunnable(query, pageNumber);

        final Future handler = AppExecutors.getInstance().networkIO().submit(new Runnable() {
            @Override
            public void run() {
                // Retrieve data from rest api.
//                mRecipes.postValue();

            }
        });

        // Timeout for the data refresh.
        AppExecutors.getInstance().networkIO().schedule(new Runnable() {
            @Override
            public void run() {
                handler.cancel(true);
            }
        }, NETWORK_TIMEOUT, TimeUnit.MILLISECONDS);
    }
    
    private class RetrieveRecipesRunnable implements Runnable {

        private String query;
        private int pageNumber;
        boolean cancelRequest;

        public RetrieveRecipesRunnable(String query, int pageNumber) {
            this.query = query;
            this.pageNumber = pageNumber;
            cancelRequest = false;
        }

        /**
         * 
         */
        @Override
        public void run() {
            try {
                Response response = getRecipes(query, pageNumber).execute();
                if(cancelRequest) {
                    return;
                }

                if(response.code() == 200) {
                    List<Recipe> list = new ArrayList<>(((RecipeSearchResponse)response.body()).getRecipes());
                    if(pageNumber == 1) {
                        mRecipes.postValue(list);
                    } else {
                        List<Recipe> currentRecipes = mRecipes.getValue();
                        currentRecipes.addAll(list);
                        mRecipes.postValue(currentRecipes);
                    }
                } else {
                    String error = response.errorBody().string();
                    Log.e(TAG, "run: " + error);
                    mRecipes.postValue(null);
                }
            } catch (IOException e) {
                e.printStackTrace();
                mRecipes.postValue(null);
            }

            
        }
        
        private Call<RecipeSearchResponse> getRecipes(String query, int pageNumber) {
            return ServiceGenerator.getRecipeApi().searchRecipe(
                    Constants.BASE_URL,
                    query,
                    String.valueOf(pageNumber)
            );
        }
        
        
        private void cancelRequest() {
            Log.d(TAG, "cancelRequest: canceling the search request.");
            cancelRequest = true;
        }
        
    }

}
