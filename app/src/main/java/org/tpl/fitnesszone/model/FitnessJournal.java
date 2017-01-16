package org.tpl.fitnesszone.model;

public class FitnessJournal {

    private String fitnessGoal;
    int startingWeight;

    public FitnessJournal() {

    }

    public String getFitnessGoal() {
        return fitnessGoal;
    }

    public void setFitnessGoal(String fitnessGoal) {
        this.fitnessGoal = fitnessGoal;
    }

    public int getStartingWeight() {
        return startingWeight;
    }

    public void setStartingWeight(int startingWeight) {
        this.startingWeight = startingWeight;
    }
}
