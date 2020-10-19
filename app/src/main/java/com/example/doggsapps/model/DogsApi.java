package com.example.doggsapps.model;

import java.util.List;

import io.reactivex.Single;
import retrofit2.http.GET;

public interface DogsApi {

    //We define which functions we will need to interact with the backend API.
    @GET("/DevTides/DogsApi/master/dogs.json")
    Single<List<DogBreed>>getDogs();
}
