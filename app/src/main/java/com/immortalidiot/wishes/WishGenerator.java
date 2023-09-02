package com.immortalidiot.wishes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class WishGenerator {


    static final List<String> listEmojis = new ArrayList<>(Arrays.asList("♥", "💘", "💝", "💖", "💗", "💓", "💞",
            "💕", "❣", "❤", "😻", "🥰", "😍", "😘"));

    static final List<String> dayList = new ArrayList<>(Arrays.asList("Доброе утро", "Доброе утречко",
            "С добрым утречком", "С добрым утром"));

    static final List<String> compliments = new ArrayList<>(Arrays.asList("моя киса", "моя принцесса", "моя зайка",
            "моя тигрица", "моё солнышко", "моя крошка", "моя сладкая", "моя красавица", "моя красотка",
            "мой смайлик", "моя пироженка", "детка"));

    private static String getRandomElement(List<String> list) {
        Random random = new Random();
        return list.get(random.nextInt(list.size()));
    }

    private static int getRandomIntegerValue() {
        Random random = new Random();
        return random.nextInt((150) + 1);
    }

    private String emojis = "";
    private int len = 0;
    @SuppressWarnings("StringConcatenationInLoop")
    public String generator(int value) {
        while (len < value) {
            emojis += getRandomElement(listEmojis);
            len++;
        }
        return emojis;
    }

    static String dayWish;

    public String getRandomDayWish() {
        int chance = getRandomIntegerValue();

        if (chance >= 140)  dayWish = getRandomElement(dayList);
        else dayWish = getRandomElement(dayList) + ", " + getRandomElement(compliments);

        return dayWish;
    }
}
