package com.immortalidiot.wishes.ui;

import android.os.Bundle;
import android.text.InputFilter;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.FragmentManager;

import com.google.android.material.textfield.TextInputEditText;
import com.immortalidiot.wishes.ClipboardUtils;
import com.immortalidiot.wishes.InputMethodUtils;
import com.immortalidiot.wishes.NumericKeyBoardTransformation;
import com.immortalidiot.wishes.R;
import com.immortalidiot.wishes.WishGenerator;
import com.immortalidiot.wishes.databinding.FragmentEmojisGeneratorBinding;

public class EmojisGeneratorFragment extends FragmentUtils {
    FragmentEmojisGeneratorBinding binding;
    WishGenerator generator = new WishGenerator();
    CallbackFragment fragment;
    private final String HINT = "Эмоджи скопированы в буфер обмена";

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentEmojisGeneratorBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        binding.backButton.setOnClickListener(v -> {
            if (fragment != null) {
                closeFragment();
            }
        });

        final TextInputEditText field = binding.valueField;
        field.setTransformationMethod(new NumericKeyBoardTransformation());
        int MIN_LENGTH = 1;
        int MAX_LENGTH = 2000;
        field.setFilters(new InputFilter[]{
                new com.immortalidiot.wishes.InputFilter(MIN_LENGTH, MAX_LENGTH)
        });

        field.setOnEditorActionListener((v, actionId, event) -> {
            if (event.getAction() == KeyEvent.ACTION_DOWN || actionId == KeyEvent.KEYCODE_ENTER) {
                InputMethodUtils.hideVirtualKeyboard(view);
            }

            return false;
        });

        final AppCompatButton generate = binding.generateButton;
        generate.setOnClickListener(v -> {
            final String text = String.valueOf(field.getText());
            if (TextUtils.isEmpty(text)) {
                Toast.makeText(getContext(),
                                "Введите количество эмоджи",
                                Toast.LENGTH_SHORT)
                        .show();
            } else {
                final int textValue = Integer.parseInt(text);
                if (getContext() != null) {
                    ClipboardUtils.save(getContext(),
                            HINT,
                            generator.generator(textValue));

                    generate.setText(R.string.refresh_button_text);
                    Toast.makeText(getContext(),
                                    HINT,
                                    Toast.LENGTH_SHORT)
                            .show();
                }
            }
        });
        return view;
    }

    public void setCallbackFragment(CallbackFragment fragment) { this.fragment = fragment; }

    private void closeFragment() {
        FragmentManager manager = getParentFragmentManager();
        manager.popBackStack();
    }
}