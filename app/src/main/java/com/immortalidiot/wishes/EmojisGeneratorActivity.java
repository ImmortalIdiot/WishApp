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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_generator);

        TextInputEditText valueField = findViewById(R.id.valueField);


        valueField.setTransformationMethod(new NumericKeyBoardTransformation());

        valueField.setFilters(new InputFilter[]{
                new com.immortalidiot.wishes.InputFilter(1, 2000)
        });

        valueField.setOnEditorActionListener((v, actionId, event) -> {
            if (event.getAction() == KeyEvent.ACTION_DOWN || actionId == KeyEvent.KEYCODE_ENTER) {
                hideVirtualKeyboard();
            }
            return false;
        });

        AppCompatButton generateButton = findViewById(R.id.generateButton);

        generateButton.setOnClickListener(v -> {
            String text = String.valueOf(valueField.getText());
            if (text.length() != 0) {
                int value = Integer.parseInt(text);
                String copyHint = String.valueOf(R.string.text_copied_hint);

                WishGenerator generate = new WishGenerator();

                ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData clip = ClipData.newPlainText(copyHint, generate.generator(value));
                clipboard.setPrimaryClip(clip);
            }
        });
    }

    void hideVirtualKeyboard() {
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    public void finishActivity(View v) {
        finish();
    }
}