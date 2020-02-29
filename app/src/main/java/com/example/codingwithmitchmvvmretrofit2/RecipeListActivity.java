package com.example.codingwithmitchmvvmretrofit2;

import android.os.Bundle;
import android.util.Log;

import com.example.codingwithmitchmvvmretrofit2.Util.Constants;
import com.example.codingwithmitchmvvmretrofit2.models.Recipe;
import com.example.codingwithmitchmvvmretrofit2.request.RecipeApi;
import com.example.codingwithmitchmvvmretrofit2.request.ServiceGenerator;
import com.example.codingwithmitchmvvmretrofit2.request.responses.RecipeSearchResponse;


import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RecipeListActivity extends BaseActivity {
    private static final String TAG = "RecipeListActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_list);

        findViewById(R.id.test).setOnClickListener(v -> {
            Log.d(TAG, "onClick: ");
            testRetrofitRequest();
        });
    }

    private void testRetrofitRequest() {
        RecipeApi recipeApi = ServiceGenerator.getRecipeApi();
        Log.d(TAG, "testRetrofitRequest: API " + recipeApi.toString());

        Call<RecipeSearchResponse> responseCall = recipeApi
                .searchRecipe(Constants.API_KEY,
                        "chicken",
                        "1");
        Log.d(TAG, "testRetrofitRequest: responseCall " + responseCall.toString());

        responseCall.enqueue(new Callback<RecipeSearchResponse>() {
            /**
             * Invoked for a received response.
             *
             * @param call
             * @param response
             */
            @Override
            public void onResponse(Call<RecipeSearchResponse> call, Response<RecipeSearchResponse> response) {
                Log.d(TAG, "onResponse: " + response.toString());

                if(response.code() == 200) {
                    Log.d(TAG, "onResponse: " + response.body().toString());
                    List<Recipe> recipes = new ArrayList<>(response.body().getRecipes());
                    for(Recipe recipe: recipes) {
                        Log.d(TAG, "onResponse: " + recipe.toString());
                    }
                } else {
                    Log.d(TAG, "onResponse: else");
                }
            }

            /**
             * Invoked when a network exception occurred talking to the server or when an unexpected
             * exception occurred creating the request or processing the response.
             *
             * @param call
             * @param t
             */
            @Override
            public void onFailure(Call<RecipeSearchResponse> call, Throwable t) {
                Log.d(TAG, "onFailure: call " + call + " Throwable " + t);
            }
        });
    }
}
