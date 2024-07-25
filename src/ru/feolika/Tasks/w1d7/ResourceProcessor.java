package ru.feolika.Tasks.w1d7;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * класс для обработки ресурса
 */
public class ResourceProcessor implements Runnable {
    /**
     * путь к ресурсу
     */
    private final String source;
    /**
     * масив искомымых слов
     */
    private final String[] words;
    /**
     * путь к результирующему файлу
     */
    private final String resultFile;
    /**
     * объект для синхронизации
     */
    private final static Object lock = new Object();
    /**
     * коллекция предложений, в которых есть одно из искомых слов
     */
    private final List<String> sentences;
    /**
     * регулярное выражение для поиска предложений, в который содержатся искомые слова
     */
    private final String REGEX;
    /**
     * регулярное выражение для поиска последнего неоконченного предложения в блоке ресурса
     */
    private final String REGEX_OF_LAST_SENTENCE = "[A-ZА-Я][^.!?]*(?=$)";

    /**
     * конструктор
     *
     * @param source путь к ресурсу
     * @param words  масив искомымых слов
     * @param res    путь к результирующему файлу
     * @throws URISyntaxException
     * @throws IOException
     */
    public ResourceProcessor(String source, String[] words, String res) throws URISyntaxException, IOException {
        this.source = source;
        this.words = words;
        this.resultFile = res;
        REGEX = createRegex(words);
        this.sentences = new LinkedList<>();
    }

    /**
     * создание регулярного выражения
     *
     * @param words масив искомымых слов
     * @return регулярное выражение для поиска предложений,
     * которые начинаются с заглавной буквы,
     * в которых есть одно из искомых слов
     * и оканчиваются точкой, восклицательным, вопросительным знаком или многоточием
     */
    private String createRegex(String[] words) {
        StringBuilder regex = new StringBuilder();
        regex.append("(?=[A-ZА-Я])[^.?!]*(?i:\\b(");
        regex.append(String.join("|", words));
        regex.append(")\\b)[^.?!]*(?:\\.{3}|[.!?])");
        return regex.toString();
    }

    /**
     * очистка результирующего файла
     *
     * @param resultFile путь к результирующему файлу
     * @throws IOException
     */
    public static void clearFile(String resultFile) throws IOException {
        FileWriter fileWriter = new FileWriter(new File(resultFile), false);
        fileWriter.close();
    }

    /**
     * поиск предложений в ресурсе по регелярному выражению
     *
     * @throws IOException
     */
    public void findOccurrences() throws IOException {
        char[] buffer = new char[1024 * 2];
        int bytesRead;
        String endOfBlock = "";
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new URL(source).openStream()))) {
            while ((bytesRead = reader.read(buffer)) != -1) {
                String block = endOfBlock + new String(buffer, 0, bytesRead);
                Pattern pattern = Pattern.compile(REGEX);
                Matcher matcher = pattern.matcher(block);
                while (matcher.find()) {
                    sentences.addLast(matcher.group());
                }
                pattern = Pattern.compile(REGEX_OF_LAST_SENTENCE);
                matcher = pattern.matcher(block);

                endOfBlock = matcher.find() ? matcher.group() : "";
            }
        }
    }

    /**
     * запись найденных предложений в результирующий файл
     */
    private void writeToFileAndClose() {
        synchronized (lock) {
            try (FileWriter fileWriter = new FileWriter(resultFile, true)) {
                fileWriter.write("Sentences in " + source + " = " + sentences.size() + "\n\r"
                        + "Thread name : " + Thread.currentThread().getName() + "\n\r"
                        + String.join("\n", sentences) + "\n\r");

            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    /**
     * запуск поиска предложений и записи их в результирующий файл в новом потоке
     */
    @Override
    public void run() {
        try {
            findOccurrences();
            writeToFileAndClose();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
