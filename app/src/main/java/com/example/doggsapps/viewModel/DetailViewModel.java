package com.example.doggsapps.viewModel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.doggsapps.model.DogBreed;

public class DetailViewModel extends ViewModel {

    public MutableLiveData<DogBreed> dogLiveData= new MutableLiveData<DogBreed>();



    public void fetch(){
        DogBreed dog1 = new DogBreed("1", "corgi", "15 years", "", "companianShip", "calm and Frendly", "");
        dogLiveData.setValue(dog1);
    }
}
