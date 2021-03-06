package com.example.expensemanager;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;

import androidx.fragment.app.DialogFragment;

import java.util.Calendar;

public class DatePickerFragment extends DialogFragment
{
    private Calendar dateTime = Calendar.getInstance();


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Using current date as start Date
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        // Get DatePicker Dialog
        return new DatePickerDialog(getActivity(), (DatePickerDialog.OnDateSetListener)getActivity(),
                year, month, day);
    }
}
