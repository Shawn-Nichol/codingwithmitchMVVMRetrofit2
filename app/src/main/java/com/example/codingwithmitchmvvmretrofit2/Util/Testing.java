package com.example.codingwithmitchmvvmretrofit2.Util;

import android.util.Log;

import com.example.codingwithmitchmvvmretrofit2.models.Recipe;

import java.util.List;

public class Testing {


    public static  void printRecipes(String tag, List<Recipe> list) {
        for(Recipe recipe : list) {
            Log.d(tag, "printRecipes: " + recipe.getRecipe_id() + ", " + recipe.getTitle());
        }
    }
}
