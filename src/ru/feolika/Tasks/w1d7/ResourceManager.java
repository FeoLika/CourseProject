package ru.feolika.Tasks.w1d7;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.RejectedExecutionException;

/**
 * Класс для обработки ресурсов
 */
public class ResourceManager implements Searchable {
    /**
     * Константа - Максимальное количество потоков
     */
    private final int MAX_THREAD = 5;
    /**
     * Список обработчиков ресурсов
     */
    private final List<ResourceProcessor> processors;

    /**
     * Конструктор
     *
     * @param sources массив путей ресурсов
     * @param words   массив искомых слов
     * @param res     путь к результирующему файлу
     */
    public ResourceManager(String[] sources, String[] words, String res) {
        processors = new ArrayList<>();
        try {
            for (String source : sources) {
                processors.add(new ResourceProcessor(source, words, res));
            }
            ResourceProcessor.clearFile(res);
        } catch (URISyntaxException | IOException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Метод для поиска предложений с искомыми словами в ресурсах
     *
     * @param sources массив путей ресурсов
     * @param words   массив искомых слов
     * @param res     путь к результирующему файлу
     * @throws NullPointerException
     * @throws RejectedExecutionException
     */
    @Override
    public void getOccurrences(String[] sources, String[] words, String res) throws NullPointerException, RejectedExecutionException {
        try (ExecutorService executor = Executors.newFixedThreadPool(MAX_THREAD)) {
            for (ResourceProcessor processor : processors) {
                executor.execute(processor);
            }
            executor.shutdown();
        } catch (NullPointerException | RejectedExecutionException e) {
            System.out.println(e.getMessage());
        }
    }
}
