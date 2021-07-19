package io.bitsmart.bdd.report.utils.simplecarbuilder.model;

import java.util.Objects;

public class SimpleCar {
    final SimpleEngine engine;

    public SimpleCar(SimpleEngine engine) {
        this.engine = engine;
    }

    public SimpleEngine getEngine() {
        return engine;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SimpleCar)) return false;
        SimpleCar car = (SimpleCar) o;
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
