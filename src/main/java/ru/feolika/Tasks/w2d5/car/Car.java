package ru.feolika.Tasks.w2d5.car;

import ru.feolika.Tasks.w2d5.carComponent.CarColor;
import ru.feolika.Tasks.w2d5.carComponent.CarEngine;
import ru.feolika.Tasks.w2d5.carComponent.CarType;

/**
 * Автомобиль
 */
public class Car {
    private CarColor color;
    private CarType carType;
    private CarEngine engine;
    private int numberOfSeats;
    private double fuel = 0;

    public Car(CarColor color, CarType carType, CarEngine engine, int numberOfSeats) {
        this.color = color;
        this.carType = carType;
        this.engine = engine;
        this.numberOfSeats = numberOfSeats;
    }

    public CarColor getColor() {
        return color;
    }

    public CarType getCarType() {
        return carType;
    }

    public CarEngine getEngine() {
        return engine;
    }

    public int getNumberOfSeats() {
        return numberOfSeats;
    }

    public double getFuel() {
        return fuel;
    }

    public void setFuel(double fuel) {
        this.fuel = fuel;
    }

    @Override
    public String toString() {
        return "Car{" +
               "color=" + color +
               ", carType=" + carType +
               ", engine=" + engine +
               ", numberOfSeats=" + numberOfSeats +
               ", fuel=" + fuel +
               '}';
    }
}
