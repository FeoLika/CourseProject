package ru.feolika.Tasks.w2d5.carBuilder;

import ru.feolika.Tasks.w2d5.car.Car;
import ru.feolika.Tasks.w2d5.carComponent.CarColor;
import ru.feolika.Tasks.w2d5.carComponent.CarEngine;
import ru.feolika.Tasks.w2d5.carComponent.CarType;

/**
 * конструктор автомобиля
 */
public class CarBuilder implements IBuilder {
    private CarType carType;
    private CarColor color;
    private CarEngine engine;
    private int numberOfSeats;

    @Override
    public void setCarType(CarType carType) {
        this.carType = carType;
    }

    @Override
    public void setColor(CarColor color) {
        this.color = color;
    }

    @Override
    public void setEngine(CarEngine engine) {
        this.engine = engine;
    }

    @Override
    public void setNumberOfSeats(int numberOfSeats) {
        this.numberOfSeats = numberOfSeats;
    }

    public Car getResult() {
        return new Car(color, carType, engine, numberOfSeats);
    }
}
