package org.tpl.fitnesszone.adapter;

import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import org.tpl.fitnesszone.R;
import org.tpl.fitnesszone.activity.EquipmentExerciseDetailActivity;
import org.tpl.fitnesszone.model.EquipmentExercise;
import org.tpl.fitnesszone.util.ResourceUtils;


public class EquipmentExerciseListRVAdapter extends RecyclerView.Adapter<EquipmentExerciseListRVAdapter.EquipmentViewHolder> {

    // Create a class to hold the exact set of views and initialize the views
    public class EquipmentViewHolder extends RecyclerView.ViewHolder {

        CardView cv;
        ImageView equipmentImage;
        TextView exerciseName;
        TextView activityType;
        Button learnMoreButton;

        // Populate the ViewHolder and store it inside the layout
        EquipmentViewHolder(View itemView) {
            super(itemView);
            cv = (CardView)itemView.findViewById(R.id.equipment_exercise_list_cardview);
            equipmentImage = (ImageView)itemView.findViewById(R.id.equipment_image);
            exerciseName = (TextView)itemView.findViewById(R.id.exercise_name);
            activityType = (TextView)itemView.findViewById(R.id.activity_type);
            learnMoreButton = (Button)itemView.findViewById(R.id.learn_more_button);
        }
    }

    // Declare an array to hold equipmentExercise objects
    EquipmentExercise[] equipmentList;

    // Add a constructor
    public EquipmentExerciseListRVAdapter(EquipmentExercise[] equipmentList) {
        this.equipmentList = equipmentList;
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    // Create new views (invoked by the layout manager)
    @Override
    public EquipmentViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.activity_equipment_exercise_list_cardview, viewGroup, false);
        EquipmentViewHolder evh = new EquipmentViewHolder(v);
        return evh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(EquipmentViewHolder equipmentViewHolder, int i) {

        final EquipmentExercise equipmentExercise = equipmentList[i];

        equipmentViewHolder.equipmentImage.setImageResource(ResourceUtils
                .getResourceId(equipmentList[i].getImageName(), R.drawable.class));
        equipmentViewHolder.exerciseName.setText(equipmentList[i].getExerciseName());
        equipmentViewHolder.activityType.setText(equipmentList[i].getActivityType());

        equipmentViewHolder.learnMoreButton.setOnClickListener(new View.OnClickListener() {
            // Called when the user clicks the Learn More Button
            @Override
            public void onClick (View view){
                Intent intent = new Intent(view.getContext(), EquipmentExerciseDetailActivity.class);
                intent.putExtra("id", (Parcelable)equipmentExercise);
                view.getContext().startActivity(intent);
            }
        });
    }

    // Return the size of the Exercise Equipment list
    @Override
    public int getItemCount() {
        return equipmentList.length;
    }

}
