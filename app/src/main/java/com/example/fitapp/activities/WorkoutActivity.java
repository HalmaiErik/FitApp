package com.example.fitapp.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.database.CursorIndexOutOfBoundsException;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
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
    View selectedView;
    Exercise selectedExercise;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout);

        databaseHelper = new DatabaseHelper(this);
        initViews();
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
        if (selectedExercise != null) {
            Intent intent = new Intent(this, EditExerciseActivity.class);
            intent.putExtra("Exercise", selectedExercise);
            startActivity(intent);
        }
        else {
            toastMessage("You need to select an exercise");
        }
    }

    public void deleteExercise(View view) {
        if (selectedExercise != null) {
            try {
                if (databaseHelper.deleteExercise(selectedExercise)) {
                    toastMessage("Exercise deleted");
                    LinearLayout parent = (LinearLayout) selectedView.getParent();
                    parent.removeView(selectedView);
                    selectedView = null;
                    selectedExercise = null;
                } else {
                    toastMessage("Something went wrong");
                }
            } catch (CursorIndexOutOfBoundsException e) {
                Log.e("DeleteExercise", e.getMessage());
            }
        }
        else {
            toastMessage("You need to select an exercise");
        }
    }

    private void loadExercises() {
        exercises = databaseHelper.getAllExercises();
        for (Exercise exercise : exercises) {
            TextView exerciseInAll = new TextView(this);
            exerciseInAll.setText(exercise.getName());
            exerciseInAll.setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
            exerciseInAll.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (selectedView != null) {
                        selectedView.setBackground(null);
                    }
                    v.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.outline_view));
                    selectedView = v;
                    selectedExercise = exercise;
                }
            });
            exerciseInAll.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    if(((View) v.getParent()).getId() == allExercises.getId()) {
                        allExercises.removeView(v);
                        currExercises.addView(v);
                    }
                    else {
                        currExercises.removeView(v);
                        allExercises.addView(v);
                    }
                    return true;
                }
            });

            allExercises.addView(exerciseInAll);
        }
    }

    /**
     * Customizable toast
     * @param message String
     */
    private void toastMessage(String message){
        Toast.makeText(this,message, Toast.LENGTH_SHORT).show();
    }
}