package com.immortalidiot.wishes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class WishGenerator {


    private static final List<String> listEmojis = new ArrayList<>(Arrays.asList("♥", "💘", "💝",
            "💖", "💗", "💓", "💞", "💕", "❣", "❤", "😻", "🥰", "😍", "😘"));

    private static final List<String> dayList = new ArrayList<>(Arrays.asList("Доброе утро",
            "Доброе утречко", "С добрым утречком", "С добрым утром"));

    private static final List<String> nightList = new ArrayList<>(Arrays.asList("Споки ноки",
            "Спокойной ночи", "Спокойной ночки", "Сладких снов"));

    private static final List<String> compliments = new ArrayList<>(Arrays.asList("моя киса",
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
            emojis += getRandomElement(listEmojis);
            len++;
        }
        return emojis;
    }

    public String getRandomDayWish() {
        int chance = getRandomIntegerValue();
        String dayWish = getRandomElement(dayList);

        if (chance < 140)  dayWish += ", " + getRandomElement(compliments);

        return dayWish;
    }

    public String getRandomNightWish() {
        int chance = getRandomIntegerValue();

        String nightWish;
        if (chance > 135) { nightWish = "Споки ноки в обе щёки"; }
        else if  (chance > 130) { nightWish = "Споки ноки нежно щёки"; }
        else nightWish = getRandomElement(nightList);

        chance = getRandomIntegerValue();
        if (chance > 140) { return nightWish; }
        else return nightWish += ", " + getRandomElement(compliments);
    }
}
