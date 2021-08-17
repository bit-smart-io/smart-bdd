package io.bitsmart.bdd.report.utils.carbuilder;

import io.bitsmart.bdd.report.utils.carbuilder.builders.CarBuilder;
import io.bitsmart.bdd.report.utils.carbuilder.builders.WheelBuilder;
import io.bitsmart.bdd.report.utils.carbuilder.model.Car;
import io.bitsmart.bdd.report.utils.carbuilder.model.EngineType;
import io.bitsmart.bdd.report.utils.carbuilder.model.SimpleEngine;
import io.bitsmart.bdd.report.utils.carbuilder.model.Wheel;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static io.bitsmart.bdd.report.utils.carbuilder.builders.CarBuilder.aCar;
import static io.bitsmart.bdd.report.utils.carbuilder.builders.SimpleEngineBuilder.anEngine;

class CarBuilderTest {
    private static final EngineType ENGINE_TYPE = EngineType.PETROL;
    private static final int ENGINE_SIZE = 1;
    private static final int WHEEL_SIZE = 1;

    @Test
    void buildCarUsingBuilders() {
        Car car = aDefaultCar().build();
        Assertions.assertThat(car.getEngine()).isEqualTo(new SimpleEngine(ENGINE_TYPE, ENGINE_SIZE));
    }

    @Test
    void updatingTheCarEngineAndWheels() {
        int anotherEngineSize = ENGINE_SIZE + 1;
        int anotherWheelSize = ENGINE_SIZE + 1;
        CarBuilder carBuilder = aDefaultCar();
        carBuilder.updateEngine().withSize(anotherEngineSize);
        carBuilder.updateWheels().forEach(builder -> builder.withSize(anotherWheelSize));

        Assertions.assertThat(carBuilder.build()).isEqualTo(
            new Car(
                new SimpleEngine(ENGINE_TYPE, anotherEngineSize),
                Arrays.asList(
                    new Wheel(WHEEL_SIZE + 1),
                    new Wheel(WHEEL_SIZE + 1),
                    new Wheel(WHEEL_SIZE + 1),
                    new Wheel(WHEEL_SIZE + 1)
                )
            )
        );
    }

    private CarBuilder aDefaultCar() {
        return aCar()
            .withEngine(anEngine()
                .withType(ENGINE_TYPE)
                .withSize(ENGINE_SIZE))
            .withWheels(Arrays.asList(aDefaultWheel(), aDefaultWheel(), aDefaultWheel(), aDefaultWheel()));
    }

    private WheelBuilder aDefaultWheel() {
        return WheelBuilder.aWheel().withSize(WHEEL_SIZE);
    }
}

