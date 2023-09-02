package com.immortalidiot.wishes;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.annotation.SuppressLint;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.text.InputFilter;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;

public class DayWishGeneratorActivity extends AppCompatActivity {

    final int MIN = 1;
    final int MAX = 2000;

    WishGenerator wishGenerator = new WishGenerator();

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_day_wish_generator);

        TextInputEditText valueField = findViewById(R.id.dayWishGeneratorValueField);
        valueField.setTransformationMethod(new NumericKeyBoardTransformation());
        valueField.setFilters(new InputFilter[]{
                new com.immortalidiot.wishes.InputFilter(MIN, MAX)});

        valueField.setOnEditorActionListener((v, actionId, event) -> {
            if (event.getAction() == KeyEvent.ACTION_DOWN || actionId == KeyEvent.KEYCODE_ENTER) {
                new GeneratorActivity().hideVirtualKeyboard();
            }
            return false;
        });

        TextView dayWishGeneratorTextView = findViewById(R.id.dayWishGeneratorHint);

        AppCompatButton generateButton = findViewById(R.id.dayWishGeneratorGenerateButton);

        generateButton.setOnClickListener(v -> {
            String text = String.valueOf(valueField.getText());
            if (text.length() != 0) {
                String copyHint = String.valueOf(R.string.day_wish_generator_text_copied_hint);
                String dayWish = wishGenerator.getRandomDayWish();

                ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData clip = ClipData.newPlainText(copyHint,
                        (dayWish + wishGenerator.generator(Integer.parseInt(text))));
                clipboard.setPrimaryClip(clip);

                dayWishGeneratorTextView.setTextSize(20);
                dayWishGeneratorTextView.setText("Compliment: " + dayWish);
                generateButton.setText(R.string.updating_button_text);


            }
        });
    }

    public void finishDayWishGeneratorActivity(View view) { finish(); }
}