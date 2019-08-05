package com.example.trainwithme;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class MyProfileFragment extends Fragment  {

    /*
    Things left to do in this fragment:
    listeners for radio and checkbox buttons
    pass training data through listeners
     */

    CollectExerciseListListener collectExerciseListListener;


    public interface CollectExerciseListListener {

        public void exerciseListSend(ArrayList<String> collectedExerciseList);
    }

    //Initialize views here:

    EditText userDate;
    TextView userAge;

    EditText userHeight;
    EditText userWeight;
    TextView bmiStatus;
    TextView bmiResult;

    RadioGroup beginnerAdvancedGroup;
    RadioButton trainingPlan;


    CheckBox checkBox;
    Button saveAndNext;

    //Initialize other fields here
    //HashMaps and ArrayList to maintain different training plan - other options might be better (to check)


    HashMap <String, HashMap<String, ArrayList<String>>> trainingData = new HashMap<>();

    HashMap<String, ArrayList<String>> beginnerData = new HashMap<>();

    HashMap<String, ArrayList<String>> advancedData = new HashMap<>();



    ArrayList<String> absBeginner = new ArrayList<>();

    ArrayList<String> aerobicsBeginner = new ArrayList<>();

    ArrayList<String> coreBeginner = new ArrayList<>();

    ArrayList<String> fatLoseBeginner = new ArrayList<>();

    ArrayList<String> spinningBeginner = new ArrayList<>();

    ArrayList<String> trxBeginner = new ArrayList<>();


    ArrayList<String> absAdvanced = new ArrayList<>();

    ArrayList<String> aerobicsAdvanced = new ArrayList<>();

    ArrayList<String> coreAdvanced = new ArrayList<>();

    ArrayList<String> fatLoseAdvanced = new ArrayList<>();

    ArrayList<String> spinningAdvanced = new ArrayList<>();

    ArrayList<String> trxAdvanced = new ArrayList<>();

    HashMap<String, ArrayList<String>> exercisePlan = new HashMap<>();
    ArrayList<String> exerciseItem = new ArrayList<>();




    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_my_profile, container, false);






        absBeginner.add("Exercise 1 ABS Beginner");

        absBeginner.add("Exercise 2 ABS Beginner");

        absBeginner.add("Exercise 3 ABS Beginner");

        beginnerData.put("ABS Exercises for Beginners", absBeginner);



        aerobicsBeginner.add("Exercise 1 Aerobics Beginner");

        aerobicsBeginner.add("Exercise 2 Aerobics Beginner");

        aerobicsBeginner.add("Exercise 3 Aerobics Beginner");

        beginnerData.put("Aerobics Exercises for Beginners", aerobicsBeginner);



        coreBeginner.add("Exercise 1 Core Beginner");

        coreBeginner.add("Exercise 2 Core Beginner");

        coreBeginner.add("Exercise 3 Core Beginner");

        beginnerData.put("Core Exercises for Beginners", coreBeginner);



        fatLoseBeginner.add("Exercise 1 Fat Lose Beginner");

        fatLoseBeginner.add("Exercise 2 Fat Lose Beginner");

        fatLoseBeginner.add("Exercise 3 Fat Lose Beginner");

        beginnerData.put("Fat Lose Exercises for Beginners", fatLoseBeginner);



        spinningBeginner.add("Exercise 1 Spinning Beginner");

        spinningBeginner.add("Exercise 2 Spinning Beginner");

        spinningBeginner.add("Exercise 3 Spinning Beginner");

        beginnerData.put("Spinning Exercises for Beginners", spinningBeginner);



        trxBeginner.add("Exercise 1 TRX Beginner");

        trxBeginner.add("Exercise 2 TRX Beginner");

        trxBeginner.add("Exercise 3 TRX Beginner");

        beginnerData.put("TRX Exercises for Beginners",  trxBeginner);



        absAdvanced.add("Exercise 1 ABS Advanced");

        absAdvanced.add("Exercise 2 ABS Advanced");

        absAdvanced.add("Exercise 3 ABS Advanced");

        advancedData.put("ABS Exercises for Advanced", absAdvanced);



        aerobicsAdvanced.add("Exercise 1 Aerobics Advanced");

        aerobicsAdvanced.add("Exercise 2 Aerobics Advanced");

        aerobicsAdvanced.add("Exercise 3 Aerobics Advanced");

        advancedData.put("Aerobics Exercises for Advanced", aerobicsAdvanced);



        coreAdvanced.add("Exercise 1 Core Advanced");

        coreAdvanced.add("Exercise 2 Core Advanced");

        coreAdvanced.add("Exercise 3 Core Advanced");

        advancedData.put("Core Exercises for Advanced", coreAdvanced);



        fatLoseAdvanced.add("Exercise 1 Fat Lose Advanced");

        fatLoseAdvanced.add("Exercise 2 Fat Lose Advanced");

        fatLoseAdvanced.add("Exercise 3 Fat Lose Advanced");

        advancedData.put("Fat Lose Exercises for Advanced", fatLoseAdvanced);



        spinningAdvanced.add("Exercise 1 Spinning Advanced");

        spinningAdvanced.add("Exercise 2 Spinning Advanced");

        spinningAdvanced.add("Exercise 3 Spinning Advanced");

        advancedData.put("Spinning Exercises for Advanced", spinningAdvanced);



        trxAdvanced.add("Exercise 1 TRX Advanced");

        trxAdvanced.add("Exercise 2 TRX Advanced");

        trxAdvanced.add("Exercise 3 TRX Advanced");

        advancedData.put("TRX Exercises for Advanced",  trxAdvanced);



        trainingData.put("Training Plan for Beginner", beginnerData);

        trainingData.put("Training Plan for Advanced", advancedData);

        //findViewsById here


        userDate = (EditText) view.findViewById(R.id.userDate);
        userDate.setInputType(InputType.TYPE_NULL);


        userAge = (TextView) view.findViewById(R.id.userAge);


        bmiStatus = (TextView) view.findViewById(R.id.bmiStatus);
        bmiResult = (TextView) view.findViewById(R.id.bmiResult);


        saveAndNext = view.findViewById(R.id.save_next);

        beginnerAdvancedGroup = (RadioGroup) view.findViewById(R.id.radioGroup_beginner_advanced);

        checkBox = view.findViewById(R.id.abs);

        //listeners here


        userDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar c = Calendar.getInstance();
                int mYear = c.get(Calendar.YEAR);
                int mMonth = c.get(Calendar.MONTH);
                int mDay = c.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog dateDialog = new DatePickerDialog(v.getContext(), datePickerListener, mYear, mMonth, mDay);
                dateDialog.getDatePicker().setMaxDate(new Date().getTime());
                dateDialog.show();
            }
        });


        userHeight = (EditText) view.findViewById(R.id.userHeight);
        userHeight.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!(s.toString().equals(""))) {
                    calculateBMI();
                }
            }
        });

        userWeight = (EditText) view.findViewById(R.id.userWeight);
        userWeight.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!(s.toString().equals(""))) {
                    calculateBMI();
                }
            }
        });

      beginnerAdvancedGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                switch (checkedId){
                    case R.id.beginner:
                        //trainingData.get("Training Plan for Beginner");

                        break;
                    case R.id.advanced:
                        //trainingData.get("Training Plan for Advanced");
                        break;
                }
            }
        });



        saveAndNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Mockup data to see if passed to other fragments through MainActivity

                exerciseItem.add("Exercise 1 ABS Beginner");

                exerciseItem.add("Exercise 2 ABS Beginner");

                exerciseItem.add("Exercise 3 ABS Beginner");

                exercisePlan.put("ItemList", exerciseItem);


                List<String> keysAsArray = new ArrayList<String>(exercisePlan.keySet());
                Random chooseRandomKey = new Random();
                ArrayList<String> collectedExerciseList = exercisePlan.get(keysAsArray.get(chooseRandomKey.nextInt(keysAsArray.size())));
                    collectExerciseListListener.exerciseListSend(collectedExerciseList);

            }



        });




        return view;
    }


    public void addListenerOnButton() {

    }


    public DatePickerDialog.OnDateSetListener datePickerListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            Calendar c = Calendar.getInstance();
            c.set(Calendar.YEAR, year);
            c.set(Calendar.MONTH, month);
            c.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            @SuppressLint("SimpleDateFormat") String format = new SimpleDateFormat("dd.MM.yyyy").format(c.getTime());
            userDate.setText(format);
            userAge.setText(Integer.toString(calculateAge(c.getTimeInMillis())));
        }
    };

    int calculateAge(long date) {
        Calendar dob = Calendar.getInstance();
        dob.setTimeInMillis(date);
        Calendar today = Calendar.getInstance();
        int age = today.get(Calendar.YEAR) - dob.get(Calendar.YEAR);
        if (today.get(Calendar.DAY_OF_MONTH) < dob.get(Calendar.DAY_OF_MONTH)) {
            age--;
        }
        return age;
    }

    void calculateBMI() {
        String height = userHeight.getText().toString();

        double heightDouble = 0;

        try {
            heightDouble = Double.parseDouble(height);
        } catch (NumberFormatException nfe) {
            Toast.makeText(getActivity(), "Enter a Number", Toast.LENGTH_SHORT).show();
        }

        heightDouble = heightDouble * 1 / 100;
        heightDouble = heightDouble * heightDouble;

        String weight = userWeight.getText().toString();

        double weightDouble = 0;

        try {
            weightDouble = Double.parseDouble(weight);
        } catch (NumberFormatException nfe) {
            Toast.makeText(getActivity(), "Enter a Number", Toast.LENGTH_SHORT).show();
        }

        double bmiResultDouble = Math.round(weightDouble / heightDouble);

        bmiResult.setText(Double.toString(bmiResultDouble));
        bmiStatus.setText(displayBMI(bmiResultDouble));
    }

    String displayBMI(double bmiResultDouble) {
        String bmiLabel;

        if (Double.compare(bmiResultDouble, 18.5f) <= 0) {
            bmiLabel = getString(R.string.under_weight);
        } else if (Double.compare(bmiResultDouble, 18.5f) > 0 && Double.compare(bmiResultDouble, 25f) <= 0) {
            bmiLabel = getString(R.string.healthy_weight);
        } else if (Double.compare(bmiResultDouble, 25f) > 0 && Double.compare(bmiResultDouble, 30f) <= 0) {
            bmiLabel = getString(R.string.over_weight);
        } else {
            bmiLabel = getString(R.string.obese);
        }
        return bmiLabel;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Activity activity = (Activity) context;

        try {

            collectExerciseListListener = (CollectExerciseListListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString() + " must implement exerciseListSend");
        }








    }
}


