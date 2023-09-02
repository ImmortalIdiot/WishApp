package com.immortalidiot.wishes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class ModeSelectionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mode_selection);
    }

    public void switchToGeneratorActivity(View v) {
        startActivity(new Intent(this, EmojisGeneratorActivity.class));
    }

    public void switchToDayWishGeneratorActivity(View v) {
        startActivity(new Intent(this, DayWishGeneratorActivity.class));
    }

    public void switchToNightWishGeneratorActivity(View v) {
        startActivity(new Intent (this, NightWishGeneratorActivity.class));
    }

    public void finishActivity(View v) { finish(); }
}