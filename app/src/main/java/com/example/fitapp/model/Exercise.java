package com.example.fitapp.model;

public class Exercise {
    private String name;
    private int sets;
    private int reps;
    private float weight;

    public Exercise (String name, int sets, int reps, float weight) {
        this.name = name;
        this.sets = sets;
        this.reps = reps;
        this.weight = weight;
    }

    @Override
    public String toString() {
        return name + " " + sets + " sets of " + reps + " reps";
    }
}
