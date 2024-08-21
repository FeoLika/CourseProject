package ru.feolika.Tasks.w2d5;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.feolika.Tasks.w2d5.car.Car;
import ru.feolika.Tasks.w2d5.carBuilder.CarBuilder;
import ru.feolika.Tasks.w2d5.carComponent.CarColor;
import ru.feolika.Tasks.w2d5.carComponent.CarEngine;
import ru.feolika.Tasks.w2d5.carComponent.CarType;
import ru.feolika.Tasks.w2d5.director.Director;

public class Starter {
    private static final Logger LOGGER = LogManager.getLogger(ru.feolika.Tasks.w2d5.Starter.class);

    public static void main(String[] args) {
        Director director = new Director();

        CarBuilder sedanBuilder = new CarBuilder();
        director.constructSedan(new CarBuilder());
        Car sedan = sedanBuilder.getResult();
        LOGGER.info("Sedan: {}", sedan);

        CarBuilder sportsBuilder = new CarBuilder();
        director.constructSportsCar(sportsBuilder);
        Car sportsCar = sportsBuilder.getResult();
        LOGGER.info("Sports Car: {}", sportsCar);

        CarBuilder suvBuilder = new CarBuilder();
        director.constructSUV(suvBuilder);
        Car suv = suvBuilder.getResult();
        LOGGER.info("SUV: {}", suv);
    }
}
