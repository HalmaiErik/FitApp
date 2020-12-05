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
import com.example.fitapp.database.validators.ProfileValidator;
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
    private boolean emptyProfile;
    private ProfileValidator validator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        databaseHelper = new DatabaseHelper(this);
        emptyProfile = databaseHelper.isEmptyProfileTable();
        initViews();

        if (!emptyProfile) {
            loadLastProfile();
            int selectedGenderID = genderGroup.getCheckedRadioButtonId();
            selectedGender = (RadioButton) findViewById(selectedGenderID);
            oldProfile = new Profile(nameText.getText().toString(),
                    Integer.parseInt(ageText.getText().toString()),
                    selectedGender.getText().toString(),
                    Float.parseFloat(currWeightText.getText().toString()),
                    Float.parseFloat(goalWeightText.getText().toString()));
            Log.d("EditProfileActivity", "oldProfile saved: " + oldProfile.toString());
        }
        validator = new ProfileValidator();
    }

    public void initViews() {
        nameText = (EditText) findViewById(R.id.intext_name);
        ageText = (EditText) findViewById(R.id.intext_age);
        genderGroup = (RadioGroup) findViewById(R.id.radgroup_gender);
        int selectedGenderID = genderGroup.getCheckedRadioButtonId();
        selectedGender = (RadioButton) findViewById(selectedGenderID);
        currWeightText = (EditText) findViewById(R.id.intext_currweight);
        goalWeightText = (EditText) findViewById(R.id.intext_goalweight);
    }

    public void loadLastProfile() {
        Profile profile = databaseHelper.getLastProfile();
        if (profile != null) {
            nameText.setText(profile.getName());
            ageText.setText(String.valueOf(profile.getAge()));
            String gender = profile.getGender();
            if (gender.equals("MALE")) {
                genderGroup.check(R.id.radbutton_male);
            }
            else {
                genderGroup.check(R.id.radbutt_female);
            }
            currWeightText.setText(String.valueOf(profile.getCurrWeight()));
            goalWeightText.setText(String.valueOf(profile.getGoalWeight()));
        }
        else Log.e("EditProfileActivity", "loadLastProfile: Not Found");
    }

    /** Called when the user taps the Back ( < ) button. */
    public void mainMenu(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    /** Called when the user taps the Save button. */
    public void save(View view) {
        String[] data = textToData();
        if (!data[0].equals("") && !data[1].equals("") && !data[2].equals("") && !data[3].equals("")
            && !data[4].equals("")) {
            int selectedGenderID = genderGroup.getCheckedRadioButtonId();
            selectedGender = (RadioButton) findViewById(selectedGenderID);
            Profile newProfile = new Profile(nameText.getText().toString(),
                    Integer.parseInt(ageText.getText().toString()),
                    selectedGender.getHint().toString(),
                    Float.parseFloat(currWeightText.getText().toString()),
                    Float.parseFloat(goalWeightText.getText().toString()));
            try {
                validator.validate(newProfile);

                if (!emptyProfile) {
                    if (databaseHelper.editProfile(oldProfile, newProfile)) {
                        toastMessage("Profile updated");
                        mainMenu(view);
                    }
                    else {
                        toastMessage("Something went wrong");
                    }
                }
                else {
                    databaseHelper.addProfile(newProfile);
                    emptyProfile = false;
                }
            } catch (IllegalArgumentException e) {
                toastMessage(e.getMessage());
            }
        }
        toastMessage(data[0] + " " + data[1] + " " + data[2] + " " + data[3] + " " + data[4]);
    }

    public String[] textToData() {
        int selectedGenderID = genderGroup.getCheckedRadioButtonId();
        selectedGender = (RadioButton) findViewById(selectedGenderID);
        return new String[] {nameText.getText().toString(), ageText.getText().toString(),
                selectedGender.getHint().toString(), currWeightText.getText().toString(),
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