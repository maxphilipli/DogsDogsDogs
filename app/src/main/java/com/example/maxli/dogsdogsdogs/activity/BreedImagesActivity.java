package com.example.maxli.dogsdogsdogs.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.maxli.dogsdogsdogs.adapter.DogBreedsImagesAdapter;
import com.example.maxli.dogsdogsdogs.models.DogBreedImages;
import com.example.maxli.dogsdogsdogs.network.DogAPI;
import com.example.maxli.dogsdogsdogs.network.DogAPICallback;
import com.maxli.dogsdogsdogs.R;

import java.util.List;

public class BreedImagesActivity extends AppCompatActivity implements DogAPICallback {

    private TextView imagesLoadingTextView;
    private TextView breedNameTextView;
    private RecyclerView dogImagesList;
    private List<String> dogBreedImages;

    private DogBreedsImagesAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_breed_images);

        imagesLoadingTextView = (TextView) findViewById(R.id.imageloadingtv);
        breedNameTextView = (TextView) findViewById(R.id.breedname);
        //set up recycler view
        dogImagesList = (RecyclerView) findViewById(R.id.dogImagesList);
        final GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 3);
        gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                return 1;
            }
        });
        gridLayoutManager.setOrientation(GridLayoutManager.VERTICAL);
        dogImagesList.setLayoutManager(gridLayoutManager);

        // Get the Intent that started this activity and extract the string
        Intent intent = getIntent();
        String dogBreed = intent.getStringExtra("breed");

        breedNameTextView.setText(dogBreed);

        dogBreed = dogBreed.toLowerCase();

        //make the api call
        if (dogBreed.contains(" ")) {
            String[] splitDogBreed = dogBreed.split(" ");
            String subBreed = splitDogBreed[0];
            dogBreed = splitDogBreed[1];
            DogAPI.getInstance().getSubBreedImages(this, dogBreed, subBreed, this);
        } else {
            DogAPI.getInstance().getBreedImages(this, dogBreed, this);
        }
    }

    @Override
    public void dogAPIGetSuccess(Object o) {
        if (o instanceof DogBreedImages) {
            DogBreedImages dogList = (DogBreedImages) o;
            dogBreedImages = dogList.getMessage();
            adapter = new DogBreedsImagesAdapter(this, dogBreedImages);
            dogImagesList.setAdapter(adapter);
            imagesLoadingTextView.setVisibility(View.GONE);
            breedNameTextView.setVisibility(View.VISIBLE);
            dogImagesList.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void dogAPIGetFailed() {
        Log.d("MAX", "Failed!");
    }
}