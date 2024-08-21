package ru.feolika.Tasks.w2d5.carComponent;

/**
 * Двигатель автомобиля
 */
public class CarEngine {
    /**
     * Объем двигателя
     */
    private final double volume;
    /**
     * Пробег
     */
    private double mileage;
    /**
     * Старт
     */
    private boolean started;

    public void on() {
        started = true;
    }

    public void off() {
        started = false;
    }

    public boolean isStarted() {
        return started;
    }

    public double getVolume() {
        return volume;
    }

    public void drive(double mileage) {
        if (started) {
            this.mileage += mileage;
        } else {
            System.err.println("Start engine first!");
        }
    }

    public double getMileage() {
        return mileage;
    }

    public CarEngine(double volume, double mileage) {
        this.volume = volume;
        this.mileage = mileage;
    }

    @Override
    public String toString() {
        return "CarEngine{" +
               "volume=" + volume +
               ", mileage=" + mileage +
               '}';
    }
}
