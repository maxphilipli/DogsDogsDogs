package com.example.maxli.dogsdogsdogs.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.maxli.dogsdogsdogs.R;

import java.util.List;

public class DogBreedsImagesAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private List<String> dogBreedsImages;

    public DogBreedsImagesAdapter(Context context, List<String> dogBreedsImages) {
        this.context = context;
        this.dogBreedsImages = dogBreedsImages;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.dogimage_listitem, parent, false);
        return new DogBreedsImagesAdapter.DogBreedsImagesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        DogBreedsImagesAdapter.DogBreedsImagesViewHolder itemHolder = (DogBreedsImagesAdapter.DogBreedsImagesViewHolder)holder;

        Glide.with(context).
                load(dogBreedsImages.get(position)).
                centerCrop().
                placeholder(R.drawable.dog_pawprint_heart).
                into(itemHolder.dogImageView);
    }

    @Override
    public int getItemCount() {
        return dogBreedsImages.size();
    }

    private class DogBreedsImagesViewHolder extends RecyclerView.ViewHolder {

        private ImageView dogImageView;

        public DogBreedsImagesViewHolder(View view) {
            super(view);
            dogImageView = (ImageView)view.findViewById(R.id.dogimageiv);
            DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
            float dpWidth = displayMetrics.widthPixels / displayMetrics.density;
            LinearLayout.LayoutParams parms = new LinearLayout.LayoutParams((int)dpWidth, (int)dpWidth);
            dogImageView.setLayoutParams(parms);
        }
    }



}
