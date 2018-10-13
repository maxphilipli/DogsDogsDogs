package com.example.maxli.dogsdogsdogs.network;

import com.google.gson.JsonElement;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface DogAPIInterface {
    @GET("breeds/list/all")
    Call<JsonElement> getDogList();

    @GET("breed/{breed}/images")
    Call<JsonElement> getDogBreedImages(@Path("breed") String breed);

    @GET("breed/{breed}/{subBreed}/images")
    Call<JsonElement> getDogSubBreedImages(@Path("breed") String breed, @Path("subBreed") String subBreed);

}
