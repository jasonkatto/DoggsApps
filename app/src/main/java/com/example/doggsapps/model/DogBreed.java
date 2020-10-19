package com.example.doggsapps.model;


import com.google.gson.annotations.SerializedName;

//class that contains the information that we receive from the backend server.
public class DogBreed {


    //variables
    @SerializedName("id")
    public String breedId;
    @SerializedName("name")
    public String dogBreed;
    @SerializedName("life_span")
    public String lifeSpan;
    @SerializedName("breed_group")
    public String breedGroup;
    @SerializedName("bred_for")
    public String bredFor;
    public String temperament;

    @SerializedName("url")
    public String imageUrl;


    public int uuid;



    //constructor
    public DogBreed(String breedId, String dogBreed, String lifeSpan, String breedGroup, String bredFor, String temperament, String imageUrl) {
        this.breedId = breedId;
        this.dogBreed = dogBreed;
        this.lifeSpan = lifeSpan;
        this.breedGroup = breedGroup;
        this.bredFor = bredFor;
        this.temperament = temperament;
        this.imageUrl = imageUrl;
    }
}
