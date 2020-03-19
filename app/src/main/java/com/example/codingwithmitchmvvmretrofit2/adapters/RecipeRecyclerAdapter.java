package com.example.codingwithmitchmvvmretrofit2.adapters;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.codingwithmitchmvvmretrofit2.R;
import com.example.codingwithmitchmvvmretrofit2.models.Recipe;

import java.util.ArrayList;
import java.util.List;

public class RecipeRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private static final int RECIPE_TYPE = 1;
    public static final int LOADING_TYPE = 2;

    private static final String TAG = "RecipeRecyclerAdapter";

    private List<Recipe> mRecipes;
    private OnRecipeListener mOnRecipeListener;


    public RecipeRecyclerAdapter(OnRecipeListener mOnRecipeListener) {
        this.mOnRecipeListener = mOnRecipeListener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;

        switch (viewType) {
            case RECIPE_TYPE: {
                Log.d(TAG, "onCreateViewHolder: Recipe type");
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_recipe_list_item, parent, false);
                return new RecipeViewHolder(view, mOnRecipeListener);
            }
            case LOADING_TYPE: {
                Log.d(TAG, "onCreateViewHolder: loading Recipe");
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_loading_list_item, parent, false);
                return new LoadingViewHolder(view);
            }
            default: {
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_recipe_list_item, parent, false);
                return new RecipeViewHolder(view, mOnRecipeListener);
            }

        }

    }


    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        int itemViewType = getItemViewType(position);
        if(itemViewType == RECIPE_TYPE) {

            RequestOptions requestOptions = new RequestOptions()
                    .placeholder(R.drawable.ic_launcher_background);

            Glide.with(holder.itemView.getContext())
                    .setDefaultRequestOptions(requestOptions)
                    .load(mRecipes.get(position).getImage_url())
                    .into(((RecipeViewHolder) holder).image);

            ((RecipeViewHolder) holder).title.setText(mRecipes.get(position).getTitle());
            ((RecipeViewHolder) holder).publisher.setText(mRecipes.get(position).getPublisher());
            ((RecipeViewHolder) holder).socialScore.setText(String.valueOf(Math.round(mRecipes.get(position).getSocial_rank())));
        }
    }

    @Override
    public int getItemViewType(int position) {
        if(mRecipes.get(position).getTitle().equals("LOADING...")) {
            return LOADING_TYPE;
        } else {
            return RECIPE_TYPE;
        }
    }

    public void displayLoading() {
        Log.d(TAG, "displayLoading: ");
        if (!isLoading()) {
            Log.d(TAG, "displayLoading: loading");
            Recipe recipe = new Recipe();
            recipe.setTitle("LOADING...");
            List<Recipe> loadingList = new ArrayList<>();
            loadingList.add(recipe);
            mRecipes = loadingList;
            notifyDataSetChanged();
        }

    }

    private boolean isLoading() {
        Log.d(TAG, "isLoading: ");
        if(mRecipes != null) {
            Log.d(TAG, "isLoading: not null");
            if (mRecipes.size() > 0) {
                Log.d(TAG, "isLoading: size greater than 0");
                if (mRecipes.get(mRecipes.size() - 1).getTitle().equals("LOADING...")) {
                    Log.d(TAG, "isLoading: getTitle " + mRecipes.get(mRecipes.size()-1).getTitle());
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public int getItemCount() {
        if(mRecipes != null) {
            return mRecipes.size();
        }
        return 0;
    }

    public void setRecipes(List<Recipe> recipes) {
        mRecipes = recipes;
        notifyDataSetChanged();
    }
}