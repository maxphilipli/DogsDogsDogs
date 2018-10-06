package com.example.maxli.dogsdogsdogs.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.maxli.dogsdogsdogs.R;
import com.example.maxli.dogsdogsdogs.adapter.DogBreedsAdapter;
import com.example.maxli.dogsdogsdogs.models.DogList;
import com.example.maxli.dogsdogsdogs.network.DogAPI;
import com.example.maxli.dogsdogsdogs.network.DogAPICallback;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements DogAPICallback{

    private TextView loadingTextView;
    private RecyclerView rvList;

    private DogBreedsAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    private List<String> dogBreeds;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loadingTextView = (TextView) findViewById(R.id.loadingtv);
        //set up recycler view
        rvList = (RecyclerView) findViewById(R.id.rvlist);
        rvList.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        rvList.setLayoutManager(layoutManager);

        DogAPI.getInstance().getDogList(this, this);
    }

    @Override
    public void dogAPIGetSuccess(Object o) {
        if (o instanceof DogList) {
            DogList dogList = (DogList)o;
            dogBreeds = new ArrayList<>();
            for( Map.Entry<String,List<String>> entry : dogList.getMessage().entrySet()){
                String key = entry.getKey();
                List<String>value = entry.getValue();
                if (value.size() > 0) {
                    int valueSize = value.size();
                    for (int i = 0; i < valueSize; i++) {
                        dogBreeds.add(value.get(i) + " " + key);
                    }
                } else {
                    dogBreeds.add(key);
                }
            }
            adapter = new DogBreedsAdapter(this, dogBreeds);
            rvList.setAdapter(adapter);
            loadingTextView.setVisibility(View.GONE);
            rvList.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void dogAPIGetFailed() {
        Log.d("MAX", "Failed!");
    }
}
