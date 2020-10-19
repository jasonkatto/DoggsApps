package com.example.doggsapps.view;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.doggsapps.R;
import com.example.doggsapps.viewModel.ListViewModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ListFragment extends Fragment {
    //this is our view model reference in our view component.
    private ListViewModel viewModel;
    //
    private DogsListAdapter dogsListAdapter = new DogsListAdapter(new ArrayList<>());

    @BindView(R.id.dogsList)
    RecyclerView dogsList;

    @BindView(R.id.listError)
    TextView listError;

    @BindView(R.id.loadingView)
    ProgressBar loadingView;

    @BindView(R.id.refreshLayout)
    SwipeRefreshLayout refreshLayout;


    public ListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list, container, false);
        ButterKnife.bind(this, view);
        // Inflate the layout for this fragment
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        //ListFragmentDirections.ActionDetail action = ListFragmentDirections.actionDetail();
       // Navigation.findNavController(view).navigate(action);


        // instantiate our view model
        viewModel = ViewModelProviders.of(this).get(ListViewModel.class);
        //the refresh function is being called by the view to retrieve the information from the view
        //model and it doesn't really matter how the information is retrieved the view doesn't we know that it doesn't really care.
        viewModel.Refresh();

        //to display the list on the screen
        dogsList.setLayoutManager(new LinearLayoutManager(getContext()));
        dogsList.setAdapter(dogsListAdapter);
        observeViewModel();
    }




    private void observeViewModel() {
       viewModel.dogs.observe(getViewLifecycleOwner(), dogs -> {
           if (dogs !=null && dogs instanceof List){
               dogsList.setVisibility(View.VISIBLE);
               dogsListAdapter.updateDogsList(dogs);
           }
       });

        viewModel.dogLoaderError.observe(getViewLifecycleOwner(), isError -> {
            if (isError != null && isError instanceof Boolean) {
                listError.setVisibility(isError ? View.VISIBLE : View.GONE);
            }
        });

        viewModel.loading.observe(getViewLifecycleOwner(), isLoading -> {
            if (isLoading != null && isLoading instanceof Boolean) {
                loadingView.setVisibility(isLoading ? View.VISIBLE : View.GONE);

                if (isLoading) {
                    listError.setVisibility(View.GONE);
                    dogsList.setVisibility(View.GONE);
                }
            }
        });

    }
}