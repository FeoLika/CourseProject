package ru.feolika.Tasks.w1d5;

import java.io.Serial;
import java.io.Serializable;

/**
 * Класс, входящий в состав Person
 */
public class Address implements Serializable {
    /**
     * serialVersionUID
     */
    @Serial
    private static final long serialVersionUID = 22321321321321321L;
    /**
     * улица
     */
    private String street;
    /**
     * город
     */
    private String city;
    /**
     * страна
     */
    private String country;
    /**
     * почтовый индекс
     */
    private int zip;

    /**
     * конструктор
     *
     * @param street  улица
     * @param city    город
     * @param country страна
     * @param zip     почтовый индекс
     */
    public Address(String street, String city, String country, int zip) {
        this.street = street;
        this.city = city;
        this.country = country;
        this.zip = zip;
    }

    @Override
    public String toString() {
        return "Address{" +
                "street= " + street +
                ", city= " + city +
                ", country= " + country +
                ", zip= " + zip +
                '}';
    }
}
