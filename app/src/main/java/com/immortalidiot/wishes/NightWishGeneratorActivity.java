package com.immortalidiot.wishes;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.os.Bundle;
import android.text.InputFilter;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;

public class NightWishGeneratorActivity extends AppCompatActivity {

    final int MIN_LENGTH = 1;
    final int MAX_LENGTH = 2000;
    WishGenerator wishGenerator = new WishGenerator();
    TextInputEditText valueField = findViewById(R.id.nightWishValueField);
    TextView nightWishTextView = findViewById(R.id.nightWishHint);
    AppCompatButton generateButton = findViewById(R.id.nightWishGenerateButton);
    CharSequence output;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_night_wish_generator);

        valueField.setTransformationMethod(new NumericKeyBoardTransformation());
        valueField.setFilters(new InputFilter[] {
                new com.immortalidiot.wishes.InputFilter(MIN_LENGTH, MAX_LENGTH)});

        valueField.setOnEditorActionListener(((v, actionId, event) -> {
            if (event.getAction() == KeyEvent.ACTION_DOWN || actionId == KeyEvent.KEYCODE_ENTER) {
                new EmojisGeneratorActivity().hideVirtualKeyboard(); }
            return false;
        }));


        generateButton.setOnClickListener(v -> {
            int text = Integer.parseInt(String.valueOf(valueField.getText()));
            if (text != 0) {
                String nightWish = wishGenerator.getRandomNightWish();

                new EmojisGeneratorActivity().save(String.valueOf(R.string.wish_copied_hint),
                        nightWish + wishGenerator.generator(text));

                output = "Compliment" + nightWish;
                nightWishTextView.setTextSize(20);
                nightWishTextView.setText(output);
                generateButton.setText(R.string.refresh_button_text);
            }
        });
    }


    public void finishActivity(View view) {
        finish();
    }
}