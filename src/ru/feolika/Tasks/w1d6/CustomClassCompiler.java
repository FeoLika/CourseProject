package ru.feolika.Tasks.w1d6;

import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Класс для компиляции и запуска кода
 */
public class CustomClassCompiler {
    /**
     * Записывает код в java файл
     *
     * @param code код метода doWork
     * @param path путь к java файлу
     */
    public void writeCodeToClass(String code, String path) {

        try {
            File file = new File(path + ".java");
            FileInputStream inputStream = new FileInputStream(file);
            byte[] data = new byte[(int) file.length()];
            inputStream.read(data);
            inputStream.close();

            boolean insideDoWork = false;
            var fileCont = new String(data);
            String[] lines = fileCont.split("\n");
            StringBuilder updatedContent = new StringBuilder();

            for (String line : lines) {
                updatedContent.append(line).append("\n");
                if (line.contains("public void doWork() {")) {
                    updatedContent.append(code).append("\n");
                }
            }

            FileOutputStream fos = new FileOutputStream(file);
            fos.flush();
            fos.write(updatedContent.toString().getBytes());
            fos.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Компилирует java файл
     *
     * @param path путь к java файлу
     */
    public void compile(String path) {
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        compiler.run(null, null, null, path + ".java");
    }

    /**
     * Запускает метод doWork из CustomClassLoader
     *
     * @param pathToSomeClass   путь к java файлу
     * @param systemClassLoader системный ClassLoader
     * @param className         бинарное имя класса
     */
    public void runMethod(String pathToSomeClass, ClassLoader systemClassLoader, String className) {
        CustomClassLoader customClassLoader = new CustomClassLoader(systemClassLoader, className);
        Class<?> classLoader = null;
        try {
            classLoader = customClassLoader.loadClass(pathToSomeClass + ".class");
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
        Object instance = null;
        try {
            instance = classLoader.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            System.out.println(e.getMessage());
        }
        try {
            classLoader.getMethod("doWork").invoke(instance);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
