package com.immortalidiot.wishes.ui;

import android.os.Bundle;
import android.text.InputFilter;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.FragmentManager;

import com.google.android.material.textfield.TextInputEditText;
import com.immortalidiot.wishes.ClipboardUtils;
import com.immortalidiot.wishes.NumericKeyBoardTransformation;
import com.immortalidiot.wishes.R;
import com.immortalidiot.wishes.WishGenerator;
import com.immortalidiot.wishes.databinding.FragmentDayWishGeneratorBinding;

public class DayWishGeneratorFragment extends FragmentUtils {
    FragmentDayWishGeneratorBinding binding;
    WishGenerator generator = new WishGenerator();
    CallbackFragment fragment;
    private final String HINT = "Пожелание скопировано в буфер обмена";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
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
                new com.immortalidiot.wishes.InputFilter(MIN_LENGTH, MAX_LENGTH)});

        binding.dayWishGeneratorGenerateButton.setOnClickListener(v -> {
            final String wish = generator.getRandomDayWish();
            final String emojis = String.valueOf(valueField.getText());

            if (TextUtils.isEmpty(emojis)) {
                Toast.makeText(getContext(),
                        "Введите количество эмоджи",
                        Toast.LENGTH_SHORT)
                        .show();
            } else {
                final int numberOfEmojis = Integer.parseInt(emojis);
                if (getContext() != null) {
                    ClipboardUtils.save(getContext(),
                            String.valueOf(R.string.wish_copied_hint),
                            (wish + generator.generator(numberOfEmojis)));

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