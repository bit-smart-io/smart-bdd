package io.bitsmart.bdd.report.utils.simplecarbuilder.builders;

import io.bitsmart.bdd.report.utils.Builder;
import io.bitsmart.bdd.report.utils.simplecarbuilder.model.SimpleCar;

public final class SimpleCarBuilder implements Builder<SimpleCar> {
    SimpleEngineBuilder engine;

    private SimpleCarBuilder() {
    }

    public static SimpleCarBuilder aCar() {
        return new SimpleCarBuilder();
    }

    public SimpleCarBuilder withEngine(SimpleEngineBuilder engine) {
        this.engine = engine;
        return this;
    }

    public SimpleEngineBuilder updateEngine() {
        return engine;
    }

    public SimpleCar build() {
        return new SimpleCar(engine.build());
    }
}
