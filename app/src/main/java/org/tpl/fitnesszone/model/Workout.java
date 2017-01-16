package org.tpl.fitnesszone.model;

public class Workout {

    String exerciseName;
    int repsSet1;
    int repsSet2;
    int repsSet3;
    String cardioTime;

    public Workout() {

    }

    public String getExerciseName() {
        return exerciseName;
    }

    public void setExerciseName(String exerciseName) {
        this.exerciseName = exerciseName;
    }

    public int getRepsSet1() {
        return repsSet1;
    }

    public void setRepsSet1(int repsSet1) {
        this.repsSet1 = repsSet1;
    }

    public int getRepsSet2() {
        return repsSet2;
    }

    public void setRepsSet2(int repsSet2) {
        this.repsSet2 = repsSet2;
    }

    public int getRepsSet3() {
        return repsSet3;
    }

    public void setRepsSet3(int repsSet3) {
        this.repsSet3 = repsSet3;
    }

    public String getCardioTime() {
        return cardioTime;
    }

    public void setCardioTime(String cardioTime) {
        this.cardioTime = cardioTime;
    }
}
