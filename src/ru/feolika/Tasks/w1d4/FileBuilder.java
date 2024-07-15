package ru.feolika.Tasks.w1d4;

import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Класс для создания файлов
 */
public class FileBuilder {
    /**
     * Запись текста в файл
     * @param fileName имя файла
     * @param text текст
     * @throws IOException ошибка "Не удалось найти файл"
     */
    private void writeInFile(String fileName, String text) throws IOException {
        try (FileOutputStream fileWriter = new FileOutputStream(fileName)) {
            byte[] buffer = text.getBytes();
            fileWriter.write(buffer, 0, buffer.length);
        }
    }

    /**
     * Создание файлов и запись в них сгенерированного текста
     * @param path путь к файлу
     * @param n количество файлов
     * @param size количество абзацев
     * @param words массив слов
     * @param probability вероятность вхождения одного из слов в следующее предложение
     * @throws IllegalArgumentException  ошибка "Неправильный ввод данных"
     */
    public void getFiles(String path, int n, int size, String[] words, int probability) throws Exception {
        if (n == 0 || size == 0 || words.length == 0 || probability == 0) {
            throw new IllegalArgumentException ("Неправильный ввод данных");
        }
        TextGenerator textGenerator = new TextGenerator(words, probability);
        for (int i = 1; i <= n; i++) {
            String filePath = path + "/Text_" + i + ".txt";
            String text = textGenerator.generateText(size);
            writeInFile(filePath, text);
        }
    }


}
