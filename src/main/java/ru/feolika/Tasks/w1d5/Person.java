package ru.feolika.Tasks.w1d5;

import java.io.Serial;
import java.io.Serializable;

/**
 * Класс для тестирования сериализации
 */
public class Person implements Serializable {
    /**
     * serialVersionUID
     */
    @Serial
    private static final long serialVersionUID = 7507618637130823075L;
    /**
     * имя
     */
    private String name;
    /**
     * возраст
     */
    private int age;
    /**
     * адрес
     */
    private Address address;

    /**
     * конструктор
     *
     * @param name    имя
     * @param age     возраст
     * @param address адрес
     */
    public Person(String name, int age, Address address) {
        this.name = name;
        this.age = age;
        this.address = address;
    }

    /**
     * пустой конструктор
     */
    public Person() {

    }

    /**
     * автозаполнение полей для тестирования
     */ {
        name = "Alex";
        age = 20;
        address = new Address("Lenina", "Izhevsk", "Russia", 123456);
    }

    @Override
    public String toString() {
        return "Person{" +
                "name= " + name +
                ", age= " + age +
                ", address =" + address +
                '}';
    }
}
