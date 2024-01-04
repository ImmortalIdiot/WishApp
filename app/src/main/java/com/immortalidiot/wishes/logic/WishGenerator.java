package com.immortalidiot.wishes.logic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class WishGenerator {
    private static final List<String> LIST_OF_EMOJIS = new ArrayList<>(Arrays.asList("♥", "💘", "💝",
            "💖", "💗", "💓", "💞", "💕", "❣", "❤", "😻", "🥰", "😍", "😘"));

    private static final List<String> DAY_LIST = new ArrayList<>(Arrays.asList("Доброе утро",
            "Доброе утречко", "С добрым утречком", "С добрым утром"));

    private static final List<String> NIGHT_LIST = new ArrayList<>(Arrays.asList("Споки ноки",
            "Спокойной ночи", "Спокойной ночки", "Сладких снов"));

    private static final List<String> COMPLIMENTS = new ArrayList<>(Arrays.asList("моя киса",
            "моя принцесса", "моя зайка", "моя тигрица", "моё солнышко", "моя крошка", "моя сладкая",
            "моя красавица", "моя красотка", "мой смайлик", "моя пироженка", "детка"));

    private String emojis = "";
    private int len = 0;

    private static String getRandomElement(List<String> list) {
        Random random = new Random();
        return list.get(random.nextInt(list.size()));
    }

    private static int getRandomIntegerValue() {
        Random random = new Random();
        return random.nextInt((150) + 1);
    }

    public String generator(int value) {
        while (len < value) {
            emojis += getRandomElement(LIST_OF_EMOJIS);
            len++;
        }
        return emojis;
    }

    public String getRandomDayWish() {
        int chance = getRandomIntegerValue();
        String dayWish = getRandomElement(DAY_LIST);

        if (chance < 140)  dayWish += ", " + getRandomElement(COMPLIMENTS);

        return dayWish;
    }

    public String getRandomNightWish() {
        int chance = getRandomIntegerValue();

        String nightWish;
        if (chance > 135) { nightWish = "Споки ноки в обе щёки"; }
        else if  (chance > 130) { nightWish = "Споки ноки нежно щёки"; }
        else nightWish = getRandomElement(NIGHT_LIST);

        chance = getRandomIntegerValue();
        if (chance > 140) { return nightWish; }
        else return nightWish + (", " + getRandomElement(COMPLIMENTS));
    }
}
