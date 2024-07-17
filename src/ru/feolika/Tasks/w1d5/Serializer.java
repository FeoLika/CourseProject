package ru.feolika.Tasks.w1d5;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.lang.reflect.Field;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;


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
    public void serialize(Object object, String file) {
        try {
            // Create a map to store the serialized object
            Map<String, Object> serializedObject = new HashMap<>();

            // Serialize the object and its nested objects
            serializeObject(object, serializedObject);

            // Write the serialized object to a file
            FileWriter fileWriter = new FileWriter(file);
            fileWriter.write(toJSON(serializedObject, 0));
            fileWriter.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * рекурсивный метод сериализации объекта
     *
     * @param object           класс
     * @param serializedObject сериализированный объект
     */
    private void serializeObject(Object object, Map<String, Object> serializedObject) {
        Field[] fields = object.getClass().getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            String fieldName = field.getName();
            Object fieldValue = null;
            try {
                fieldValue = field.get(object);
            } catch (IllegalAccessException e) {
                System.out.println(e.getMessage());
            }

            if (fieldValue != null && !field.getType().isPrimitive() && !(field.getType() == String.class)) {
                Map<String, Object> nestedObject = new HashMap<>();
                serializeObject(fieldValue, nestedObject);
                serializedObject.put(fieldName, nestedObject);
            } else {
                serializedObject.put(fieldName, fieldValue);
            }
        }
    }

    /**
     * Рекурсивный метод преобразования в JSON
     *
     * @param serializedObject сериализированный объект
     * @param levelTab         уровень вложенности
     * @return JSON
     */
    private String toJSON(Map<String, Object> serializedObject, int levelTab) {
        StringBuilder json = new StringBuilder();

        StringBuilder tab = new StringBuilder();
        tab.append("\t".repeat(Math.max(0, levelTab)));
        json.append("{\n");
        try {
            for (Map.Entry<String, Object> entry : serializedObject.entrySet()) {
                json.append(tab + "\t\"").append(entry.getKey()).append("\": ");
                if (entry.getValue() instanceof Map) {
                    json.append(toJSON((Map<String, Object>) entry.getValue(), ++levelTab));
                } else {
                    json.append("\"").append(entry.getValue()).append("\"");
                }
                json.append(",\n");
            }

            json.deleteCharAt(json.length() - 2);
            json.append(tab + "}");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return json.toString();
    }

    /**
     * Метод десериализации
     *
     * @param file путь к файлу
     * @return десериализированный объект
     */
    public Object deSerialize(String file) {
        Object object = null;
        try {
            String jsonData = getJsonData(file);

            Class<?> classSer = Class.forName("ru.feolika.Tasks.w1d5.Person");
            object = classSer.getDeclaredConstructor().newInstance();

            setField(object, jsonData);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return object;
    }

    /**
     * Получение JSON из файла
     *
     * @param file путь к файлу
     * @return JSON
     * @throws FileNotFoundException
     */
    private static String getJsonData(String file) throws FileNotFoundException {
        File jsonFile = new File(file);
        Scanner scanner = new Scanner(jsonFile);

        StringBuilder jsonContent = new StringBuilder();
        while (scanner.hasNextLine()) {
            jsonContent.append(scanner.nextLine());
        }
        scanner.close();

        String jsonString = jsonContent.toString();
        int startIndex = jsonString.indexOf('{') + 1;
        int endIndex = jsonString.lastIndexOf('}');
        return jsonString.substring(startIndex, endIndex);
    }

    /**
     * Рекурсивная десериализация полей объекта
     *
     * @param object   класс для десериализации
     * @param jsonData JSON
     */
    private void setField(Object object, String jsonData) {
        Field[] fields = object.getClass().getDeclaredFields();

        String[] keyValuePairs = jsonData.split(",");
        try {
            for (String keyValuePair : keyValuePairs) {
                String[] keyValue = keyValuePair.trim().split(":");
                String key = keyValue[0].replaceAll("\"", "");
                String value = keyValue[1].trim().replaceAll("\"", "");

                for (Field field : fields) {
                    if (key.equals(field.getName())) {
                        field.setAccessible(true);
                        if (field.getType().isPrimitive() || field.getType() == String.class) {
                            field.set(object, value);
                        } else {
                            Object nestedObject = field.getType().getDeclaredConstructor().newInstance();
                            setField(nestedObject, value);
                            field.set(object, nestedObject);
                        }
                        break;
                    }
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
