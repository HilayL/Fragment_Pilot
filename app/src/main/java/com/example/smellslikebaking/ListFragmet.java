package com.example.smellslikebaking;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class ListFragmet extends Fragment {

    public interface OnRecipeSelectedInterface{
        void onListRecipeSelected(int index);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        OnRecipeSelectedInterface listener = (OnRecipeSelectedInterface) getActivity();
        View view = inflater.inflate(R.layout.fragment_list,container,false);
        //a view connected to our fragment_list.xml

        RecyclerView recyclerView = view.findViewById(R.id.listRecyclerView);
        // a recycler view connected to the list recyclerView widget fragment_list

        ListAdapter listAdapter = new ListAdapter(listener);
        recyclerView.setAdapter(listAdapter);
        //a list adapter from the desined list adapter class, he is the adapter of the
        // recyclerView we created

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        //a layout manager for our recyclerView (this is the built in linearLayoutManager from android)

        return view;
    }
}
