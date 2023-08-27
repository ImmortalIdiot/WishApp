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

    public void switchToGenerator(View v) {
        Intent intent = new Intent(this, GeneratorActivity.class);
        startActivity(intent);
    }

    public void goBack(View v) {
        finish();
    }
}