package org.tpl.fitnesszone.activity;

import android.app.DatePickerDialog;
import android.app.DialogFragment;
import android.content.Context;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import org.tpl.fitnesszone.R;
import org.tpl.fitnesszone.fragment.DatePickerFragment;
import org.tpl.fitnesszone.model.EquipmentExercise;
import org.tpl.fitnesszone.model.Workout;
import org.tpl.fitnesszone.model.WorkoutTracker;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import static android.R.attr.breadCrumbShortTitle;
import static android.R.attr.checked;

public class WorkoutTrackerActivity extends AppCompatActivity
        implements
        DatePickerDialog.OnDateSetListener,
        AdapterView.OnItemSelectedListener {

    private List<Workout> userWorkout = new ArrayList<>();
    private WorkoutTracker userWorkoutTracker = new WorkoutTracker();
    private Spinner spinner1;
    private Spinner spinner2;
    private Spinner spinner3;
    private Spinner spinner4;
    private Spinner spinner5;
    private EquipmentExercise[] equipmentExercises;
    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM-dd-yyyy");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout_tracker);

        Toolbar fzToolbar = (Toolbar) findViewById(R.id.fz_toolbar);
        fzToolbar.setTitle(R.string.daily_workout_tracker);
        setSupportActionBar(fzToolbar);

        // Get a support ActionBar corresponding to this toolbar
        ActionBar ab = getSupportActionBar();

        // Enable the Up button
        ab.setDisplayHomeAsUpEnabled(true);

        for (int i = 0; i < 5; i++){
            userWorkout.add(new Workout());
        }

        initializeData();

        // Supply each spinner with the array using an instance of ArrayAdapter
        spinner1 = (Spinner) findViewById(R.id.exercise_spinner1);
        spinner2 = (Spinner) findViewById(R.id.exercise_spinner2);
        spinner3 = (Spinner) findViewById(R.id.exercise_spinner3);
        spinner4 = (Spinner) findViewById(R.id.exercise_spinner4);
        spinner5 = (Spinner) findViewById(R.id.exercise_spinner5);

        // Create a new ArrayList and populate it with the exercise names
        ArrayList<String> equipmentExerciseNames = new ArrayList<String>();
        for (int i = 0; i < equipmentExercises.length; i++) {
            equipmentExerciseNames.add(equipmentExercises[i].getExerciseName());
        }

        // Create an ArrayAdapter using the string ArrayList and default simple spinner
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_dropdown_item, equipmentExerciseNames);

        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Apply the adapter to each spinner
        spinner1.setAdapter(adapter);
        spinner2.setAdapter(adapter);
        spinner3.setAdapter(adapter);
        spinner4.setAdapter(adapter);
        spinner5.setAdapter(adapter);

        spinner1.setOnItemSelectedListener(this);
        spinner2.setOnItemSelectedListener(this);
        spinner3.setOnItemSelectedListener(this);
        spinner4.setOnItemSelectedListener(this);
        spinner5.setOnItemSelectedListener(this);
    }

    // Specify the options menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_workout_tracker, menu);
        return true;
    }

    // Method to show the DatePickerDialog when the user clicks the Date button
    public void showDatePickerDialog(View v) {
        DialogFragment newFragment = new DatePickerFragment();
        newFragment.show(getFragmentManager(), "datePicker");
    }

    // Method required by the DatePickerDialog.OnDateSetListener interface
    // implementation
    @Override
    public void onDateSet(DatePicker view, int year, int month, int day) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, day);
        userWorkoutTracker.setWorkoutDate(calendar);
    }

    // Load equipmentExercise info from the json file in the raw resource folder
    // and then use info to populate each spinner with the exercise names.
    private void initializeData() {
        Gson gson = new Gson();
        InputStream inputStream = getResources().openRawResource(R.raw.equipment_exercise);
        Reader rd = new BufferedReader(new InputStreamReader(inputStream));
        equipmentExercises = gson.fromJson(rd, EquipmentExercise[].class);
    }

    // Method required by the AdapterView.OnItemSelectedListener interface
    // implementation. Confirm which spinner was selected, get the spinner item,
    // and assign it to the corresponding userWorkout exerciseName
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        if (parent.getId() == R.id.exercise_spinner1)
        {
            String spinner1Item = parent.getItemAtPosition(position).toString();
            userWorkout.get(0).setExerciseName(spinner1Item);
        }
        else if (parent.getId() == R.id.exercise_spinner2)
        {
            String spinner2Item = parent.getItemAtPosition(position).toString();
            userWorkout.get(1).setExerciseName(spinner2Item);
        }
        else if (parent.getId() == R.id.exercise_spinner3)
        {
            String spinner3Item = parent.getItemAtPosition(position).toString();
            userWorkout.get(2).setExerciseName(spinner3Item);
        }
        else if (parent.getId() == R.id.exercise_spinner4)
        {
            String spinner4Item = parent.getItemAtPosition(position).toString();
            userWorkout.get(3).setExerciseName(spinner4Item);
        }
        else if (parent.getId() == R.id.exercise_spinner5)
        {
            String spinner5Item = parent.getItemAtPosition(position).toString();
            userWorkout.get(4).setExerciseName(spinner5Item);
        }
    }

    // Method required by the AdapterView.OnItemSelectedListener interface
    // implementation
    public void onNothingSelected(AdapterView<?> parent) {

    }

    // Method to handle the click events for the arm, leg, and cardio checkboxes
    public void onCheckboxClicked(View view) {

        // Determine if the checkbox is clicked
        // Returns true if the checkbox is checked
        // Returns false if the checkbox is not checked
        boolean checked = ((CheckBox) view).isChecked();

        switch (view.getId()) {
            case R.id.arms:
                userWorkoutTracker.setArmFocus(checked);
                break;
            case R.id.legs:
                userWorkoutTracker.setLegFocus(checked);
                break;
            case R.id.cardio:
                userWorkoutTracker.setCardioFocus(checked);
                break;
        }
    }

    public void setUserCurrentWeight() {
        EditText userCurrentWeight = (EditText) findViewById(R.id.user_current_weight);

        userCurrentWeight.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                boolean handled = false;
                if(actionId == EditorInfo.IME_ACTION_DONE){
                    handled = true;
                }
                return handled;
            }
        });

        if ((userCurrentWeight.getText() != null) && !(userCurrentWeight.getText().toString().equals(""))) {
            userWorkoutTracker.setCurrentWeight(Integer.parseInt(userCurrentWeight.getText().toString()));
        }
    }

    // Get user reps sets 1,2,3 and user cardio time and assign them to the userWorkout
    public void setUserRepsAndCardioTime() {

        // Get row 1 (array index 0)
        EditText userRepsSet1Text1 = (EditText) findViewById(R.id.reps_set1_text1);
        if ((userRepsSet1Text1.getText() != null) && !(userRepsSet1Text1.getText().toString().equals(""))) {
            userWorkout.get(0).setRepsSet1(Integer.parseInt(userRepsSet1Text1.getText().toString()));
        }

        EditText userRepsSet2Text1 = (EditText) findViewById(R.id.reps_set2_text1);
        if ((userRepsSet2Text1.getText() != null) && !(userRepsSet2Text1.getText().toString().equals(""))) {
            userWorkout.get(0).setRepsSet2(Integer.parseInt(userRepsSet2Text1.getText().toString()));
        }

        EditText userRepsSet3Text1 = (EditText) findViewById(R.id.reps_set3_text1);
        if ((userRepsSet3Text1.getText() != null) && !(userRepsSet3Text1.getText().toString().equals(""))) {
            userWorkout.get(0).setRepsSet3(Integer.parseInt(userRepsSet3Text1.getText().toString()));
        }

        EditText userCardioTimeText1 = (EditText) findViewById(R.id.cardio_time_text1);
        if (userCardioTimeText1.getText() != null) {
            userWorkout.get(0).setCardioTime(userCardioTimeText1.getText().toString());
        }

        // Get row 2 (array index 1)
        EditText userRepsSet1Text2 = (EditText) findViewById(R.id.reps_set1_text2);
        if ((userRepsSet1Text2.getText() != null) && !(userRepsSet1Text2.getText().toString().equals(""))) {
            userWorkout.get(1).setRepsSet1(Integer.parseInt(userRepsSet1Text2.getText().toString()));
        }

        EditText userRepsSet2Text2 = (EditText) findViewById(R.id.reps_set2_text2);
        if ((userRepsSet2Text2.getText() != null) && !(userRepsSet2Text2.getText().toString().equals(""))) {
            userWorkout.get(1).setRepsSet2(Integer.parseInt(userRepsSet2Text2.getText().toString()));
        }

        EditText userRepsSet3Text2 = (EditText) findViewById(R.id.reps_set3_text2);
        if ((userRepsSet3Text2.getText() != null) && !(userRepsSet3Text2.getText().toString().equals(""))) {
            userWorkout.get(1).setRepsSet3(Integer.parseInt(userRepsSet3Text2.getText().toString()));
        }

        EditText userCardioTimeText2 = (EditText) findViewById(R.id.cardio_time_text2);
        if (userCardioTimeText2.getText() != null) {
            userWorkout.get(1).setCardioTime(userCardioTimeText2.getText().toString());
        }

        // Get row 3 (array index 2)
        EditText userRepsSet1Text3 = (EditText) findViewById(R.id.reps_set1_text3);
        if ((userRepsSet1Text3.getText() != null) && !(userRepsSet1Text3.getText().toString().equals(""))) {
            userWorkout.get(2).setRepsSet1(Integer.parseInt(userRepsSet1Text3.getText().toString()));
        }

        EditText userRepsSet2Text3 = (EditText) findViewById(R.id.reps_set2_text3);
        if ((userRepsSet2Text3.getText() != null) && !(userRepsSet2Text3.getText().toString().equals(""))) {
            userWorkout.get(2).setRepsSet2(Integer.parseInt(userRepsSet2Text3.getText().toString()));
        }

        EditText userRepsSet3Text3 = (EditText) findViewById(R.id.reps_set3_text3);
        if ((userRepsSet3Text3.getText() != null) && !(userRepsSet3Text3.getText().toString().equals(""))) {
            userWorkout.get(2).setRepsSet3(Integer.parseInt(userRepsSet3Text3.getText().toString()));
        }

        EditText userCardioTimeText3 = (EditText) findViewById(R.id.cardio_time_text3);
        if (userCardioTimeText3.getText() != null) {
            userWorkout.get(2).setCardioTime(userCardioTimeText3.getText().toString());
        }

        // Get row 4 (array index 3)
        EditText userRepsSet1Text4 = (EditText) findViewById(R.id.reps_set1_text4);
        if ((userRepsSet1Text4.getText() != null) && !(userRepsSet1Text4.getText().toString().equals(""))) {
            userWorkout.get(3).setRepsSet1(Integer.parseInt(userRepsSet1Text4.getText().toString()));
        }

        EditText userRepsSet2Text4 = (EditText) findViewById(R.id.reps_set2_text4);
        if ((userRepsSet2Text4.getText() != null) && !(userRepsSet2Text4.getText().toString().equals(""))) {
            userWorkout.get(3).setRepsSet2(Integer.parseInt(userRepsSet2Text4.getText().toString()));
        }

        EditText userRepsSet3Text4 = (EditText) findViewById(R.id.reps_set3_text4);
        if ((userRepsSet3Text4.getText() != null) && !(userRepsSet3Text4.getText().toString().equals(""))) {
            userWorkout.get(3).setRepsSet3(Integer.parseInt(userRepsSet3Text4.getText().toString()));
        }

        EditText userCardioTimeText4 = (EditText) findViewById(R.id.cardio_time_text4);
        if (userCardioTimeText4.getText() != null) {
            userWorkout.get(3).setCardioTime(userCardioTimeText4.getText().toString());
        }

        // Get row 5 (array index 4)
        EditText userRepsSet1Text5 = (EditText) findViewById(R.id.reps_set1_text5);
        if ((userRepsSet1Text5.getText() != null) && !(userRepsSet1Text5.getText().toString().equals(""))) {
            userWorkout.get(4).setRepsSet1(Integer.parseInt(userRepsSet1Text5.getText().toString()));
        }

        EditText userRepsSet2Text5 = (EditText) findViewById(R.id.reps_set2_text5);
        if ((userRepsSet2Text5.getText() != null) && !(userRepsSet2Text5.getText().toString().equals(""))) {
            userWorkout.get(4).setRepsSet2(Integer.parseInt(userRepsSet2Text5.getText().toString()));
        }

        EditText userRepsSet3Text5 = (EditText) findViewById(R.id.reps_set3_text5);
        if ((userRepsSet3Text5.getText() != null) && !(userRepsSet3Text5.getText().toString().equals(""))) {
            userWorkout.get(4).setRepsSet3(Integer.parseInt(userRepsSet3Text5.getText().toString()));
        }

        EditText userCardioTimeText5 = (EditText) findViewById(R.id.cardio_time_text5);
        if (userCardioTimeText5.getText() != null) {
            userWorkout.get(4).setCardioTime(userCardioTimeText5.getText().toString());
        }
    }

    // onClick menu item method to save the user's workout tracker info
    public void saveWorkoutTracker(MenuItem item) {

        setUserCurrentWeight();

        setUserRepsAndCardioTime();

        // Create a Gson object to handle the Java to JSON serialization
        Gson gson = new Gson();

        // Convert the userWorkoutTracker object to JSON
        String workoutTrackerJson = gson.toJson(userWorkoutTracker);

        // Save the fitnessJournal JSON to the internal storage
        String filename = "workout_tracker";
        FileOutputStream fileOutputStream;

        try {
            fileOutputStream = openFileOutput(filename, Context.MODE_PRIVATE);
            fileOutputStream.write(workoutTrackerJson.getBytes());
            fileOutputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        String todaysArmFocus = "";
        String todaysLegFocus = "";
        String todaysCardioFocus = "";

        if (userWorkoutTracker.isArmFocus() && (userWorkoutTracker.isLegFocus() || userWorkoutTracker.isCardioFocus())){
            todaysArmFocus = "Arms, ";
        }
        else if (userWorkoutTracker.isArmFocus() && (!userWorkoutTracker.isLegFocus() || !userWorkoutTracker.isCardioFocus())){
            todaysArmFocus = "Arms";
        }
        else if (!userWorkoutTracker.isArmFocus() && (userWorkoutTracker.isLegFocus() || userWorkoutTracker.isCardioFocus())){
            todaysArmFocus = "";
        };

        if (userWorkoutTracker.isLegFocus() && userWorkoutTracker.isCardioFocus()){
            todaysLegFocus = "Legs, ";
        }
        else if (userWorkoutTracker.isLegFocus() && (!userWorkoutTracker.isArmFocus() || !userWorkoutTracker.isCardioFocus())){
            todaysLegFocus = "Legs";
        }
        else if (!userWorkoutTracker.isLegFocus()){
            todaysLegFocus = "";
        };

        if (userWorkoutTracker.isCardioFocus()){
            todaysCardioFocus = "Cardio";
        }
        else if (!userWorkoutTracker.isCardioFocus()){
            todaysCardioFocus = "";
        };


        CharSequence text = "Congratulations! Your workout has been saved." +
                "\n" +
                "\n" +
                "Workout date: " + simpleDateFormat.format(userWorkoutTracker.getWorkoutDate().getTime()) +
                "\n" +
                "\n" +
                "Current weight: " + userWorkoutTracker.getCurrentWeight() +
                "\n" +
                "\n" +
                "Today's focus: " +
                todaysArmFocus +
                todaysLegFocus +
                todaysCardioFocus +
                "\n" +
                "\n" +
                "Today's exercises: " +
                userWorkout.get(0).getExerciseName() + " " +
                userWorkout.get(0).getRepsSet1() + " " +
                userWorkout.get(0).getRepsSet2() + " " +
                userWorkout.get(0).getRepsSet3() + " " +
                userWorkout.get(0).getCardioTime() + ", " +
                "\n" +
                userWorkout.get(1).getExerciseName() + " " +
                userWorkout.get(1).getRepsSet1() + " " +
                userWorkout.get(1).getRepsSet2() + " " +
                userWorkout.get(1).getRepsSet3() + " " +
                userWorkout.get(1).getCardioTime() + ", " +
                "\n" +
                userWorkout.get(2).getExerciseName() + " " +
                userWorkout.get(2).getRepsSet1() + " " +
                userWorkout.get(2).getRepsSet2() + " " +
                userWorkout.get(2).getRepsSet3() + " " +
                userWorkout.get(2).getCardioTime() + ", " +
                "\n" +
                userWorkout.get(3).getExerciseName() + " " +
                userWorkout.get(3).getRepsSet1() + " " +
                userWorkout.get(3).getRepsSet2() + " " +
                userWorkout.get(3).getRepsSet3() + " " +
                userWorkout.get(3).getCardioTime() + ", " +
                "\n" +
                userWorkout.get(4).getExerciseName() + " " +
                userWorkout.get(4).getRepsSet1() + " " +
                userWorkout.get(4).getRepsSet2() + " " +
                userWorkout.get(4).getRepsSet3() + " " +
                userWorkout.get(4).getCardioTime() + ", ";

        int duration = Toast.LENGTH_LONG;
        Toast.makeText(this, text, duration).show();
    }
}
