package io.bitsmart.bdd.report.utils.carbuilder.model;

import java.util.List;
import java.util.Objects;

public class Car {
    final SimpleEngine engine;
    final List<Wheel> wheels;

    public Car(SimpleEngine engine, List<Wheel> wheels) {
        this.engine = engine;
        this.wheels = wheels;
    }

    public SimpleEngine getEngine() {
        return engine;
    }

    public List<Wheel> getWheels() {
        return wheels;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Car)) return false;
        Car car = (Car) o;
        return Objects.equals(engine, car.engine) && Objects.equals(wheels, car.wheels);
    }

    @Override
    public int hashCode() {
        return Objects.hash(engine, wheels);
    }

    @Override
    public String toString() {
        return "Car{" +
            "engine=" + engine +
            ", wheels=" + wheels +
            '}';
    }
}
