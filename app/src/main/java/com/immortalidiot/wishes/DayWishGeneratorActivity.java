package com.immortalidiot.wishes;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.os.Bundle;
import android.text.InputFilter;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

public class DayWishGeneratorActivity extends AppCompatActivity {

    private final WishGenerator wishGenerator = new WishGenerator();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_day_wish_generator);

        final TextInputEditText valueField = findViewById(R.id.dayWishGeneratorValueField);
        valueField.setTransformationMethod(new NumericKeyBoardTransformation());

        int MIN_LENGTH = 1;
        int MAX_LENGTH = 2000;

        valueField.setFilters(new InputFilter[]{
                new com.immortalidiot.wishes.InputFilter(MIN_LENGTH, MAX_LENGTH)});

        valueField.setOnEditorActionListener((v, actionId, event) -> {
            if (event.getAction() == KeyEvent.ACTION_DOWN || actionId == KeyEvent.KEYCODE_ENTER) {
                InputMethodUtils.hideVirtualKeyboard(this); } return false; });

        final AppCompatButton generateButton = findViewById(R.id.dayWishGeneratorGenerateButton);

        generateButton.setOnClickListener(v -> {
            final String dayWish = wishGenerator.getRandomDayWish();
            final String text = String.valueOf(valueField.getText());

            if (text.length() != 0) { ClipboardUtils.save(this,
                                      String.valueOf(R.string.wish_copied_hint),
                                      (dayWish + wishGenerator.generator(Integer.parseInt(text))));

                final TextView dayWishGeneratorTextView = findViewById(R.id.dayWishGeneratorHint);
                final CharSequence output = SystemLanguageUtils.fullWishLocalization() + dayWish;
                dayWishGeneratorTextView.setTextSize(20);
                dayWishGeneratorTextView.setText(output);
                generateButton.setText(R.string.refresh_button_text);
                Toast.makeText(this, R.string.wish_copied_hint, Toast.LENGTH_SHORT).show();}});
    }

    public void finishActivity(View view) { finish(); }
}