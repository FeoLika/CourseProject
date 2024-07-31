package ru.feolika.Tasks.w1d3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.TreeSet;

/**
 * Generic Class MathBox, хранящий коллекцию уникальных чисел
 * @param <T>
 */
public class MathBox <T extends Number> extends ObjectBox{
    /**
     * Заполнение коллекции отсортированными уникальными числами
     * @param array массив чисел
     */
    public MathBox(T[] array){
        super(new TreeSet<>().toArray());
        TreeSet<T> sortedElements = new TreeSet<>(Arrays.asList(array));
        this.collectionOfObjects.addAll(sortedElements);
    }

    /**
     * Суммирование всех чисел коллекций
     * @return сумма всех чисел коллекции в double
     */
    public double summator(){
        double sum = 0.0;
        for (Object element : collectionOfObjects) {
            try {
                sum += ((T) element).doubleValue();
            } catch (Exception e){
                System.out.println(e.getClass());
            }
        }
        return sum;
    }

    /**
     * Деление всех элементов коллекции на int
     * @param divider делитель
     * @return коллекция чисел после деления
     */
    public Collection<Double> splitter(int divider){
        if (divider == 0){
            throw new ArithmeticException("Нельзя делить на ноль");
        }
        Collection<Double> result = new ArrayList<>();
        for (Object element : collectionOfObjects) {
            try {
                result.add(((T)element).doubleValue() / divider);
            } catch (Exception e){
                System.out.println(e.getClass());
            }
        }
        return result;
    }

    /**
     * Добавление числа в коллекцию
     * @param obj добавляемый объект
     */
    @Override
    public void addObject(Object obj) {
        if (!(obj instanceof Number)) {
            throw new IllegalArgumentException("Можно добавлять только числа");
        }
        super.addObject(obj);
    }
}
