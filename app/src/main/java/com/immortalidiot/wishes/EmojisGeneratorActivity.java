package com.immortalidiot.wishes;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.os.Bundle;
import android.text.InputFilter;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import com.google.android.material.textfield.TextInputEditText;



public class EmojisGeneratorActivity extends AppCompatActivity {

    int MIN_LENGTH = 1;
    int MAX_LENGTH = 2000;
    WishGenerator wishGenerator = new WishGenerator();
    TextInputEditText valueField = findViewById(R.id.valueField);
    int text = Integer.parseInt(String.valueOf(valueField.getText()));
    AppCompatButton generateButton = findViewById(R.id.generateButton);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emoji_generator);

        valueField.setTransformationMethod(new NumericKeyBoardTransformation());

        valueField.setFilters(new InputFilter[]{
                new com.immortalidiot.wishes.InputFilter(MIN_LENGTH, MAX_LENGTH)
        });

        valueField.setOnEditorActionListener((v, actionId, event) -> {
            if (event.getAction() == KeyEvent.ACTION_DOWN || actionId == KeyEvent.KEYCODE_ENTER) {
                hideVirtualKeyboard(); }
            return false;
        });

        generateButton.
                setOnClickListener(v -> { if (text != 0) {
                    save(String.valueOf(R.string.text_copied_hint), wishGenerator.generator(text)); }});
    }

    void hideVirtualKeyboard() {
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    void save(String hint, CharSequence copiedText) {
        ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
        ClipData clip = ClipData.newPlainText(hint, copiedText);
        clipboard.setPrimaryClip(clip);
    }

    public void finishActivity(View v) { finish(); }
}