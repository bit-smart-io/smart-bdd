package io.bitsmart.bdd.report.utils.simplecarbuilder;

import io.bitsmart.bdd.report.utils.simplecarbuilder.model.SimpleCar;
import io.bitsmart.bdd.report.utils.simplecarbuilder.valuebuilders.SimpleCarBuilder;
import io.bitsmart.bdd.report.utils.simplecarbuilder.model.SimpleEngine;
import io.bitsmart.bdd.report.utils.simplecarbuilder.model.EngineType;
import org.junit.jupiter.api.Test;

import static io.bitsmart.bdd.report.utils.simplecarbuilder.valuebuilders.SimpleCarBuilder.aCar;
import static io.bitsmart.bdd.report.utils.simplecarbuilder.valuebuilders.SimpleEngineBuilder.anEngine;
import static org.assertj.core.api.Assertions.assertThat;

class SimpleCarValueOrBuilderTest {
    private static final EngineType ENGINE_TYPE = EngineType.PETROL;
    private static final int ENGINE_SIZE = 1;

    @Test
    void buildACarUsingValues() {
        SimpleCar car = aCar()
            .withEngine(anEngine()
                .withType(ENGINE_TYPE)
                .withSize(ENGINE_SIZE)
                .build())
            .build();

        assertThat(car.getEngine()).isEqualTo(new SimpleEngine(ENGINE_TYPE, ENGINE_SIZE));
    }

    @Test
    void buildACarUsingBuilders() {
        SimpleCar car = aCar()
            .withEngine(anEngine()
                .withType(ENGINE_TYPE)
                .withSize(ENGINE_SIZE))
            .build();

        assertThat(car.getEngine()).isEqualTo(new SimpleEngine(ENGINE_TYPE, ENGINE_SIZE));
    }

    /**
     * There's no good way to access the engine and modify it.
     * We don't know if the engine is a value or a builder.
     * If everything was a builder then we wouldn't have this issue.
     */
    @Test
    void updatingTheCarEngineRequiresANewEngine() {
        int anotherEngineSize = ENGINE_SIZE + 1;

        SimpleCarBuilder carBuilder = aCar()
            .withEngine(anEngine()
                .withType(ENGINE_TYPE)
                .withSize(ENGINE_SIZE));

        SimpleCar car = carBuilder.withEngine(anEngine()
            .withType(ENGINE_TYPE)
            .withSize(anotherEngineSize))
            .build();

        assertThat(car.getEngine()).isEqualTo(new SimpleEngine(ENGINE_TYPE, anotherEngineSize));
    }
}

