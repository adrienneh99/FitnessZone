package org.tpl.fitnesszone.activity;

import android.app.DatePickerDialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.SharedPreferences;
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
        spinner1 = (Spinner) findViewById(R.id.exercise1_spinner1);
        spinner2 = (Spinner) findViewById(R.id.exercise2_spinner2);
        spinner3 = (Spinner) findViewById(R.id.exercise3_spinner3);
        spinner4 = (Spinner) findViewById(R.id.exercise4_spinner4);
        spinner5 = (Spinner) findViewById(R.id.exercise5_spinner5);

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
        if (parent.getId() == R.id.exercise1_spinner1)
        {
            String spinner1Item = parent.getItemAtPosition(position).toString();
            userWorkout.get(0).setExerciseName(spinner1Item);
        }
        else if (parent.getId() == R.id.exercise2_spinner2)
        {
            String spinner2Item = parent.getItemAtPosition(position).toString();
            userWorkout.get(1).setExerciseName(spinner2Item);
        }
        else if (parent.getId() == R.id.exercise3_spinner3)
        {
            String spinner3Item = parent.getItemAtPosition(position).toString();
            userWorkout.get(2).setExerciseName(spinner3Item);
        }
        else if (parent.getId() == R.id.exercise4_spinner4)
        {
            String spinner4Item = parent.getItemAtPosition(position).toString();
            userWorkout.get(3).setExerciseName(spinner4Item);
        }
        else if (parent.getId() == R.id.exercise5_spinner5)
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

        // Exercise 1, Reps Set 1
        EditText exercise1RepsSet1 = (EditText) findViewById(R.id.exercise1_reps_set1);
        exercise1RepsSet1.setOnEditorActionListener(new TextView.OnEditorActionListener(){
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                boolean handledExercise1RepsSet1 = false;
                if(actionId == EditorInfo.IME_ACTION_NEXT){
                    handledExercise1RepsSet1 = true;
                }
                return handledExercise1RepsSet1;
            }
        });
        if ((exercise1RepsSet1.getText() != null) && !(exercise1RepsSet1.getText().toString().equals(""))) {
            userWorkout.get(0).setRepsSet1(Integer.parseInt(exercise1RepsSet1.getText().toString()));
        }

        // Exercise 1, Reps Set 2
        EditText exercise1RepsSet2 = (EditText) findViewById(R.id.exercise1_reps_set2);
        exercise1RepsSet2.setOnEditorActionListener(new TextView.OnEditorActionListener(){
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                boolean handledExercise1RepsSet2 = false;
                if(actionId == EditorInfo.IME_ACTION_NEXT){
                    handledExercise1RepsSet2 = true;
                }
                return handledExercise1RepsSet2;
            }
        });
        if ((exercise1RepsSet2.getText() != null) && !(exercise1RepsSet2.getText().toString().equals(""))) {
            userWorkout.get(0).setRepsSet2(Integer.parseInt(exercise1RepsSet2.getText().toString()));
        }

        // Exercise 1, Reps Set 3
        EditText exercise1RepsSet3 = (EditText) findViewById(R.id.exercise1_reps_set3);
        exercise1RepsSet3.setOnEditorActionListener(new TextView.OnEditorActionListener(){
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                boolean handledExercise1RepsSet3 = false;
                if(actionId == EditorInfo.IME_ACTION_NEXT){
                    handledExercise1RepsSet3 = true;
                }
                return handledExercise1RepsSet3;
            }
        });
        if ((exercise1RepsSet3.getText() != null) && !(exercise1RepsSet3.getText().toString().equals(""))) {
            userWorkout.get(0).setRepsSet3(Integer.parseInt(exercise1RepsSet3.getText().toString()));
        }

        // Exercise 1, Cardio Time
        EditText exercise1CardioTime = (EditText) findViewById(R.id.exercise1_cardio_time);
        exercise1CardioTime.setOnEditorActionListener(new TextView.OnEditorActionListener(){
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                boolean handledExercise1CardioTime = false;
                if(actionId == EditorInfo.IME_ACTION_DONE){
                    handledExercise1CardioTime = true;
                }
                return handledExercise1CardioTime;
            }
        });
        if (exercise1CardioTime.getText() != null) {
            userWorkout.get(0).setCardioTime(exercise1CardioTime.getText().toString());
        }

        // Get row 2 (array index 1)

        // Exercise 2, Reps Set 1
        EditText exercise2RepsSet1 = (EditText) findViewById(R.id.exercise2_reps_set1);
        exercise2RepsSet1.setOnEditorActionListener(new TextView.OnEditorActionListener(){
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                boolean handledExercise2RepsSet1 = false;
                if(actionId == EditorInfo.IME_ACTION_NEXT){
                    handledExercise2RepsSet1 = true;
                }
                return handledExercise2RepsSet1;
            }
        });
        if ((exercise2RepsSet1.getText() != null) && !(exercise2RepsSet1.getText().toString().equals(""))) {
            userWorkout.get(1).setRepsSet1(Integer.parseInt(exercise2RepsSet1.getText().toString()));
        }

        // Exercise 2, Reps Set 2
        EditText exercise2RepsSet2 = (EditText) findViewById(R.id.exercise2_reps_set2);
        exercise2RepsSet2.setOnEditorActionListener(new TextView.OnEditorActionListener(){
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                boolean handledExercise2RepsSet2 = false;
                if(actionId == EditorInfo.IME_ACTION_NEXT){
                    handledExercise2RepsSet2 = true;
                }
                return handledExercise2RepsSet2;
            }
        });
        if ((exercise2RepsSet2.getText() != null) && !(exercise2RepsSet2.getText().toString().equals(""))) {
            userWorkout.get(1).setRepsSet2(Integer.parseInt(exercise2RepsSet2.getText().toString()));
        }

        // Exercise 2, Reps Set 3
        EditText exercise2RepsSet3 = (EditText) findViewById(R.id.exercise2_reps_set3);
        exercise2RepsSet3.setOnEditorActionListener(new TextView.OnEditorActionListener(){
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                boolean handledExercise2RepsSet3 = false;
                if(actionId == EditorInfo.IME_ACTION_NEXT){
                    handledExercise2RepsSet3 = true;
                }
                return handledExercise2RepsSet3;
            }
        });
        if ((exercise2RepsSet3.getText() != null) && !(exercise2RepsSet3.getText().toString().equals(""))) {
            userWorkout.get(1).setRepsSet3(Integer.parseInt(exercise2RepsSet3.getText().toString()));
        }

        // Exercise 2, Cardio Time
        EditText exercise2CardioTime = (EditText) findViewById(R.id.exercise2_cardio_time);
        exercise2CardioTime.setOnEditorActionListener(new TextView.OnEditorActionListener(){
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                boolean handledExercise2CardioTime = false;
                if(actionId == EditorInfo.IME_ACTION_DONE){
                    handledExercise2CardioTime = true;
                }
                return handledExercise2CardioTime;
            }
        });
        if (exercise2CardioTime.getText() != null) {
            userWorkout.get(1).setCardioTime(exercise2CardioTime.getText().toString());
        }

        // Get row 3 (array index 2)

        // Exercise 3, Reps Set 1
        EditText exercise3RepsSet1 = (EditText) findViewById(R.id.exercise3_reps_set1);
        exercise3RepsSet1.setOnEditorActionListener(new TextView.OnEditorActionListener(){
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                boolean handledExercise3RepsSet1 = false;
                if(actionId == EditorInfo.IME_ACTION_NEXT){
                    handledExercise3RepsSet1 = true;
                }
                return handledExercise3RepsSet1;
            }
        });
        if ((exercise3RepsSet1.getText() != null) && !(exercise3RepsSet1.getText().toString().equals(""))) {
            userWorkout.get(2).setRepsSet1(Integer.parseInt(exercise3RepsSet1.getText().toString()));
        }

        // Exercise 3, Reps Set 2
        EditText exercise3RepsSet2 = (EditText) findViewById(R.id.exercise3_reps_set2);
        exercise3RepsSet2.setOnEditorActionListener(new TextView.OnEditorActionListener(){
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                boolean handledExercise3RepsSet2 = false;
                if(actionId == EditorInfo.IME_ACTION_NEXT){
                    handledExercise3RepsSet2 = true;
                }
                return handledExercise3RepsSet2;
            }
        });
        if ((exercise3RepsSet2.getText() != null) && !(exercise3RepsSet2.getText().toString().equals(""))) {
            userWorkout.get(2).setRepsSet2(Integer.parseInt(exercise3RepsSet2.getText().toString()));
        }

        // Exercise 3, Reps Set 3
        EditText exercise3RepsSet3 = (EditText) findViewById(R.id.exercise3_reps_set3);
        exercise3RepsSet3.setOnEditorActionListener(new TextView.OnEditorActionListener(){
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                boolean handledExercise3RepsSet3 = false;
                if(actionId == EditorInfo.IME_ACTION_NEXT){
                    handledExercise3RepsSet3 = true;
                }
                return handledExercise3RepsSet3;
            }
        });
        if ((exercise3RepsSet3.getText() != null) && !(exercise3RepsSet3.getText().toString().equals(""))) {
            userWorkout.get(2).setRepsSet3(Integer.parseInt(exercise3RepsSet3.getText().toString()));
        }

        // Exercise 3, Cardio Time
        EditText exercise3CardioTime = (EditText) findViewById(R.id.exercise3_cardio_time);
        exercise3CardioTime.setOnEditorActionListener(new TextView.OnEditorActionListener(){
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                boolean handledExercise3CardioTime = false;
                if(actionId == EditorInfo.IME_ACTION_DONE){
                    handledExercise3CardioTime = true;
                }
                return handledExercise3CardioTime;
            }
        });
        if (exercise3CardioTime.getText() != null) {
            userWorkout.get(2).setCardioTime(exercise3CardioTime.getText().toString());
        }

        // Get row 4 (array index 3)

        // Exercise 4, Reps Set 1
        EditText exercise4RepsSet1 = (EditText) findViewById(R.id.exercise4_reps_set1);
        exercise4RepsSet1.setOnEditorActionListener(new TextView.OnEditorActionListener(){
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                boolean handledExercise4RepsSet1 = false;
                if(actionId == EditorInfo.IME_ACTION_NEXT){
                    handledExercise4RepsSet1 = true;
                }
                return handledExercise4RepsSet1;
            }
        });
        if ((exercise4RepsSet1.getText() != null) && !(exercise4RepsSet1.getText().toString().equals(""))) {
            userWorkout.get(3).setRepsSet1(Integer.parseInt(exercise4RepsSet1.getText().toString()));
        }

        // Exercise 4, Reps Set 2
        EditText exercise4RepsSet2 = (EditText) findViewById(R.id.exercise4_reps_set2);
        exercise4RepsSet2.setOnEditorActionListener(new TextView.OnEditorActionListener(){
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                boolean handledExercise4RepsSet2 = false;
                if(actionId == EditorInfo.IME_ACTION_NEXT){
                    handledExercise4RepsSet2 = true;
                }
                return handledExercise4RepsSet2;
            }
        });
        if ((exercise4RepsSet2.getText() != null) && !(exercise4RepsSet2.getText().toString().equals(""))) {
            userWorkout.get(3).setRepsSet2(Integer.parseInt(exercise4RepsSet2.getText().toString()));
        }

        // Exercise 4, Reps Set 3
        EditText exercise4RepsSet3 = (EditText) findViewById(R.id.exercise4_reps_set3);
        exercise4RepsSet3.setOnEditorActionListener(new TextView.OnEditorActionListener(){
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                boolean handledExercise4RepsSet3 = false;
                if(actionId == EditorInfo.IME_ACTION_NEXT){
                    handledExercise4RepsSet3 = true;
                }
                return handledExercise4RepsSet3;
            }
        });
        if ((exercise4RepsSet3.getText() != null) && !(exercise4RepsSet3.getText().toString().equals(""))) {
            userWorkout.get(3).setRepsSet3(Integer.parseInt(exercise4RepsSet3.getText().toString()));
        }

        // Exercise 4, Cardio Time
        EditText exercise4CardioTime = (EditText) findViewById(R.id.exercise4_cardio_time);
        exercise4CardioTime.setOnEditorActionListener(new TextView.OnEditorActionListener(){
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                boolean handledExercise4CardioTime = false;
                if(actionId == EditorInfo.IME_ACTION_DONE){
                    handledExercise4CardioTime = true;
                }
                return handledExercise4CardioTime;
            }
        });
        if (exercise4CardioTime.getText() != null) {
            userWorkout.get(3).setCardioTime(exercise4CardioTime.getText().toString());
        }

        // Get row 5 (array index 4)

        // Exercise 5, Reps Set 1
        EditText exercise5RepsSet1 = (EditText) findViewById(R.id.exercise5_reps_set1);
        exercise5RepsSet1.setOnEditorActionListener(new TextView.OnEditorActionListener(){
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                boolean handledExercise5RepsSet1 = false;
                if(actionId == EditorInfo.IME_ACTION_NEXT){
                    handledExercise5RepsSet1 = true;
                }
                return handledExercise5RepsSet1;
            }
        });
        if ((exercise5RepsSet1.getText() != null) && !(exercise5RepsSet1.getText().toString().equals(""))) {
            userWorkout.get(4).setRepsSet1(Integer.parseInt(exercise5RepsSet1.getText().toString()));
        }

        // Exercise 5, Reps Set 2
        EditText exercise5RepsSet2 = (EditText) findViewById(R.id.exercise5_reps_set2);
        exercise5RepsSet2.setOnEditorActionListener(new TextView.OnEditorActionListener(){
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                boolean handledExercise5RepsSet2 = false;
                if(actionId == EditorInfo.IME_ACTION_NEXT){
                    handledExercise5RepsSet2 = true;
                }
                return handledExercise5RepsSet2;
            }
        });
        if ((exercise5RepsSet2.getText() != null) && !(exercise5RepsSet2.getText().toString().equals(""))) {
            userWorkout.get(4).setRepsSet2(Integer.parseInt(exercise5RepsSet2.getText().toString()));
        }

        // Exercise 5, Reps Set 3
        EditText exercise5RepsSet3 = (EditText) findViewById(R.id.exercise5_reps_set3);
        exercise5RepsSet3.setOnEditorActionListener(new TextView.OnEditorActionListener(){
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                boolean handledExercise5RepsSet3 = false;
                if(actionId == EditorInfo.IME_ACTION_NEXT){
                    handledExercise5RepsSet3 = true;
                }
                return handledExercise5RepsSet3;
            }
        });
        if ((exercise5RepsSet3.getText() != null) && !(exercise5RepsSet3.getText().toString().equals(""))) {
            userWorkout.get(4).setRepsSet3(Integer.parseInt(exercise5RepsSet3.getText().toString()));
        }

        // Exercise 5, Cardio Time
        EditText exercise5CardioTime = (EditText) findViewById(R.id.exercise5_cardio_time);
        exercise5CardioTime.setOnEditorActionListener(new TextView.OnEditorActionListener(){
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                boolean handledExercise5CardioTime = false;
                if(actionId == EditorInfo.IME_ACTION_DONE){
                    handledExercise5CardioTime = true;
                }
                return handledExercise5CardioTime;
            }
        });
        if (exercise5CardioTime.getText() != null) {
            userWorkout.get(4).setCardioTime(exercise5CardioTime.getText().toString());
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
                "Current weight: " + userWorkoutTracker.getCurrentWeight() + " lbs" +
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
