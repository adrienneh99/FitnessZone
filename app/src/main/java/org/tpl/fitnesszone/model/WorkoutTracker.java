package org.tpl.fitnesszone.model;

import java.util.Calendar;
import java.util.List;

public class WorkoutTracker {

    Calendar workoutDate;
    int currentWeight;
    boolean armFocus;
    boolean legFocus;
    boolean cardioFocus;
    List<Workout> workouts;

    public WorkoutTracker() {
        armFocus = false;
        legFocus = false;
        cardioFocus = false;
    }

    public Calendar getWorkoutDate() {
        return workoutDate;
    }

    public void setWorkoutDate(Calendar workoutDate) {
        this.workoutDate = workoutDate;
    }

    public int getCurrentWeight() {
        return currentWeight;
    }

    public void setCurrentWeight(int currentWeight) {
        this.currentWeight = currentWeight;
    }

    public boolean isArmFocus() {
        return armFocus;
    }

    public void setArmFocus(boolean armFocus) {
        this.armFocus = armFocus;
    }

    public boolean isLegFocus() {
        return legFocus;
    }

    public void setLegFocus(boolean legFocus) {
        this.legFocus = legFocus;
    }

    public boolean isCardioFocus() {
        return cardioFocus;
    }

    public void setCardioFocus(boolean cardioFocus) {
        this.cardioFocus = cardioFocus;
    }

    public List<Workout> getWorkouts() {
        return workouts;
    }

    public void setWorkouts(List<Workout> workouts) {
        this.workouts = workouts;
    }
}
