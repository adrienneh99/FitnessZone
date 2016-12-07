package org.tpl.fitnesszone.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import org.tpl.fitnesszone.R;
import org.tpl.fitnesszone.model.Circuit;


public class CircuitWorkoutRVAdapter extends RecyclerView.Adapter<CircuitWorkoutRVAdapter.CircuitViewHolder> {

    // Create a class to hold the exact set of views and initialize the views
    public class CircuitViewHolder extends RecyclerView.ViewHolder {

        TextView circuitAction;
        TextView exerciseNameLabel;
        TextView circuitExerciseName;
        TextView repetitionsLabel;
        TextView circuitRepetitions;
        TextView setsLabel;
        TextView circuitSets;
        TextView restBetweenSetsLabel;
        TextView circuitRestBetweenSets;

        // Populate the ViewHolder and store it inside the layout
        CircuitViewHolder(View itemView) {
            super(itemView);
            circuitAction = (TextView)itemView.findViewById(R.id.circuit_action);
            exerciseNameLabel = (TextView)itemView.findViewById(R.id.exercise_name_label);
            circuitExerciseName = (TextView)itemView.findViewById(R.id.circuit_exercise_name);
            repetitionsLabel = (TextView)itemView.findViewById(R.id.repetitions_label);
            circuitRepetitions = (TextView)itemView.findViewById(R.id.circuit_repetitions);
            setsLabel = (TextView)itemView.findViewById(R.id.sets_label);
            circuitSets = (TextView)itemView.findViewById(R.id.circuit_sets);
            restBetweenSetsLabel = (TextView)itemView.findViewById(R.id.rest_between_sets_label);
            circuitRestBetweenSets = (TextView)itemView.findViewById(R.id.circuit_rest_between_sets);
        }
    }

    // Declare an array to hold circuit objects
    Circuit[] circuitWorkouts;

    // Add a constructor
    public CircuitWorkoutRVAdapter(Circuit[] circuitWorkouts) {
        this.circuitWorkouts = circuitWorkouts;
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    // Create new views (invoked by the layout manager)
    @Override
    public CircuitViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.activity_circuit_workout, viewGroup, false);
        CircuitViewHolder cvh = new CircuitViewHolder(v);
        return cvh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(CircuitViewHolder circuitViewHolder, int i) {

        circuitViewHolder.circuitAction.setText(circuitWorkouts[i].getAction());
        circuitViewHolder.exerciseNameLabel.setText(R.string.exercise_name);
        circuitViewHolder.circuitExerciseName.setText(circuitWorkouts[i].getExerciseName());
        circuitViewHolder.repetitionsLabel.setText(R.string.repetitions);
        circuitViewHolder.circuitRepetitions.setText(circuitWorkouts[i].getRepetitions());
        circuitViewHolder.setsLabel.setText(R.string.sets);
        circuitViewHolder.circuitSets.setText(circuitWorkouts[i].getSets());
        circuitViewHolder.restBetweenSetsLabel.setText(R.string.rest_between_sets);
        circuitViewHolder.circuitRestBetweenSets.setText(circuitWorkouts[i].getRestBetweenSets());
    }

    // Return the size of the Exercise Equipment list
    @Override
    public int getItemCount() {
        return circuitWorkouts.length;
    }
}
