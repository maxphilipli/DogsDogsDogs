package com.example.maxli.dogsdogsdogs.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.maxli.dogsdogsdogs.activity.BreedImagesActivity;
import com.maxli.dogsdogsdogs.R;

import java.util.List;

public class DogBreedsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private List<String> dogBreeds;

    public DogBreedsAdapter(Context context, List<String> dogBreeds) {
        this.context = context;
        this.dogBreeds = dogBreeds;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.dogbreed_listitem, parent, false);
        return new DogBreedsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        DogBreedsViewHolder itemHolder = (DogBreedsViewHolder)holder;

        itemHolder.dogBreedTextView.setText(dogBreeds.get(position));
        itemHolder.relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, BreedImagesActivity.class);
                intent.putExtra("breed", dogBreeds.get(position));
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return dogBreeds.size();
    }

    private class DogBreedsViewHolder extends RecyclerView.ViewHolder {

        private RelativeLayout relativeLayout;
        private TextView dogBreedTextView;

        public DogBreedsViewHolder(View view) {
            super(view);
            relativeLayout = (RelativeLayout)view.findViewById(R.id.relativelayout);
            dogBreedTextView = (TextView)view.findViewById(R.id.breedtv);
        }
    }


}
