package com.example.trainwithme;

import android.app.DatePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;



public class MainActivity extends AppCompatActivity  {

    EditText userDate;
    TextView userAge;

    EditText userHeight;
    EditText userWeight;
    TextView bmiStatus;
    TextView bmiResult;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        userDate = findViewById(R.id.userDate);
        userAge = findViewById(R.id.userAge);



        bmiStatus = findViewById(R.id.bmiStatus);
        bmiResult = findViewById(R.id.bmiResult);



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


        userHeight = findViewById(R.id.userHeight);
        userHeight.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!(s.toString().equals(""))){
                    calculateBMI();
                }
            }
        });

        userWeight = findViewById(R.id.userWeight);
        userWeight.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!(s.toString().equals(""))){
                    calculateBMI();
                }
            }
        });




    }

    //Methods

    private DatePickerDialog.OnDateSetListener datePickerListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            Calendar c = Calendar.getInstance();
            c.set(Calendar.YEAR, year);
            c.set(Calendar.MONTH, month);
            c.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            String format = new SimpleDateFormat("dd.MM.yyyy").format(c.getTime());
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

    void calculateBMI (){
        String height = userHeight.getText().toString();

        double heightDouble = 0;

        try {
            heightDouble = Double.parseDouble(height);
        } catch (NumberFormatException nfe){
            Toast.makeText(this, "Enter a Number", Toast.LENGTH_SHORT).show();
        }

        heightDouble = heightDouble * 1/100;
        heightDouble = heightDouble * heightDouble;

        String weight = userWeight.getText().toString();

        double weightDouble = 0;

        try {
            weightDouble = Double.parseDouble(weight);
        } catch (NumberFormatException nfe){
            Toast.makeText(this, "Enter a Number", Toast.LENGTH_SHORT).show();
        }

        double bmiResultDouble = Math.round(weightDouble / heightDouble);

        bmiResult.setText(Double.toString(bmiResultDouble));
        bmiStatus.setText(displayBMI(bmiResultDouble));
    }

    String displayBMI(double bmiResultDouble) {
        String bmiLabel;

        if(Double.compare(bmiResultDouble, 18.5f) <= 0) {
            bmiLabel = getString(R.string.under_weight);
        } else if (Double.compare(bmiResultDouble, 18.5f) > 0 && Double.compare(bmiResultDouble, 25f) <= 0){
            bmiLabel = getString(R.string.healthy_weight);
        } else if (Double.compare(bmiResultDouble, 25f) > 0 && Double.compare(bmiResultDouble, 30f) <= 0){
            bmiLabel = getString(R.string.over_weight);
        }else {
            bmiLabel = getString(R.string.obese);
        }
        return bmiLabel;
    }




    //final }
}
