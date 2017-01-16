package org.tpl.fitnesszone.fragment;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;

import org.tpl.fitnesszone.activity.WorkoutTrackerActivity;

import java.util.Calendar;

public class DatePickerFragment extends DialogFragment {

    // onCreateDialog(Bundle) used instead of onCreateView(LayoutInflater, ViewGroup, Bundle)
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        // Use the current date as the default date in the picker
        final Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        // Create a new instance of the DatePickerDialog and return it
        return new DatePickerDialog(getActivity(), (WorkoutTrackerActivity)getActivity(), year, month, day);
    }
}
