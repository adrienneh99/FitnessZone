package org.tpl.fitnesszone.model;


public class ExerciseEquipment {
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
}
