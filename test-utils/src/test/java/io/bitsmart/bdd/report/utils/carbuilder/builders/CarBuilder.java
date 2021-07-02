package io.bitsmart.bdd.report.utils.carbuilder.builders;

import io.bitsmart.bdd.report.utils.Builder;
import io.bitsmart.bdd.report.utils.carbuilder.model.Car;

public final class CarBuilder implements Builder<Car> {
    EngineBuilder engine;

    private CarBuilder() {
    }

    public static CarBuilder aCar() {
        return new CarBuilder();
    }

    public CarBuilder withEngine(EngineBuilder engine) {
        this.engine = engine;
        return this;
    }

    public EngineBuilder updateEngine() {
        return engine;
    }

    public Car build() {
        return new Car(engine.build());
    }
}
