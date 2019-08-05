package com.example.trainwithme;


import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity implements MyProfileFragment.CollectExerciseListListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation_bar);
        bottomNavigationView.setOnNavigationItemSelectedListener(navigationItemSelectedListener);

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new MyProfileFragment()).commit();


    }

    private BottomNavigationView.OnNavigationItemSelectedListener navigationItemSelectedListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                    Fragment selectedFragment = null;



                    switch (menuItem.getItemId()){
                        case R.id.my_profile_icon:
                            selectedFragment = new MyProfileFragment();
                            break;
                        case R.id.my_weekly_training_icon:
                            selectedFragment = new WeeklyTrainingFragment();
                            break;
                        case R.id.training_history_icon:
                            selectedFragment = new TrainingHistoryFragment();
                            break;
                    }

                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, selectedFragment).commit();
                    return true;
                }
            };


    @Override
    public void exerciseListSend(ArrayList<String> collectedExerciseList) {
        WeeklyTrainingFragment weeklyTrainingFragment = new WeeklyTrainingFragment();
        Bundle bundle = new Bundle();
        bundle.putStringArrayList("collectedExerciseList", collectedExerciseList);
        weeklyTrainingFragment.setArguments(bundle);

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, weeklyTrainingFragment, null);
        fragmentTransaction.commit();

    }
}