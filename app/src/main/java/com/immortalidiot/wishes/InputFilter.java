package com.immortalidiot.wishes;

import android.text.Spanned;

public class InputFilter implements android.text.InputFilter {

    private final int min;
    private final int max;

    private final int MINIMUM = 1000;
    private final int MAXIMUM = 2999;

    public InputFilter(int min, int max) {
        this.min = min;
        this.max = max;
    }

    public InputFilter(String min, String max) {
        this.min = Integer.parseInt(min);
        this.max = Integer.parseInt(max);
    }

    @Override
    public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
        try {
            int input = Integer.parseInt(dest.toString() + source.toString());
            if (isInRange(min, max, input)) return null;
        } catch (NumberFormatException ignored) {
        }
        return "";
    }

    private boolean isInRange(int min, int max, int input) {
        return max > min ? input >= min && input <= max : input >= max && input <= min;
    }

    int clamp(int v, int min, int max) {
        return v > max ? max : (Math.max(v, min));
    }
}