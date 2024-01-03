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
import com.immortalidiot.wishes.databinding.FragmentNightWishGeneratorBinding;

public class NightWishGeneratorFragment extends FragmentUtils {
    FragmentNightWishGeneratorBinding binding;
    WishGenerator generator = new WishGenerator();
    CallbackFragment fragment;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentNightWishGeneratorBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        binding.nightWishBackButton.setOnClickListener(v -> {
            if (fragment != null) {
                closeFragment();
            }
        });

        final TextInputEditText valueField = binding.nightWishValueField;
        valueField.setTransformationMethod(new NumericKeyBoardTransformation());
        final int MIN_LENGTH = 1;
        final int MAX_LENGTH = 2000;
        valueField.setFilters(new InputFilter[] {
                new com.immortalidiot.wishes.InputFilter(MIN_LENGTH, MAX_LENGTH)
        });

        binding.nightWishGenerateButton.setOnClickListener(v -> {
            final String wish = generator.getRandomNightWish();
            final String emojis = String.valueOf(valueField.getText());

            if (TextUtils.isEmpty(emojis)) {
                Toast.makeText(getContext(),
                               "Введите количество эмоджи",
                               Toast.LENGTH_SHORT)
                        .show();
            } else {
                if (getContext() != null) {
                    final int numberOfEmojis = Integer.parseInt(emojis);
                    ClipboardUtils.save(getContext(),
                            String.valueOf(R.string.wish_copied_hint),
                            (wish + generator.generator(numberOfEmojis)));

                    final TextView nightWishGeneratorTextView = binding.nightWishHint;
                    final String output = "Комплимент: " + wish;
                    nightWishGeneratorTextView.setTextSize(20);
                    nightWishGeneratorTextView.setText(output);
                    binding.nightWishGenerateButton.setText("Обновить");
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