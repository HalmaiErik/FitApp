package com.example.fitapp.database.validators;

import com.example.fitapp.model.Exercise;

public class ExerciseValidator implements Validator<Exercise> {
    private static final int MIN_LENGTH_NAME = 2;
    private static final int MAX_LENGTH_NAME = 40;
    private static final int MIN_SETS_REPS = 1;
    private static final int MIN_WEIGHT = 0;

    @Override
    public void validate(Exercise exercise) {
        if (exercise.getName().length() < MIN_LENGTH_NAME || exercise.getName().length() > MAX_LENGTH_NAME) {
            throw new IllegalArgumentException("The name most be between " + MIN_LENGTH_NAME + " and " +
                    MAX_LENGTH_NAME + " characters");
        }

        if (exercise.getSets() < MIN_SETS_REPS || exercise.getReps() < MIN_SETS_REPS) {
            throw new IllegalArgumentException("The number of sets and reps must be greater than " + MIN_SETS_REPS);
        }

        if (exercise.getWeight() < MIN_WEIGHT) {
            throw new IllegalArgumentException("The weight must be greater than " + MIN_WEIGHT);
        }
    }
}
