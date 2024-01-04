package com.immortalidiot.wishes.ui;

import android.os.Bundle;
import android.text.InputFilter;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;

import com.google.android.material.textfield.TextInputEditText;
import com.immortalidiot.wishes.InputMethodUtils;
import com.immortalidiot.wishes.NumericKeyBoardTransformation;
import com.immortalidiot.wishes.R;
import com.immortalidiot.wishes.databinding.FragmentDayWishGeneratorBinding;
import com.immortalidiot.wishes.logic.ClipboardUtils;
import com.immortalidiot.wishes.logic.WishGenerator;

public class DayWishGeneratorFragment extends BaseFragment {
    FragmentDayWishGeneratorBinding binding;
    WishGenerator generator;
    CallbackFragment fragment;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentDayWishGeneratorBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        binding.dayWishBackButton.setOnClickListener(v -> {
            if (fragment != null) {
                closeFragment();
            }
        });

        final TextInputEditText valueField = binding.dayWishGeneratorValueField;
        valueField.setTransformationMethod(new NumericKeyBoardTransformation());
        final int MIN_LENGTH = 1;
        final int MAX_LENGTH = 2000;
        valueField.setFilters(new InputFilter[]{
                new com.immortalidiot.wishes.logic.InputFilter(MIN_LENGTH, MAX_LENGTH)});

        valueField.setOnEditorActionListener((v, actionId, event) -> {
            if (event.getAction() == KeyEvent.ACTION_DOWN || actionId == KeyEvent.KEYCODE_ENTER) {
                InputMethodUtils.hideVirtualKeyboard(view);
            }
            return false;
        });

        binding.dayWishGeneratorGenerateButton.setOnClickListener(v -> {
            generator = new WishGenerator();
            final String wish = generator.getRandomDayWish();
            final String emojis = String.valueOf(valueField.getText());

            if (TextUtils.isEmpty(emojis)) {
                Toast.makeText(getContext(),
                               "Введите количество эмоджи",
                               Toast.LENGTH_SHORT)
                        .show();
            } else {
                if (getContext() != null) {
                    final String fullExpression = wish +
                            generator.generator(Integer.parseInt(emojis));
                    ClipboardUtils.save(getContext(),
                                        String.valueOf(R.string.wish_copied_hint),
                                        fullExpression);

                    final TextView dayWishGeneratorTextView = binding.dayWishGeneratorHint;
                    final String output = "Комплимент: " + wish;

                    dayWishGeneratorTextView.setTextSize(20);
                    dayWishGeneratorTextView.setText(output);
                    binding.dayWishGeneratorGenerateButton.setText("Обновить");

                    Toast.makeText(getContext(),
                                   R.string.wish_copied_hint,
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