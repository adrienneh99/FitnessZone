package org.tpl.fitnesszone.model;

import android.os.Parcel;
import android.os.Parcelable;


public class ExerciseEquipment implements Parcelable {

    private String imageName;
    private String name;
    private String activityType;
    private String [] activityTypeDetail;
    private String [] musclesUsed;
    private String [] healthBenefits;

    ExerciseEquipment() {

    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    // Parcelable implementation
    public ExerciseEquipment(Parcel in) {
        this.name = in.readString();
        this.imageName = in.readString();
        this.activityType = in.readString();
        in.readStringArray(this.activityTypeDetail);
        in.readStringArray(this.musclesUsed);
        in.readStringArray(this.healthBenefits);
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
        dest.writeString(name);
        dest.writeString(imageName);
        dest.writeString(activityType);
        dest.writeStringArray(activityTypeDetail);
        dest.writeStringArray(musclesUsed);
        dest.writeStringArray(healthBenefits);
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
