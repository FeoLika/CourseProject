package ru.feolika.Tasks.w2d5.carBuilder;

import ru.feolika.Tasks.w2d5.carComponent.CarColor;
import ru.feolika.Tasks.w2d5.carComponent.CarEngine;
import ru.feolika.Tasks.w2d5.carComponent.CarType;

public interface IBuilder {
    void setCarType(CarType carType);

    void setColor(CarColor color);

    void setEngine(CarEngine engine);

    void setNumberOfSeats(int numberOfSeats);
}
