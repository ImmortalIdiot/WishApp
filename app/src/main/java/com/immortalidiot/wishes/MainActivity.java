package com.immortalidiot.wishes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void switchToGenerator(View v) {
        Intent intent = new Intent(this, GeneratorActivity.class);
        startActivity(intent);
    }


    public void switchToCreator(View v) {
        Intent intent = new Intent(this, CreditsActivity.class);
        startActivity(intent);
    }

    public void exitWithoutConfirmation(View v) {
        finishAffinity();
    }
}