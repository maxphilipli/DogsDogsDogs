package com.example.maxli.dogsdogsdogs.network;

import android.content.Context;

import com.example.maxli.dogsdogsdogs.models.DogBreedImages;
import com.example.maxli.dogsdogsdogs.models.DogList;
import com.google.gson.Gson;
import com.google.gson.JsonElement;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DogAPI {

    private static final String BASE_URL = "https://dog.ceo/api/";
    private static Retrofit.Builder builder = new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create());
    private static Retrofit retrofit = builder.build();
    private static OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

    private static <S> S createService(Class<S> serviceClass) {
        builder.client(httpClient.build());
        retrofit = builder.build();
        return retrofit.create(serviceClass);
    }

    //make singleton
    private static DogAPI dogAPI;

    public static DogAPI getInstance() {
        if (dogAPI == null)
            dogAPI = new DogAPI();

        return dogAPI;
    }

    private DogAPI(){

    }

    public void getDogList(final Context context, final DogAPICallback callback) {
        DogAPIInterface dogAPIInterface = createService(DogAPIInterface.class);
        Call<JsonElement> call = dogAPIInterface.getDogList();
        call.enqueue(new Callback<JsonElement>() {
            @Override
            public void onResponse(Call<JsonElement> call, Response<JsonElement> response) {
                Gson gson = new Gson();
                if (response.isSuccessful()){
                    callback.dogAPIGetSuccess(gson.fromJson(response.body(), DogList.class));
                }
            }

            @Override
            public void onFailure(Call<JsonElement> call, Throwable t) {
                callback.dogAPIGetFailed();
            }
        });
    }


    public void getBreedImages(final Context context, String breed, final DogAPICallback callback) {
        DogAPIInterface dogAPIInterface = createService(DogAPIInterface.class);
        Call<JsonElement> call = dogAPIInterface.getDogBreedImages(breed);
        call.enqueue(new Callback<JsonElement>() {
            @Override
            public void onResponse(Call<JsonElement> call, Response<JsonElement> response) {
                Gson gson = new Gson();
                if (response.isSuccessful()){
                    callback.dogAPIGetSuccess(gson.fromJson(response.body(), DogBreedImages.class));
                }
            }

            @Override
            public void onFailure(Call<JsonElement> call, Throwable t) {
                callback.dogAPIGetFailed();
            }
        });
    }


    public void getSubBreedImages(final Context context, String breed, String subBreed, final DogAPICallback callback) {
        DogAPIInterface dogAPIInterface = createService(DogAPIInterface.class);
        Call<JsonElement> call = dogAPIInterface.getDogSubBreedImages(breed, subBreed);
        call.enqueue(new Callback<JsonElement>() {
            @Override
            public void onResponse(Call<JsonElement> call, Response<JsonElement> response) {
                Gson gson = new Gson();
                if (response.isSuccessful()){
                    callback.dogAPIGetSuccess(gson.fromJson(response.body(), DogBreedImages.class));
                }
            }

            @Override
            public void onFailure(Call<JsonElement> call, Throwable t) {
                callback.dogAPIGetFailed();
            }
        });
    }

}









