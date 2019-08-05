package com.example.trainwithme;



import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;



/**
 * A simple {@link Fragment} subclass.
 */
public class SundayFragment extends Fragment {


    public SundayFragment() {
        // Required empty public constructor
    }

    View myFragment;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        /*
        Things to do in this fragment (Same as Friday Fragment)
        enter recycler view
        get data from MyProfileFragment through MainActivity and use it to create recycler view
         */


        // Inflate the layout for this fragment
        myFragment = inflater.inflate(R.layout.fragment_sunday,container,false);




        return myFragment;
    }

}
