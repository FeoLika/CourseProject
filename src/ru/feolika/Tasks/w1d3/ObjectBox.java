package ru.feolika.Tasks.w1d3;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Class ObjectBox, хранящий коллекцию уникальных объектов
 */
public class ObjectBox {
    /**
     * коллекция уникальных объектов
     */
    protected Set<Object> collectionOfObjects;

    /**
     * Конструктор кастит массив объектов в HashSet
     * @param objects коллекция объектов
     */
    public ObjectBox(Object[] objects){
        this.collectionOfObjects = new HashSet<>(Arrays.asList(objects));
    }

    /**
     * Добавление объекта в коллекцию
     * @param obj добавляемый объект
     */
    public void addObject(Object obj){
        collectionOfObjects.add(obj);
    }

    /**
     * Удаление объекта из коллекции
     * @param obj удаляемый объект
     * @return true - если объект есть и удален
     * false - если объекта нет
     */
    public boolean deleteObject(Object obj){
        return collectionOfObjects.remove(obj);
    }

    /**
     * Вывод данных в консоль
     */
    public void dump(){
        System.out.println(toString());
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName() +
                '{' + Arrays.toString(collectionOfObjects.toArray()) +
                '}';
    }
    @Override
    public int hashCode() {
        return collectionOfObjects.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || obj.getClass() != this.getClass()) {
            return false;
        }
        ObjectBox difMathBox = (ObjectBox) obj;
        return collectionOfObjects.equals(difMathBox.collectionOfObjects);
    }
}
