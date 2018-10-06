package com.example.maxli.dogsdogsdogs.network;

import com.google.gson.JsonElement;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface DogAPIInterface {
    @GET("breeds/list/all")
    Call<JsonElement> getDogList();
}
