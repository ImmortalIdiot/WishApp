package com.immortalidiot.wishes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class WishGenerator {


    static final List<String> listEmojis = new ArrayList<>(Arrays.asList("♥", "💘", "💝", "💖", "💗", "💓", "💞",
            "💕", "❣", "❤", "😻", "🥰", "😍", "😘"));
    private String emojis = "";
    private int len = 0;
    @SuppressWarnings("StringConcatenationInLoop")
    public String generator(int value) {
        while (len < value) {
            emojis += getRandomElement();
            len++;
        }
        return emojis;
    }

    private static String getRandomElement() {
        Random random = new Random();
        return WishGenerator.listEmojis.get(random.nextInt(WishGenerator.listEmojis.size()));
    }
}
