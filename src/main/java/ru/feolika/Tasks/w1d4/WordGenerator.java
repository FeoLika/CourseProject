package ru.feolika.Tasks.w1d4;

import java.util.Random;

public class WordGenerator {

    private static Random random = new Random();
    private static final int WORDS_COUNT = 1000;
    private static final int MAX_LETTERS_IN_WORD = 15;

    public static String[] generateThousandWords() {
        String[] result = new String[WORDS_COUNT];

        for (int i = 0; i < WORDS_COUNT; i++) {
            int length = random.nextInt(MAX_LETTERS_IN_WORD) + 1;
            StringBuilder wordBuilder = new StringBuilder();
            for (int j = 0; j < length; j++) {
                char letter = (char) ('a' + random.nextInt('z' - 'a'));
                wordBuilder.append(letter);
            }
            result[i] = wordBuilder.toString();
        }
        return result;
    }


}
