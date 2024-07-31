package ru.feolika.Tasks.w1d7;

import java.util.concurrent.RejectedExecutionException;

/**
 * Интерфейс для поиска совпадений в тексте
 */
public interface Searchable {
    void getOccurrences(String[] sources, String[] words, String res) throws NullPointerException, RejectedExecutionException;
}
