package com.example.maxli.dogsdogsdogs.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class DogBreedImages {

    @SerializedName("status")
    @Expose
    String status;

    @SerializedName("message")
    @Expose
    List<String> message;

    public String getStatus() {
        return status;
    }

    public List<String> getMessage() {
        return message;
    }

}
