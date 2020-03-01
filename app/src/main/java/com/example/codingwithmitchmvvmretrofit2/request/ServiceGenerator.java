package com.example.codingwithmitchmvvmretrofit2.request;

import android.util.Log;

import com.example.codingwithmitchmvvmretrofit2.Util.Constants;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

// Uses singleton
public class ServiceGenerator {

    private static final String TAG = "ServiceGenerator";

    private static Retrofit.Builder retrofitBuilder =
            new Retrofit.Builder()
                    .baseUrl(Constants.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create());

    private static Retrofit retrofit = retrofitBuilder.build();

    private static RecipeApi recipeApi = retrofit.create(RecipeApi.class);

    public static RecipeApi getRecipeApi() {
        Log.d(TAG, "getRecipeApi: " + Constants.BASE_URL + " API " + Constants.API_KEY);
        return recipeApi;
    }

}
