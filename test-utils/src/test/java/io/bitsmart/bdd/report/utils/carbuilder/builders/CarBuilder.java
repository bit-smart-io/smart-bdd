package io.bitsmart.bdd.report.utils.carbuilder.builders;

import io.bitsmart.bdd.report.utils.Builder;
import io.bitsmart.bdd.report.utils.BuilderUtils;
import io.bitsmart.bdd.report.utils.carbuilder.model.Car;

import java.util.ArrayList;
import java.util.List;

public final class CarBuilder implements Builder<Car> {
    SimpleEngineBuilder engine;

    //TODO list of List<WheelBuilder> or WheelsBuilder
    // List<WheelBuilder> doesn't handle nulls very well
    List<WheelBuilder> wheels = new ArrayList<>();

    private CarBuilder() {
    }

    public static CarBuilder aCar() {
        return new CarBuilder();
    }

    public CarBuilder withEngine(SimpleEngineBuilder engine) {
        this.engine = engine;
        return this;
    }

    public CarBuilder withWheels(List<WheelBuilder> wheels) {
        this.wheels = wheels;
        return this;
    }

    public List<WheelBuilder> updateWheels() {
        return wheels;
    }

    public SimpleEngineBuilder updateEngine() {
        return engine;
    }

    public Car build() {
        return new Car(engine.build(), BuilderUtils.build(wheels));
    }
}
