package com.example.fitapp.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.LinearLayout.LayoutParams;

import com.example.fitapp.R;
import com.example.fitapp.database.DatabaseHelper;
import com.example.fitapp.model.Exercise;

import java.util.List;

public class WorkoutActivity extends AppCompatActivity {
    DatabaseHelper databaseHelper;
    LinearLayout allExercises;
    LinearLayout currExercises;
    List<Exercise> exercises;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout);

        databaseHelper = new DatabaseHelper(this);
        initViews();
        exercises = databaseHelper.getAllExercises();
        loadExercises();
    }

    public void initViews() {
        allExercises = findViewById(R.id.linearAllExercises);
        currExercises = findViewById(R.id.linearCurrWorkout);
    }

    public void createExercise(View view) {
        Intent intent = new Intent(this, CreateExerciseActivity.class);
        startActivity(intent);
    }

    public void editExercise(View view) {
        Intent intent = new Intent(this, EditExerciseActivity.class);
        startActivity(intent);
    }

    private void loadExercises() {
        exercises = databaseHelper.getAllExercises();
        for (Exercise exercise : exercises) {
            TextView value = new TextView(this);
            value.setText(exercise.getName());
            value.setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));

            allExercises.addView(value);
        }
    }

    /**
     * Customizable toast
     * @param message
     */
    private void toastMessage(String message){
        Toast.makeText(this,message, Toast.LENGTH_SHORT).show();
    }
}