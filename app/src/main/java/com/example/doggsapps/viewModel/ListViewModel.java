package com.example.doggsapps.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.doggsapps.model.DogBreed;
import com.example.doggsapps.model.DogsApiService;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

public class ListViewModel extends AndroidViewModel {

    public MutableLiveData<List<DogBreed>> dogs = new MutableLiveData<List<DogBreed>>();
    //this will basically provide the information if there is an error coming from the server API.
    public MutableLiveData<Boolean> dogLoaderError = new MutableLiveData<Boolean>();
    //provides information that says whether the information is being loaded in the background
    public MutableLiveData<Boolean> loading = new MutableLiveData<Boolean>();
    private DogsApiService dogsApiService = new DogsApiService();
    private CompositeDisposable disposable = new CompositeDisposable();

    public ListViewModel(@NonNull Application application) {
        super(application);
    }


    //this refresh function is going to be called by the view that says OK now we need to refresh the data.
    public void Refresh() {

        fetchFromRemote();
    }

    private void fetchFromRemote() {
        loading.setValue(true);
        disposable.add(


                dogsApiService.getDogs()
                        .subscribeOn(Schedulers.newThread())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeWith(new DisposableSingleObserver<List<DogBreed>>() {
                            @Override
                            public void onSuccess(List<DogBreed> dogBreeds) {

                                dogs.setValue(dogBreeds);
                                dogLoaderError.setValue(false);
                                loading.setValue(false);
                            }

                            @Override
                            public void onError(Throwable e) {
                                dogLoaderError.setValue(true);
                                loading.setValue(false);
                                e.printStackTrace();
                            }
                        })
        );

    }

    @Override
    protected void onCleared() {
        super.onCleared();
        disposable.clear();
    }
}
