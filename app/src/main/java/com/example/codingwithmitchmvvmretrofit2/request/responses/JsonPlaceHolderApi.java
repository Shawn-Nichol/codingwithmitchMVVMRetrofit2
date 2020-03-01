package com.example.codingwithmitchmvvmretrofit2.request.responses;

import com.example.codingwithmitchmvvmretrofit2.models.Post;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface JsonPlaceHolderApi {

    @GET("posts")
    Call<List<Post>> getPosts();
}