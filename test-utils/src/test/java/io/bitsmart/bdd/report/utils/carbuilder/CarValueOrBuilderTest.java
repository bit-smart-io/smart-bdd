package io.bitsmart.bdd.report.utils.carbuilder;

import io.bitsmart.bdd.report.utils.carbuilder.model.Car;
import io.bitsmart.bdd.report.utils.carbuilder.valuebuilders.CarBuilder;
import io.bitsmart.bdd.report.utils.carbuilder.model.Engine;
import io.bitsmart.bdd.report.utils.carbuilder.model.EngineType;
import org.junit.jupiter.api.Test;

import static io.bitsmart.bdd.report.utils.carbuilder.valuebuilders.CarBuilder.aCar;
import static io.bitsmart.bdd.report.utils.carbuilder.valuebuilders.EngineBuilder.anEngine;
import static org.assertj.core.api.Assertions.assertThat;

class CarValueOrBuilderTest {
    private static final EngineType ENGINE_TYPE = EngineType.PETROL;
    private static final int ENGINE_SIZE = 1;

    @Test
    void buildCarUsingValues() {
        Car car = aCar()
            .withEngine(anEngine()
                .withType(ENGINE_TYPE)
                .withSize(ENGINE_SIZE)
                .build())
            .build();

        assertThat(car.getEngine()).isEqualTo(new Engine(ENGINE_TYPE, ENGINE_SIZE));
    }

    @Test
    void buildCarUsingBuilders() {
        Car car = aCar()
            .withEngine(anEngine()
                .withType(ENGINE_TYPE)
                .withSize(ENGINE_SIZE))
            .build();

        assertThat(car.getEngine()).isEqualTo(new Engine(ENGINE_TYPE, ENGINE_SIZE));
    }

    /**
     * There's no good way to access the engine and modify it.
     * We don't know if the engine is a value or a builder.
     * If everything was a builder then we wouldn't have this issue.
     */
    @Test
    void updatingADefaultCarEngineRequiresANewEngine() {
        int anotherEngineSize = ENGINE_SIZE + 1;

        CarBuilder carBuilder = aCar()
            .withEngine(anEngine()
                .withType(ENGINE_TYPE)
                .withSize(ENGINE_SIZE));

        Car car = carBuilder.withEngine(anEngine()
            .withType(ENGINE_TYPE)
            .withSize(anotherEngineSize))
            .build();

        assertThat(car.getEngine()).isEqualTo(new Engine(ENGINE_TYPE, anotherEngineSize));
    }
}

