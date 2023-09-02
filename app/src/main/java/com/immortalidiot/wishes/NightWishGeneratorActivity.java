package com.immortalidiot.wishes;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.annotation.SuppressLint;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
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


    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_night_wish_generator);

        TextInputEditText valueField = findViewById(R.id.nightWishValueField);
        valueField.setTransformationMethod(new NumericKeyBoardTransformation());
        valueField.setFilters(new InputFilter[] {
                new com.immortalidiot.wishes.InputFilter(MIN_LENGTH, MAX_LENGTH)});

        valueField.setOnEditorActionListener(((v, actionId, event) -> {
            if (event.getAction() == KeyEvent.ACTION_DOWN || actionId == KeyEvent.KEYCODE_ENTER) {
                new EmojisGeneratorActivity().hideVirtualKeyboard(); }
            return false;
        }));

        TextView nightWishTextView = findViewById(R.id.nightWishHint);
        AppCompatButton generateButton = findViewById(R.id.nightWishGenerateButton);

        generateButton.setOnClickListener(v -> {
            int text = Integer.parseInt(String.valueOf(valueField.getText()));
            if (text != 0) {
                String copyHint = String.valueOf(R.string.wish_copied_hint);
                String nightWish = wishGenerator.getRandomNightWish();

                ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData clip = ClipData.newPlainText(copyHint,
                        (nightWish + wishGenerator.generator(text)));
                clipboard.setPrimaryClip(clip);

                nightWishTextView.setTextSize(20);
                nightWishTextView.setText("Compliment: " + nightWish);
                generateButton.setText(R.string.refresh_button_text);
            }
        });
    }


    public void finishActivity(View view) {
        finish();
    }
}