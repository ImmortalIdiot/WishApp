package com.immortalidiot.wishes;

import android.text.method.PasswordTransformationMethod;
import android.view.View;

public class NumericKeyBoardTransformation extends PasswordTransformationMethod {
    @Override
    public CharSequence getTransformation(CharSequence source, View view) {
        return source;
    }
}
