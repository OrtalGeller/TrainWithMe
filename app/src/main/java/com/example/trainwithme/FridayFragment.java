package com.example.trainwithme;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class FridayFragment extends Fragment {


    public FridayFragment() {
        // Required empty public constructor
    }


    View myFragment;
    private RecyclerView myRecyclerView;
    private List<Exercise> exerciseList;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        myFragment = inflater.inflate(R.layout.fragment_friday,container,false);
        myRecyclerView = myFragment.findViewById(R.id.exercise_recyclerview);
        RecyclerViewAdapter recyclerViewAdapter = new RecyclerViewAdapter(getContext(),exerciseList);
        myRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        myRecyclerView.setAdapter(recyclerViewAdapter);




        return myFragment;
    }

    /*
    Things left to do in this fragment
    create button at the bottom to finish training, present motivation massage and loan training to TrainingHistoryFragment
     */

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {

        //get data from MyProfileFragment through MainActivity
        //usage of the recieved data to initiate recycler view

        super.onCreate(savedInstanceState);
        exerciseList = new ArrayList<>();
        ArrayList<String> collectedExerciseList = new ArrayList<String>();

        Fragment parentFragment = getParentFragment();

        if(parentFragment instanceof WeeklyTrainingFragment) {
             collectedExerciseList = ((WeeklyTrainingFragment) parentFragment).getArray();
        }

        for (String exerciseDescription : collectedExerciseList) {
            exerciseList.add(new Exercise(exerciseDescription));
        }
    }
}

