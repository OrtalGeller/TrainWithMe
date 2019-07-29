package com.example.trainwithme;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;



import java.util.Calendar;


public class WeeklyTrainingFragment extends Fragment {

    View myFragment;
    ViewPager viewPager;
    TabLayout tabLayout;


    public WeeklyTrainingFragment() {
        //Required empty public constructor
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        myFragment = inflater.inflate(R.layout.fragment_weekly_training,container,false);

        viewPager = myFragment.findViewById(R.id.viewPager);
        tabLayout = myFragment.findViewById(R.id.tabLayout);








        return myFragment;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        setUpViewPager(viewPager);
        tabLayout.setupWithViewPager(viewPager);

        tabLayout.addOnTabSelectedListener(new TabLayout.BaseOnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

    }

    private void setUpViewPager(ViewPager viewPager) {
        SectionPagerAdapter adapter = new SectionPagerAdapter(getChildFragmentManager());

        adapter.addFragment(new SundayFragment(), "Sun");
        adapter.addFragment(new MondayFragment(), "Mon");
        adapter.addFragment(new TuesdayFragment(), "Tue");
        adapter.addFragment(new WednesdayFragment(), "Wed");
        adapter.addFragment(new ThursdayFragment(), "Thu");

        viewPager.setAdapter(adapter);

        viewPager.setCurrentItem(1, true);

        Calendar calendar = Calendar.getInstance();
        int dayOfTheWeek = calendar.get(Calendar.DAY_OF_WEEK);
        if (Calendar.SUNDAY == dayOfTheWeek) {
            viewPager.setCurrentItem(0, true);
        } else if (Calendar.MONDAY == dayOfTheWeek) {
            viewPager.setCurrentItem(1, true);
        } else if (Calendar.TUESDAY == dayOfTheWeek) {
            viewPager.setCurrentItem(2, true);
        } else if (Calendar.WEDNESDAY == dayOfTheWeek) {
            viewPager.setCurrentItem(3, true);
        } else if (Calendar.THURSDAY == dayOfTheWeek) {
            viewPager.setCurrentItem(4, true);
        } else if (Calendar.FRIDAY == dayOfTheWeek) {
            viewPager.setCurrentItem(4, true);
        } else if (Calendar.SATURDAY == dayOfTheWeek) {
            viewPager.setCurrentItem(4, true);
        }


    }



}