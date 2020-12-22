package com.example.fitapp.model;

import java.io.Serializable;

public class Exercise implements Serializable {
    private String name;
    private int sets;
    private int reps;
    private float weight;
    private int fkProfile;

    public Exercise (String name, int sets, int reps, float weight, int fkProfile) {
        this.name = name;
        this.sets = sets;
        this.reps = reps;
        this.weight = weight;
        this.fkProfile = fkProfile;
    }

    @Override
    public String toString() {
        return name + " " + sets + " sets of " + reps + " reps - " + weight + " kg";
    }

    public String getName() {
        return name;
    }

    public int getSets() {
        return sets;
    }

    public int getReps() {
        return reps;
    }

    public float getWeight() {
        return weight;
    }

    public int getFkProfile() {
        return fkProfile;
    }
}
