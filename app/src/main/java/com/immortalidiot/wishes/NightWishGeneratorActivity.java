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
    private final TextInputEditText valueField = findViewById(R.id.nightWishValueField);
    private final WishGenerator wishGenerator = new WishGenerator();
    private final TextView nightWishTextView = findViewById(R.id.nightWishHint);
    private final AppCompatButton generateButton = findViewById(R.id.nightWishGenerateButton);
    private final String nightWish = wishGenerator.getRandomNightWish();
    private final CharSequence output = "Compliment" + nightWish;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_night_wish_generator);

        valueField.setTransformationMethod(new NumericKeyBoardTransformation());
        valueField.setFilters(new InputFilter[] {
                new com.immortalidiot.wishes.InputFilter(MIN_LENGTH, MAX_LENGTH)});

        valueField.setOnEditorActionListener((v, actionId, event) -> {
            if (event.getAction() == KeyEvent.ACTION_DOWN || actionId == KeyEvent.KEYCODE_ENTER) {
                InputMethodUtils.hideVirtualKeyboard(this); } return false; });

        generateButton.setOnClickListener(v -> {
            final String text = String.valueOf(valueField.getText());
            if (text.length() != 0) { ClipboardUtils.save(this,
                                      String.valueOf(R.string.wish_copied_hint),
                                      (nightWish + wishGenerator.generator(Integer.parseInt(text))));
                                      nightWishTextView.setTextSize(20);
                                      nightWishTextView.setText(output);
                                      generateButton.setText(R.string.refresh_button_text); }});
    }

    public void finishActivity(View view) { finish(); }
}