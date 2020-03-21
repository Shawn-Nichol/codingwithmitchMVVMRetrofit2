package com.example.codingwithmitchmvvmretrofit2;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.example.codingwithmitchmvvmretrofit2.Util.Testing;
import com.example.codingwithmitchmvvmretrofit2.Util.VerticalSpacingItemDecorator;
import com.example.codingwithmitchmvvmretrofit2.adapters.OnRecipeListener;
import com.example.codingwithmitchmvvmretrofit2.adapters.RecipeRecyclerAdapter;
import com.example.codingwithmitchmvvmretrofit2.models.Recipe;
import com.example.codingwithmitchmvvmretrofit2.viewmodel.RecipeListViewModel;

import java.util.List;

public class RecipeListActivity extends BaseActivity implements OnRecipeListener {

    private static final String TAG = "RecipeListActivity";

    private RecipeListViewModel mRecipeListViewModel;
    private RecyclerView mRecyclerView;
    private RecipeRecyclerAdapter mAdapter;
    private SearchView mSearchView;
    Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_list);
        mRecyclerView = findViewById(R.id.recipe_list);
        mSearchView = findViewById(R.id.search_view);
        mToolbar = findViewById(R.id.toolbar);

        // Different then source code, ViewModelProviders is deprecated.
        mRecipeListViewModel = new ViewModelProvider(this).get(RecipeListViewModel.class);

        initRecyclerView();
        subscribeObservers();
        initSearchView();
        if(!mRecipeListViewModel.isViewingRecipes()){
            displaySearchCategories();
        }

        setSupportActionBar(findViewById(R.id.toolbar));
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle(" ");

    }

    private void subscribeObservers(){

        mRecipeListViewModel.getRecipes().observe(this, new Observer<List<Recipe>>() {
            @Override
            public void onChanged(@Nullable List<Recipe> recipes) {
                if(recipes != null){
                    if(mRecipeListViewModel.isViewingRecipes()) {
                        Testing.printRecipes("network test", recipes);
                        mRecipeListViewModel.setIsPerformingQuery(false);
                        mAdapter.setRecipes(recipes);
                    }
                }
            }
        });
    }

    private void initRecyclerView(){
        mAdapter = new RecipeRecyclerAdapter(this);
        mRecyclerView.setAdapter(mAdapter);
        VerticalSpacingItemDecorator itemDecorator = new VerticalSpacingItemDecorator(30);
        mRecyclerView.addItemDecoration(itemDecorator);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

    }

    private void initSearchView(){

        mSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                // Search the database for a recipe
                mAdapter.displayLoading();
                mRecipeListViewModel.searchRecipesApi("chicken", 1);
                mSearchView.clearFocus();

                return false;
            }

            @Override
            public boolean onQueryTextChange(String query) {

                // Wait for the user to submit the search. So do nothing here.

                return false;
            }
        });
    }

    @Override
    public void onRecipeClick(int position) {
        Log.d(TAG, "onRecipeClick: clicked. " + position);
    }

    @Override
    public void onCategoryClick(String category) {
        mAdapter.displayLoading();
        mRecipeListViewModel.searchRecipesApi(category, 1);
        mSearchView.clearFocus();
    }

    @Override
    public void onBackPressed() {
        if(mRecipeListViewModel.onBackPressed()) {
            super.onBackPressed();
        } else {
            displaySearchCategories();
        }
    }

    private void displaySearchCategories(){
        Log.d(TAG, "displaySearchCategories: called.");
        mRecipeListViewModel.setIsViewingRecipes(false);
        mAdapter.displaySearchCategories();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(item.getItemId() == R.id.action_categories){
            displaySearchCategories();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.recipe_search_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }
}
