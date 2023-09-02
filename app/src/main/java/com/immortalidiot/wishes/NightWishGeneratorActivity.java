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

    private final WishGenerator wishGenerator = new WishGenerator();
    private final String nightWish = wishGenerator.getRandomNightWish();
    private final CharSequence output = "Compliment" + nightWish;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_night_wish_generator);

        final TextInputEditText valueField = findViewById(R.id.nightWishValueField);
        valueField.setTransformationMethod(new NumericKeyBoardTransformation());

        final int MIN_LENGTH = 1;
        final int MAX_LENGTH = 2000;

        valueField.setFilters(new InputFilter[] {
                new com.immortalidiot.wishes.InputFilter(MIN_LENGTH, MAX_LENGTH)});

        valueField.setOnEditorActionListener((v, actionId, event) -> {
            if (event.getAction() == KeyEvent.ACTION_DOWN || actionId == KeyEvent.KEYCODE_ENTER) {
                InputMethodUtils.hideVirtualKeyboard(this); } return false; });

        final AppCompatButton generateButton = findViewById(R.id.nightWishGenerateButton);

        generateButton.setOnClickListener(v -> {
            final String text = String.valueOf(valueField.getText());
            if (text.length() != 0) { ClipboardUtils.save(this,
                                      String.valueOf(R.string.wish_copied_hint),
                                      (nightWish + wishGenerator.generator(Integer.parseInt(text))));

                final TextView nightWishTextView = findViewById(R.id.nightWishHint);
                nightWishTextView.setTextSize(20);
                nightWishTextView.setText(output);
                generateButton.setText(R.string.refresh_button_text); }});
    }

    public void finishActivity(View view) { finish(); }
}