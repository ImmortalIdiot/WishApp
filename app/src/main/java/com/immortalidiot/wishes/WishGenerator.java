package com.immortalidiot.wishes;

import java.util.List;
import java.util.Random;

public class WishGenerator {
    private static int getRandomIntegerValue(int min, int max) {
        Random random = new Random();
        return random.nextInt(max - min + 1) + min;
    }

    private static String getRandomElement(List<String> list) {
        Random random = new Random();
        return list.get(random.nextInt(list.size()));
    }
}
