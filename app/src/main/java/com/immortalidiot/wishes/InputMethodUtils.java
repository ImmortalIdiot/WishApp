package com.immortalidiot.wishes;

import android.content.Context;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

public class InputMethodUtils {
    public static void hideVirtualKeyboard(View view) {
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) view
                    .getContext()
                    .getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }
}
