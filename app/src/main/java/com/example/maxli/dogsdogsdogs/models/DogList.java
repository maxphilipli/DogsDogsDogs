package com.example.maxli.dogsdogsdogs.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.LinkedHashMap;
import java.util.List;

public class DogList {
    @SerializedName("status")
    @Expose
    String status;

    @SerializedName("message")
    @Expose
    LinkedHashMap<String, List<String>> message;

    public String getStatus() {
        return status;
    }

    public LinkedHashMap<String, List<String>> getMessage() {
        return message;
    }
}
