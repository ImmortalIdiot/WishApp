package com.immortalidiot.wishes;

import java.util.Locale;

public class SystemLanguageUtils {
    public static String fullWishLocalization() {
        if (Locale.getDefault().getLanguage().equalsIgnoreCase("ru")) {
            return "Комплимент: "; }
        else return "Compliment: ";
    }
}
