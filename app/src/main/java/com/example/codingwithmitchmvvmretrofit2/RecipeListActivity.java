package com.example.codingwithmitchmvvmretrofit2;

import android.os.Bundle;
import android.util.Log;
import android.view.View;


import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;


import com.example.codingwithmitchmvvmretrofit2.Util.Testing;
import com.example.codingwithmitchmvvmretrofit2.models.Recipe;
import com.example.codingwithmitchmvvmretrofit2.viewmodel.RecipeListViewModel;

import java.util.List;

public class RecipeListActivity extends AppCompatActivity {
    private static final String TAG = "RecipeListActivity";

    private RecipeListViewModel mRecipeListViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_list);

        // Different then source code, ViewModelProviders is deprecated.
        mRecipeListViewModel = new ViewModelProvider(this).get(RecipeListViewModel.class);

        subscribeObservers();

        findViewById(R.id.test).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: ");
                testRetrofitRequest();
            }
        });

    }
    
    private void subscribeObservers() {
        Log.d(TAG, "subscribeObservers: ");
        mRecipeListViewModel.getRecipes().observe(this, new Observer<List<Recipe>>() {
            @Override
            public void onChanged(List<Recipe> recipes) {
                Log.d(TAG, "onChanged: ");
                if(recipes != null) {
                    Log.d(TAG, "onChanged: ");
                    Testing.printRecipes("network test", recipes);
                }
            }
        });
    }

    private void testRetrofitRequest() {
        Log.d(TAG, "testRetrofitRequest: ");
        mRecipeListViewModel.searchRecipesApi("chicken", 1);
    }
}
