package ru.feolika.Tasks.w1d4;

import java.util.Random;

/**
 * Класс для работы с текстом
 */
public class TextGenerator {
    /**
     * Генератор рандома
     */
    private Random random = new Random();
    /**
     * максимальное количество слов в предложении
     */
    private final int MAX_WORD_IN_SENTENCE = 15;
    /**
     * максимальное предложений в абзаце
     */
    private final int MAX_SENTENCES_IN_PARAGRAPH = 20;
    /**
     * Вероятность запятой
     */
    private final int PROBABILITY_OF_COMMA = 10;
    /**
     * Массив символов конца предложения
     */
    private final String[] END_OF_SENTENCE = new String[]{"! ", "? ", ". "};
    /**
     * Разрыв строки и перенос каретки
     */
    private final String END_OF_PARAGRAPH = "\r\n";
    /**
     * Массив слов
     */
    private final String[] words;
    /**
     * Вероятность вхождения одного из слов этого массива в следующее предложение
     */
    private final int probability;

    /**
     * Конструктор
     * @param words массив слов
     * @param probability Вероятность вхождения одного из слов этого массива в следующее предложение
     */
    public TextGenerator(String[] words, int probability){
        this.words = words;
        this.probability = probability;
    }

    /**
     * Генерирование текста с количеством абзацев = countOfParagraphs. В каждом абзаце 1-20 предложений
     * @param countOfParagraphs количество абзацев
     * @return текст
     * @throws Exception ошибка "Слишком маленькая вероятность"
     */
    public String generateText(int countOfParagraphs) throws Exception {
        StringBuilder text = new StringBuilder();
        for (int i = 0; i < countOfParagraphs; i++) {
            int paragraphLength = random.nextInt(MAX_SENTENCES_IN_PARAGRAPH) + 1;
            for (int j = 0; j < paragraphLength; j++) {
                text.append(generateSentence());
            }
            text.append(END_OF_PARAGRAPH);
        }
        return text.toString();
    }

    /**
     * Гененрирование предложения. В каждом предложении 1-15 слов
     * @return Предложение
     * @throws Exception ошибка "Слишком маленькая вероятность"
     */
    private String generateSentence() throws Exception {
        StringBuilder sentence = new StringBuilder();
        int sentenceLength = random.nextInt(MAX_WORD_IN_SENTENCE) + 1;
        for (int i = 0; i < sentenceLength; i++) {
            sentence.append(getWord(i == 0));
            sentence.append(getSplitter(i == sentenceLength - 1));
        }
        return sentence.toString();
    }

    /**
     * Генерация слова. Каждое слово состоит из 1-15 символов
     * @param isFirstWordInSentence Является ли слово первым в предложении
     * @return Слово
     * @throws Exception ошибка "Слишком маленькая вероятность"
     */
    private String getWord(boolean isFirstWordInSentence) throws Exception {
        for (String word : words) {
            if (Math.random() < 1.0 / probability) {
                if (isFirstWordInSentence) {
                    return word.substring(0, 1).toUpperCase() + word.substring(1);
                } else {
                    return word;
                }
            }
        }
        throw new Exception("Слишком маленькая вероятность");
    }

    /**
     * Генерация разделителя слов
     * @param isLastWordInSentence Является ли слово последним в предложении
     * @return Если между слов - пробел или запятая с пробелом
     * Если конец предложения - один из символов окончания предложения
     */
    private String getSplitter(boolean isLastWordInSentence) {
        if (!isLastWordInSentence) {
            if (Math.random() < 1.0 / PROBABILITY_OF_COMMA) {
                return ", ";
            }
            return " ";
        } else {
            return END_OF_SENTENCE[random.nextInt(3)];
        }
    }
}
