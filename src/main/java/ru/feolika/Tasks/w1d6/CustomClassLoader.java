package ru.feolika.Tasks.w1d6;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Кастомный ClassLoader
 */
public class CustomClassLoader extends ClassLoader {
    /**
     * Имя класса
     */
    private final String className;

    /**
     * конструктор
     *
     * @param parent    родительский класс ClassLoader
     * @param className имя класса
     */
    public CustomClassLoader(ClassLoader parent, String className) {
        super(parent);
        this.className = className;
    }

    /**
     * Загрузка класса
     *
     * @param classPath The <a href="#binary-name">binary name</a> of the class
     * @return Исполняемый класс
     * @throws ClassNotFoundException
     */
    @Override
    public Class<?> findClass(String classPath) throws ClassNotFoundException {
        try {
            byte[] bytes = fetchClassFromFS(classPath);
            return defineClass(className, bytes, 0, bytes.length);
        } catch (FileNotFoundException ex) {
            return super.findClass(className);
        } catch (IOException ex) {
            return super.findClass(className);
        }
    }

    /**
     * Загрузка бинарного файла исполняемого класса
     *
     * @param path путь к файлу
     * @return бинарный файл
     * @throws IOException
     */
    private byte[] fetchClassFromFS(String path) throws IOException {
        try (FileInputStream is = new FileInputStream(path)) {
            long length = new File(path).length();
            if (length > Integer.MAX_VALUE) {
                throw new IOException("File is too large");
            }
            byte[] bytes = new byte[(int) length];
            int offset = 0;
            int numRead = 0;
            while (offset < bytes.length
                    && (numRead = is.read(bytes, offset, bytes.length - offset)) >= 0) {
                offset += numRead;

            }
            if (offset < bytes.length) {
                throw new IOException("Could not completely read file " + path);
            }
            is.close();
            return bytes;
        }
    }
}
