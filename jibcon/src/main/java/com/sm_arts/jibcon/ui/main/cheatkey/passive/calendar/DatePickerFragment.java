package com.sm_arts.jibcon.ui.main.cheatkey.passive.calendar;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.widget.DatePicker;
import android.widget.Toast;

import java.util.Calendar;

import static com.facebook.FacebookSdk.getApplicationContext;


/**
 * Created by jahid on 12/10/15.
 */
public class DatePickerFragment extends DialogFragment
         implements DatePickerDialog.OnDateSetListener {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the current date as the default date in the picker
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        // Create a new instance of DatePickerDialog and return it
        return new DatePickerDialog(getActivity(), this, year, month, day);
    }

    public void onDateSet(DatePicker view, int year, int month, int day) {
        // Do something with the date chosen by the user
       // Button tv1= (Button) getActivity().findViewById(R.id.date);
        //tv1.setText(view.getYear()+"년"+view.getMonth()+"월"+view.getDayOfMonth()+"일");
        Toast.makeText(getApplicationContext(),
                view.getYear()+"년"+view.getMonth()+"월"+view.getDayOfMonth()+"일",
                Toast.LENGTH_SHORT).show();

    }


}