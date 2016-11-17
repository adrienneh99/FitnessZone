package org.tpl.fitnesszone.adapter;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import org.tpl.fitnesszone.R;
import org.tpl.fitnesszone.model.ExerciseEquipment;
import org.tpl.fitnesszone.util.ResourceUtils;

import java.util.List;

public class RVAdapter extends RecyclerView.Adapter<RVAdapter.EquipmentViewHolder> {

    // Create a class to hold the exact set of views and initialize the views
    public static class EquipmentViewHolder extends RecyclerView.ViewHolder {

        CardView cv;
        ImageView equipmentImage;
        TextView equipmentName;
        TextView activityType;
        Button learnMoreButton;

        // Populate the ViewHolder and store it inside the layout
        EquipmentViewHolder(View itemView) {
            super(itemView);
            cv = (CardView)itemView.findViewById(R.id.equipment_benefits_cardview);
            equipmentImage = (ImageView)itemView.findViewById(R.id.equipment_image);
            equipmentName = (TextView)itemView.findViewById(R.id.equipment_name);
            activityType = (TextView)itemView.findViewById(R.id.activity_type);
            learnMoreButton = (Button)itemView.findViewById(R.id.learn_more_button);
        }
    }

    // Declare a list to hold equipment objects
    ExerciseEquipment[] equipmentList;

    // Add a constructor
    public RVAdapter(ExerciseEquipment[] equipmentList) {
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
                .inflate(R.layout.activity_equipment_benefits_cardview, viewGroup, false);
        EquipmentViewHolder evh = new EquipmentViewHolder(v);
        return evh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(EquipmentViewHolder equipmentViewHolder, int i) {
        equipmentViewHolder.equipmentImage.setImageResource(ResourceUtils.getResourceId(equipmentList[i].getImageName(), R.drawable.class));
        equipmentViewHolder.equipmentName.setText(equipmentList[i].getName());
        equipmentViewHolder.activityType.setText(equipmentList[i].getActivityType());
    }

    // Return the size of the Exercise Equipment list
    @Override
    public int getItemCount() {
        return equipmentList.length;
    }

}
