package com.immortalidiot.wishes;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.os.Bundle;
import android.text.InputFilter;
import android.view.KeyEvent;
import android.view.View;

import com.google.android.material.textfield.TextInputEditText;



public class EmojisGeneratorActivity extends AppCompatActivity {

    private final TextInputEditText valueField = findViewById(R.id.valueField);
    private final WishGenerator wishGenerator = new WishGenerator();
    private final AppCompatButton generateButton = findViewById(R.id.generateButton);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emoji_generator);

        valueField.setTransformationMethod(new NumericKeyBoardTransformation());

        int MIN_LENGTH = 1;
        int MAX_LENGTH = 2000;

        valueField.setFilters(new InputFilter[]{
                new com.immortalidiot.wishes.InputFilter(MIN_LENGTH, MAX_LENGTH)});

        valueField.setOnEditorActionListener((v, actionId, event) -> {
            if (event.getAction() == KeyEvent.ACTION_DOWN || actionId == KeyEvent.KEYCODE_ENTER) {
                InputMethodUtils.hideVirtualKeyboard(this); } return false; });

        generateButton.setOnClickListener(v -> {
            final String text = String.valueOf(valueField.getText());
            if (text.length() != 0) { ClipboardUtils.save(this,
                                      String.valueOf(R.string.text_copied_hint),
                                      wishGenerator.generator(Integer.parseInt(text))); }});
    }

    public void finishActivity(View v) { finish(); }
}