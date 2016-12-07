package org.tpl.fitnesszone.model;

public class Circuit {

    private String action;
    private String exerciseName;
    private String repetitions;
    private String sets;
    private String restBetweenSets;

    Circuit() {

    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getExerciseName() {
        return exerciseName;
    }

    public void setExerciseName(String exerciseName) {
        this.exerciseName = exerciseName;
    }

    public String getRepetitions() {
        return repetitions;
    }

    public void setRepetitions(String repetitions) {
        this.repetitions = repetitions;
    }

    public String getSets() {
        return sets;
    }

    public void setSets(String sets) {
        this.sets = sets;
    }

    public String getRestBetweenSets() {
        return restBetweenSets;
    }

    public void setRestBetweenSets(String restBetweenSets) {
        this.restBetweenSets = restBetweenSets;
    }
}
