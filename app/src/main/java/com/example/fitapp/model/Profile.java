package com.example.fitapp.model;

public class Profile {
    private enum Gender {
        MALE, FEMALE
    }

    private String name;
    private int age;
    private Gender gender;
    private float currWeight;
    private float goalWeight;

    public Profile(String name, int age, String gender, float currWeight, float goalWeight) {
        this.name = name;
        this.age = age;
        if (gender.equals("Male")) {
            this.gender = Gender.MALE;
        }
        else {
            this.gender = Gender.FEMALE;
        }
        this.currWeight = currWeight;
        this.goalWeight = goalWeight;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getGender() {
        return String.valueOf(gender);
    }

    public float getCurrWeight() {
        return currWeight;
    }

    public float getGoalWeight() {
        return goalWeight;
    }

    @Override
    public String toString() {
        return "Profile{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", gender=" + gender +
                ", currWeight=" + currWeight +
                ", goalWeight=" + goalWeight +
                '}';
    }
}
