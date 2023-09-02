package com.immortalidiot.wishes;
import android.app.Activity;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;


public class ClipboardUtils {
    public static void save(Activity activity, String hint, CharSequence copiedText) {
        ClipboardManager clipboard = (ClipboardManager) activity.getSystemService(Context.CLIPBOARD_SERVICE);
        ClipData clip = ClipData.newPlainText(hint, copiedText);
        clipboard.setPrimaryClip(clip);
    }
}
