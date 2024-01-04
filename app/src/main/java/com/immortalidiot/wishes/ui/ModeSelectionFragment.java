package com.immortalidiot.wishes.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.immortalidiot.wishes.databinding.FragmentModeSelectionBinding;

public class ModeSelectionFragment extends BaseFragment {
    FragmentModeSelectionBinding binding;
    CallbackFragment fragment;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentModeSelectionBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        binding.emojisGenerator.setOnClickListener(v -> {
            if (fragment != null) {
                fragment.changeFragment(new EmojisGeneratorFragment(), true);
            }
        });

        binding.dayWishGenerator.setOnClickListener(v -> {
            if (fragment != null) {
                fragment.changeFragment(new DayWishGeneratorFragment(), true);
            }
        });

        binding.nightWishGenerator.setOnClickListener(v -> {
            if (fragment != null) {
                fragment.changeFragment(new NightWishGeneratorFragment(), true);
            }
        });

        binding.developersButton.setOnClickListener(v -> {
            if (fragment != null) {
                fragment.changeFragment(new CreditsFragment(), true);
            }
        });
        return view;
    }

    public void setCallbackFragment(CallbackFragment fragment) {
        this.fragment = fragment;
    }
}