package org.tpl.fitnesszone.model;

import android.os.Parcel;
import android.os.Parcelable;


public class ExerciseEquipment implements Parcelable {

    private String imageName;
    private String exerciseName;
    private String equipmentName;
    private String activityType;
    private String [] activityTypeDetail;
    private String [] musclesUsed;
    private String [] healthBenefits;
    private String [] instructions;
    private String [] exerciseTips;

    ExerciseEquipment() {

    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public String getExerciseName() {
        return exerciseName;
    }

    public void setExerciseName(String exerciseName) {
        this.exerciseName = exerciseName;
    }

    public String getEquipmentName() {
        return equipmentName;
    }

    public void setEquipmentName(String equipmentName) {
        this.equipmentName = equipmentName;
    }

    public String getActivityType() {
        return activityType;
    }

    public void setActivityType(String activityType) {
        this.activityType = activityType;
    }

    public String [] getActivityTypeDetail() {
        return activityTypeDetail;
    }

    public void setActivityTypeDetail(String [] activityTypeDetail) {
        this.activityTypeDetail = activityTypeDetail;
    }

    public String[] getMusclesUsed() {
        return musclesUsed;
    }

    public void setMusclesUsed(String[] musclesUsed) {
        this.musclesUsed = musclesUsed;
    }

    public String[] getHealthBenefits() {
        return healthBenefits;
    }

    public void setHealthBenefits(String[] healthBenefits) {
        this.healthBenefits = healthBenefits;
    }

    public String[] getInstructions() {
        return instructions;
    }

    public void setInstructions(String[] instructions) {
        this.instructions = instructions;
    }

    public String[] getExerciseTips() {
        return exerciseTips;
    }

    public void setExerciseTips(String[] exerciseTips) {
        this.exerciseTips = exerciseTips;
    }

    // Parcelable implementation
    public ExerciseEquipment(Parcel in) {
        this.imageName = in.readString();
        this.exerciseName = in.readString();
        this.equipmentName = in.readString();
        this.activityType = in.readString();
        in.readStringArray(this.activityTypeDetail);
        in.readStringArray(this.musclesUsed);
        in.readStringArray(this.healthBenefits);
        in.readStringArray(this.instructions);
        in.readStringArray(this.exerciseTips);
    }

    // Describe the kinds of special objects contained in this Parcelable instance's
    // marshaled representation
    @Override
    public int describeContents() {
        return 0;
    }

    // Flatten this object in to a Parcel; "dest" = the Parcel in which the object should
    // be written
    @Override
    public void writeToParcel(Parcel dest, int flag) {
        dest.writeString(imageName);
        dest.writeString(exerciseName);
        dest.writeString(equipmentName);
        dest.writeString(activityType);
        dest.writeStringArray(activityTypeDetail);
        dest.writeStringArray(musclesUsed);
        dest.writeStringArray(healthBenefits);
        dest.writeStringArray(instructions);
        dest.writeStringArray(exerciseTips);
    }

    // Interface that must be implemented and provided as a public CREATOR field that
    // generates instances of your Parcelable class from a Parcel
    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        public ExerciseEquipment createFromParcel(Parcel in) {
            return new ExerciseEquipment(in);
        }

        public ExerciseEquipment[] newArray(int size) {
            return new ExerciseEquipment[size];
        }
    };

}
