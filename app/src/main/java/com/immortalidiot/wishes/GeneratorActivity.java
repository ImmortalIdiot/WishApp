package com.immortalidiot.wishes;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputFilter;
import android.view.View;
import android.widget.EditText;


public class GeneratorActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_generator);

        EditText firstValueField = findViewById(R.id.first);
        EditText secondValueField = findViewById(R.id.second);

        firstValueField.setTransformationMethod(new NumericKeyBoardTransformation());
        secondValueField.setTransformationMethod(new NumericKeyBoardTransformation());

        final int CRITICAL_MINIMUM = 1;
        final int CRITICAL_MAXIMUM = 2500;

        firstValueField.setFilters(
                new InputFilter[]{new com.immortalidiot.wishes.InputFilter(
                        CRITICAL_MINIMUM, CRITICAL_MAXIMUM)});

        secondValueField.setFilters(
                new InputFilter[]{new com.immortalidiot.wishes.InputFilter(
                        CRITICAL_MINIMUM, CRITICAL_MAXIMUM)});//Integer.parseInt(String.valueOf(firstValueField)))});

    }
    public void goBack(View v) {
        finish();
    }
}