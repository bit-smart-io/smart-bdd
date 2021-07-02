package io.bitsmart.bdd.report.utils.carbuilder.model;

import java.util.Objects;

public class Car {
    final Engine engine;

    public Car(Engine engine) {
        this.engine = engine;
    }

    public Engine getEngine() {
        return engine;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Car)) return false;
        Car car = (Car) o;
        return Objects.equals(engine, car.engine);
    }

    @Override
    public int hashCode() {
        return Objects.hash(engine);
    }

    @Override
    public String toString() {
        return "Car{" +
            "engine=" + engine +
            '}';
    }
}
