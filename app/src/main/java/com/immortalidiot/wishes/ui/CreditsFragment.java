package com.immortalidiot.wishes.ui;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.FragmentManager;

import com.immortalidiot.wishes.databinding.FragmentCreditsBinding;

public class CreditsFragment extends FragmentUtils {
    FragmentCreditsBinding binding;
    CallbackFragment fragment;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentCreditsBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        binding.backButton.setOnClickListener(v -> {
            if (fragment != null) {
                closeFragment();
            }
        });

        binding.ImmortalIdiotTextView.setOnClickListener(v -> {
            try {
                Intent telegram = new Intent(Intent.ACTION_VIEW);
                telegram.setData(Uri.parse("https://t.me/Immortal_Idiot"));
                startActivity(telegram);
            } catch (Exception e) { throw new RuntimeException(e); }
        });

        binding.kipish080TextView.setOnClickListener(v -> {
            try {
                Intent telegram = new Intent(Intent.ACTION_VIEW);
                telegram.setData(Uri.parse("https://t.me/kipish_080"));
                startActivity(telegram);
            } catch (Exception e) { throw new RuntimeException(e); }
        });
        return view;
    }

    public void setCallbackFragment(CallbackFragment fragment) { this.fragment = fragment; }
    private void closeFragment() {
        FragmentManager manager = getParentFragmentManager();
        manager.popBackStack();
    }
}