package com.example.fitapp.database.validators;

import com.example.fitapp.model.Profile;

public class ProfileValidator implements Validator<Profile> {
    private static final int MIN_LENGTH_NAME = 2;
    private static final int MAX_LENGTH_NAME = 30;
    private static final int MIN_AGE = 1;
    private static final int MAX_AGE = 100;
    private static final int MIN_WEIGHT = 5;

    @Override
    public void validate(Profile profile) {
        if (profile.getName().length() < MIN_LENGTH_NAME || profile.getName().length() > MAX_LENGTH_NAME) {
            throw new IllegalArgumentException("The name most be between " + MIN_LENGTH_NAME + " and " +
                    MAX_LENGTH_NAME + " characters");
        }
        if (profile.getAge() < MIN_AGE || profile.getAge() > MAX_AGE) {
            throw new IllegalArgumentException("The age must be between " + MIN_AGE + " and " + MAX_AGE);
        }
        if (profile.getCurrWeight() < MIN_WEIGHT || profile.getGoalWeight() < 5) {
            throw new IllegalArgumentException("The weight must be greater than " + MIN_WEIGHT);
        }
    }
}
