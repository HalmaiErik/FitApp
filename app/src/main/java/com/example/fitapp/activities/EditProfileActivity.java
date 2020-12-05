package com.example.fitapp.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.fitapp.R;
import com.example.fitapp.database.DatabaseHelper;
import com.example.fitapp.model.Profile;

public class EditProfileActivity extends AppCompatActivity {
    DatabaseHelper databaseHelper;
    private EditText nameText;
    private EditText ageText;
    private RadioGroup genderGroup;
    private RadioButton selectedGender;
    private EditText currWeightText;
    private EditText goalWeightText;
    private Profile oldProfile;
    private boolean profileEntered;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        databaseHelper = new DatabaseHelper(this);
        nameText = (EditText) findViewById(R.id.intext_name);
        ageText = (EditText) findViewById(R.id.intext_age);
        genderGroup = (RadioGroup) findViewById(R.id.radgroup_gender);
        int selectedGenderID = genderGroup.getCheckedRadioButtonId();
        selectedGender = (RadioButton) findViewById(selectedGenderID);
        currWeightText = (EditText) findViewById(R.id.intext_currweight);
        goalWeightText = (EditText) findViewById(R.id.intext_goalweight);
        oldProfile = new Profile(nameText.getText().toString(),
                Integer.parseInt(ageText.getText().toString()),
                selectedGender.getText().toString(),
                Float.parseFloat(currWeightText.getText().toString()),
                Float.parseFloat(goalWeightText.getText().toString()));
        Log.d("EditProfileActivity", "oldProfile saved: " + oldProfile.toString());
        profileEntered = false;
    }

    /** Called when the user taps the Back ( < ) button. */
    public void mainMenu(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    /** Called when the user taps the Save button. */
    public void save(View view) {
        String[] data = textToData();
        if (!data[0].equals("") && !data[1].equals("") && !data[2].equals())
        Profile newProfile = new Profile(nameText.getText().toString(),
                Integer.parseInt(ageText.getText().toString()),
                selectedGender.getText().toString(),
                Float.parseFloat(currWeightText.getText().toString()),
                Float.parseFloat(goalWeightText.getText().toString()));

        if (profileEntered) {
            databaseHelper.editProfile(oldProfile, newProfile);
        }
        else {
            databaseHelper.addProfile(newProfile);
        }
        mainMenu(view);
    }

    public String[] textToData() {
        return new String[] {nameText.getText().toString(), ageText.getText().toString(),
                selectedGender.getText().toString(), currWeightText.getText().toString(),
                goalWeightText.getText().toString()};
    }
    
    /**
     * Customizable toast
     * @param message
     */
    private void toastMessage(String message){
        Toast.makeText(this,message, Toast.LENGTH_SHORT).show();
    }
}