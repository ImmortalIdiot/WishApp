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
import com.immortalidiot.wishes.logic.ClipboardUtils;
import com.immortalidiot.wishes.InputMethodUtils;
import com.immortalidiot.wishes.NumericKeyBoardTransformation;
import com.immortalidiot.wishes.R;
import com.immortalidiot.wishes.logic.WishGenerator;
import com.immortalidiot.wishes.databinding.FragmentEmojisGeneratorBinding;

public class EmojisGeneratorFragment extends BaseFragment {
    FragmentEmojisGeneratorBinding binding;
    WishGenerator generator;
    CallbackFragment fragment;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentEmojisGeneratorBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        binding.emojisGeneratorBackButton.setOnClickListener(v -> {
            if (fragment != null) {
                closeFragment();
            }
        });

        final TextInputEditText field = binding.emojisGeneratorValueField;
        field.setTransformationMethod(new NumericKeyBoardTransformation());
        int MIN_LENGTH = 1;
        int MAX_LENGTH = 2000;
        field.setFilters(new InputFilter[]{
                new com.immortalidiot.wishes.logic.InputFilter(MIN_LENGTH, MAX_LENGTH)
        });

        field.setOnEditorActionListener((v, actionId, event) -> {
            if (event.getAction() == KeyEvent.ACTION_DOWN || actionId == KeyEvent.KEYCODE_ENTER) {
                InputMethodUtils.hideVirtualKeyboard(view);
            }
            return false;
        });

        final AppCompatButton generate = binding.emojisGeneratorGenerateButton;
        generate.setOnClickListener(v -> {
            generator = new WishGenerator();
            final String text = String.valueOf(field.getText());
            if (TextUtils.isEmpty(text)) {
                Toast.makeText(getContext(),
                               "Введите количество эмоджи",
                               Toast.LENGTH_SHORT)
                        .show();
            } else {
                if (getContext() != null) {
                    final String expression = generator.generator(Integer.parseInt(text));
                    ClipboardUtils.save(getContext(),
                                        String.valueOf(R.string.text_copied_hint),
                                        expression);

                    generate.setText(R.string.refresh_button_text);
                    Toast.makeText(getContext(),
                                   R.string.text_copied_hint,
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