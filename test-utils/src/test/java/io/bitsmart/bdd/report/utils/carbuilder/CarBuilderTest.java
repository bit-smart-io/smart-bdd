package io.bitsmart.bdd.report.utils.carbuilder;

import io.bitsmart.bdd.report.utils.carbuilder.builders.CarBuilder;
import io.bitsmart.bdd.report.utils.carbuilder.model.Car;
import io.bitsmart.bdd.report.utils.carbuilder.model.Engine;
import io.bitsmart.bdd.report.utils.carbuilder.model.EngineType;
import org.junit.jupiter.api.Test;

import static io.bitsmart.bdd.report.utils.carbuilder.builders.CarBuilder.aCar;
import static io.bitsmart.bdd.report.utils.carbuilder.builders.EngineBuilder.anEngine;
import static org.assertj.core.api.Assertions.assertThat;

class CarBuilderTest {
    private static final EngineType ENGINE_TYPE = EngineType.PETROL;
    private static final int ENGINE_SIZE = 1;

    @Test
    void buildCarUsingBuilders() {
        Car car = aDefaultCar().build();
        assertThat(car.getEngine()).isEqualTo(new Engine(ENGINE_TYPE, ENGINE_SIZE));
    }

    /**
     * Address short fall from CarValueOrBuilderTest#updatingADefaultCarEngineRequiresANewEngine()
     */
    @Test
    void updatingADefaultCarEngine() {
        int anotherEngineSize = ENGINE_SIZE + 1;
        CarBuilder carBuilder = aDefaultCar();
        carBuilder.updateEngine().withSize(anotherEngineSize);

        assertThat(carBuilder.build()).isEqualTo(
            new Car(new Engine(ENGINE_TYPE, anotherEngineSize)));
    }

    private CarBuilder aDefaultCar() {
        return aCar()
            .withEngine(anEngine()
                .withType(ENGINE_TYPE)
                .withSize(ENGINE_SIZE));
    }

//    public static BuilderAssert assertThat(Builder<?> actual) {
//        return new BuilderAssert(actual);
//    }
//
//    public static class BuilderAssert extends AbstractAssert<BuilderAssert, Builder<?>> {
//        public BuilderAssert(Builder<?> actual) {
//            super(actual, BuilderAssert.class);
//        }
//    }
}

