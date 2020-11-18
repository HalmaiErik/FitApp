package com.example.fitapp.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.fitapp.R;

public class EditProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
        //TODO: Database load
    }

    /** Called when the user taps the Back ( < ) button. */
    public void mainMenu (View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    /** Called when the user taps the Save button. */
    public void save (View view) {
        //TODO: Database save
        mainMenu(view);
    }
}