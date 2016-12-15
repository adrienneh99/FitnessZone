package org.tpl.fitnesszone.fragment;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.widget.DatePicker;
import java.util.Calendar;

public class DatePickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {

    final Calendar calendar = Calendar.getInstance();

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        // Use the current date as the default date in the picker
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        // Create a new instance of the DatePickerDialog and return it
        return new DatePickerDialog(getActivity(), this, year, month, day);
    }

    // Save the date chosen by the user
    public void onDateSet(DatePicker view, int year, int month, int day) {
        calendar.set(year,month,day);

        // **SAVE THE DATE CHOSEN BY THE USER**
    }


}
