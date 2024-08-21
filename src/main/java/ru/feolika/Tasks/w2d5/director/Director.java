package ru.feolika.Tasks.w2d5.director;

import ru.feolika.Tasks.w2d5.carBuilder.CarBuilder;
import ru.feolika.Tasks.w2d5.carComponent.CarColor;
import ru.feolika.Tasks.w2d5.carComponent.CarEngine;
import ru.feolika.Tasks.w2d5.carComponent.CarType;

public class Director {
    /**
     * конструктор спортивного автомобиля
     *
     * @param builder
     */
    public void constructSportsCar(CarBuilder builder) {
        builder.setCarType(CarType.SPORTS_CAR);
        builder.setColor(CarColor.BLACK);
        builder.setEngine(new CarEngine(3.0, 0));
        builder.setNumberOfSeats(2);
    }

    /**
     * конструктор седана
     *
     * @param builder
     */
    public void constructSedan(CarBuilder builder) {
        builder.setCarType(CarType.SEDAN);
        builder.setColor(CarColor.WHITE);
        builder.setEngine(new CarEngine(1.6, 0));
        builder.setNumberOfSeats(4);
    }

    /**
     * конструктор внедорожника
     *
     * @param builder
     */
    public void constructSUV(CarBuilder builder) {
        builder.setCarType(CarType.SUV);
        builder.setColor(CarColor.RED);
        builder.setEngine(new CarEngine(2.4, 0));
        builder.setNumberOfSeats(5);
    }
}
