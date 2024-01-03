package com.immortalidiot.wishes;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;


public class ClipboardUtils {
    public static void save(Context context, String hint, CharSequence copiedText) {
        ClipboardManager clipboard = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
        ClipData clip = ClipData.newPlainText(hint, copiedText);
        clipboard.setPrimaryClip(clip);
    }
}
