package io.bitsmart.bdd.report.utils.carbuilder.valuebuilders;

import io.bitsmart.bdd.report.utils.Builder;
import io.bitsmart.bdd.report.utils.ValueOrBuilder;
import io.bitsmart.bdd.report.utils.carbuilder.model.Car;
import io.bitsmart.bdd.report.utils.carbuilder.model.Engine;

public final class CarBuilder implements Builder<Car> {
    ValueOrBuilder<Engine> engine;

    private CarBuilder() {
    }

    public static CarBuilder aCar() {
        return new CarBuilder();
    }

    public CarBuilder withEngine(Engine engine) {
        this.engine = new ValueOrBuilder<>(engine);
        return this;
    }

    public CarBuilder withEngine(EngineBuilder engine) {
        this.engine = new ValueOrBuilder<>(engine);
        return this;
    }

    public Car build() {
        return new Car(engine.get());
    }
}
