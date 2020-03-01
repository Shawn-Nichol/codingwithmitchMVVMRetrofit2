package com.example.codingwithmitchmvvmretrofit2;

import android.os.Bundle;
import android.util.Log;


import androidx.appcompat.app.AppCompatActivity;

import com.example.codingwithmitchmvvmretrofit2.Util.Constants;
import com.example.codingwithmitchmvvmretrofit2.models.Post;
import com.example.codingwithmitchmvvmretrofit2.models.Recipe;
import com.example.codingwithmitchmvvmretrofit2.request.RecipeApi;
import com.example.codingwithmitchmvvmretrofit2.request.ServiceGenerator;
import com.example.codingwithmitchmvvmretrofit2.request.responses.JsonPlaceHolderApi;
import com.example.codingwithmitchmvvmretrofit2.request.responses.RecipeResponse;
import com.example.codingwithmitchmvvmretrofit2.request.responses.RecipeSearchResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RecipeListActivity extends AppCompatActivity {
    private static final String TAG = "RecipeListActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_list);

        findViewById(R.id.test).setOnClickListener(v -> {
            Log.d(TAG, "onClick: ");
            myRetroFit();
        });
    }

    private void myRetroFit() {
//        RecipeApi recipeApi = ServiceGenerator.getRecipeApi();
//
//        Call<RecipeSearchResponse> responseCall = recipeApi
//        .searchRecipe(
//                Constants.API_KEY,
//                "chicken",
//                "1");
//
//        responseCall.enqueue(new Callback<RecipeSearchResponse>() {
//            @Override
//            public void onResponse(Call<RecipeSearchResponse> call, Response<RecipeSearchResponse> response) {
//                Log.d(TAG, "onResponse: server response " + response.toString());
//
//                // Can google http response codes.
//                if(response.code() == 200) {
//                    Log.d(TAG, "onResponse: " + response.body().toString());
//                    List<Recipe> recipes = new ArrayList<>(response.body().getRecipes());
//                    for(Recipe recipe: recipes) {
//                        Log.d(TAG, "onResponse: " + recipe.getTitle());
//                    }
//                } else {
//                    try {
//                        Log.d(TAG, "onResponse: " + response.errorBody().string());
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//                }
//            }
//
//            @Override
//            public void onFailure(Call<RecipeSearchResponse> call, Throwable t) {
//                Log.d(TAG, "onFailure: ");
//            }
//        });

        RecipeApi recipeApi = ServiceGenerator.getRecipeApi();

        Call<RecipeResponse> responseCall = recipeApi
                .getRecipe(
                        Constants.API_KEY,
                        "b269c4");

        responseCall.enqueue(new Callback<RecipeResponse>() {
            @Override
            public void onResponse(Call<RecipeResponse> call, Response<RecipeResponse> response) {
                Log.d(TAG, "onResponse: server response: " + response.toString());
                if(response.code() == 200) {
                    Log.d(TAG, "onResponse: " + response.body().toString());
                    Recipe recipe = response.body().getRecipe();
                    Log.d(TAG, "onResponse: Retrieved Recipe: " + recipe.toString());

                } else {
                    try {
                        Log.d(TAG, "onResponse: " + response.errorBody().string());
                    } catch( IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<RecipeResponse> call, Throwable t) {
                Log.d(TAG, "onFailure: ");
            }
        });
        
    }
}
