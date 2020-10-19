package com.example.doggsapps.view;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.doggsapps.R;
import com.example.doggsapps.model.DogBreed;
import com.example.doggsapps.util.Util;

import java.util.ArrayList;
import java.util.List;

public class DogsListAdapter extends RecyclerView.Adapter<DogsListAdapter.DogViewHolder> {
    //the list of gods that we wants to display
    private ArrayList<DogBreed> dogList;

    //to store that we get in the constructor
    public DogsListAdapter(ArrayList<DogBreed> dogList) {
        this.dogList = dogList;
    }

    //method to update the new list at some point
    public void updateDogsList(List<DogBreed> newDogsList) {
        dogList.clear();
        dogList.addAll(newDogsList);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public DogViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_dog, parent, false);
        return new DogViewHolder(view);
    }


    // here we attach the information from the dog list to the view holder
    @Override
    public void onBindViewHolder(@NonNull DogViewHolder holder, int position) {
        // retrieve the information for image view
        ImageView image = holder.itemView.findViewById(R.id.imageView);
        TextView name = holder.itemView.findViewById(R.id.name);
        TextView lifeSpan = holder.itemView.findViewById(R.id.lifeSpan);


        //populate the name and life span so that will a system to create new dog view holder at onCreateViewHolder()
        //for each element that is part of our god lists
        //it  know s how many elements there are in the size from the getItemcount method
        name.setText(dogList.get(position).dogBreed);
        lifeSpan.setText(dogList.get(position).lifeSpan);
        Util.loadImage(image, dogList.get(position).imageUrl, Util.getProgressDrawable(image.getContext()));
    }

    @Override
    public int getItemCount() {
        return dogList.size();
    }


    class DogViewHolder extends RecyclerView.ViewHolder {
        public View itemView;

        public DogViewHolder(@NonNull View itemView) {
            super(itemView);
            this.itemView = itemView;
        }
    }
}
