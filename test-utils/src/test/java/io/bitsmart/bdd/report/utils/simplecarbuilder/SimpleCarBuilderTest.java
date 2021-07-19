package io.bitsmart.bdd.report.utils.simplecarbuilder;

import io.bitsmart.bdd.report.utils.simplecarbuilder.builders.SimpleCarBuilder;
import io.bitsmart.bdd.report.utils.simplecarbuilder.model.SimpleCar;
import io.bitsmart.bdd.report.utils.simplecarbuilder.model.SimpleEngine;
import io.bitsmart.bdd.report.utils.simplecarbuilder.model.EngineType;
import org.junit.jupiter.api.Test;

import static io.bitsmart.bdd.report.utils.simplecarbuilder.builders.SimpleCarBuilder.aCar;
import static io.bitsmart.bdd.report.utils.simplecarbuilder.builders.SimpleEngineBuilder.anEngine;
import static org.assertj.core.api.Assertions.assertThat;

class SimpleCarBuilderTest {
    private static final EngineType ENGINE_TYPE = EngineType.PETROL;
    private static final int ENGINE_SIZE = 1;

    @Test
    void buildACarUsingBuilders() {
        SimpleCar car = aDefaultCar().build();
        assertThat(car.getEngine()).isEqualTo(new SimpleEngine(ENGINE_TYPE, ENGINE_SIZE));
    }

    /**
     * Address short fall from CarValueOrBuilderTest#updatingADefaultCarEngineRequiresANewEngine()
     */
    @Test
    void updatingTheCarEngine() {
        int anotherEngineSize = ENGINE_SIZE + 1;
        SimpleCarBuilder simpleCarBuilder = aDefaultCar();
        simpleCarBuilder.updateEngine().withSize(anotherEngineSize);

        assertThat(simpleCarBuilder.build()).isEqualTo(
            new SimpleCar(new SimpleEngine(ENGINE_TYPE, anotherEngineSize)));
    }

    private SimpleCarBuilder aDefaultCar() {
        return aCar()
            .withEngine(anEngine()
                .withType(ENGINE_TYPE)
                .withSize(ENGINE_SIZE));
    }
}

