package ru.feolika.Tasks.w1d5;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.lang.reflect.Field;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationTargetException;

/**
 * Класс для сериализации и десериализации объектов
 */
public class Serializer {
    /**
     * Метод сериализации
     *
     * @param object класс для сериализации
     * @param file   путь к файлу
     */
    public static void serialize(Object object, String file) {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file))) {
            Class<?> classSer = object.getClass();
            Field[] fields = classSer.getDeclaredFields();

            for (Field field : fields) {
                field.setAccessible(true);
                out.writeObject(field.get(object));
            }

        } catch (IOException | IllegalAccessException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Метод десериализации
     *
     * @param file путь к файлу
     * @return десериализированный объект
     */
    public static Object deSerialize(String file) {
        Object object = null;
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(file))) {
            Class<?> classSer = Class.forName("ru.feolika.Tasks.w1d5.Person");
            object = classSer.getDeclaredConstructor().newInstance();
            Field[] fields = classSer.getDeclaredFields();

            for (Field field : fields) {
                field.setAccessible(true);
                field.set(object, in.readObject());
            }

        } catch (IOException | ClassNotFoundException | IllegalAccessException | InvocationTargetException |
                 InstantiationException | NoSuchMethodException e) {
            System.out.println(e.getMessage());
        }
        return object;
    }
}
