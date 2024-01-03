package com.immortalidiot.wishes.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.immortalidiot.wishes.databinding.FragmentModeSelectionBinding;

public class ModeSelectionFragment extends FragmentUtils {
    FragmentModeSelectionBinding binding;
    CallbackFragment fragment;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentModeSelectionBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        binding.loveEmojiGenerator.setOnClickListener(v -> {
            if (fragment != null) {
                fragment.changeFragment(new EmojisGeneratorFragment(), true);
            }
        });

        binding.dayWishGeneratorMode.setOnClickListener(v -> {
            if (fragment != null) {
                fragment.changeFragment(new DayWishGeneratorFragment(), true);
            }
        });

        binding.nightWishGeneratorMode.setOnClickListener(v -> {
            if (fragment != null) {
                fragment.changeFragment(new NightWishGeneratorFragment(), true);
            }
        });
        return view;
    }

    public void setCallbackFragment(CallbackFragment fragment) {
        this.fragment = fragment;
    }
}