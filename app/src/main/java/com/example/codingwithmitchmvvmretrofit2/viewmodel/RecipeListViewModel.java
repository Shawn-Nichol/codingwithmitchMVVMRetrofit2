package com.example.codingwithmitchmvvmretrofit2.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.codingwithmitchmvvmretrofit2.models.Recipe;

import java.util.List;

public class RecipeListViewModel extends ViewModel {

    private MutableLiveData<List<Recipe>> mRecipes = new MutableLiveData<>();

    public RecipeListViewModel() {

    }

    public LiveData<List<Recipe>> getRecipes() {
        return mRecipes;
    }
}
