package com.example.fitapp.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.fitapp.R;
import com.example.fitapp.database.DatabaseHelper;
import com.example.fitapp.database.validators.ExerciseValidator;
import com.example.fitapp.model.Exercise;

public class EditExerciseActivity extends AppCompatActivity {
    DatabaseHelper databaseHelper;
    private EditText nameText;
    private EditText setsText;
    private EditText repsText;
    private EditText weightText;
    private int lastProfileId;
    private ExerciseValidator validator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_exercise);

        databaseHelper = new DatabaseHelper(getApplicationContext());
        lastProfileId = databaseHelper.getLastProfileId();
        initViews();
        validator = new ExerciseValidator();
    }

    private void initViews() {
        nameText = findViewById(R.id.intext_nameEx);
        setsText = findViewById(R.id.intext_sets);
        repsText = findViewById(R.id.intext_reps);
        weightText = findViewById(R.id.intext_weight);
    }

    public void save(View view) {
        String[] data = textToData();
        if (!data[0].equals("") && !data[1].equals("") && !data[2].equals("") && !data[3].equals("")) {
            Exercise exercise = new Exercise(data[0], Integer.parseInt(data[1]), Integer.parseInt(data[2]),
                    Float.parseFloat(data[3]), lastProfileId);
            try {
                validator.validate(exercise);
                if (databaseHelper.editExercise(exercise)) {
                    toastMessage("Exercise created");
                    workoutMenu(view);
                }
                else {
                    toastMessage("Something went wrong");
                }
            } catch (IllegalArgumentException e) {
                toastMessage(e.getMessage());
            }
        }
    }

    /** Called when the user taps the Back ( < ) button. */
    public void workoutMenu(View view) {
        Intent intent = new Intent(this, WorkoutActivity.class);
        startActivity(intent);
    }

    public String[] textToData() {
        return new String[]{nameText.getText().toString(), setsText.getText().toString(),
                repsText.getText().toString(), weightText.getText().toString()};
    }

    /**
     * Customizable toast
     * @param message
     */
    private void toastMessage(String message){
        Toast.makeText(this,message, Toast.LENGTH_SHORT).show();
    }
}