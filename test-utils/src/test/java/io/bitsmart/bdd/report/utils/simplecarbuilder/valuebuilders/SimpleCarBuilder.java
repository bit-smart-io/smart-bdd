package io.bitsmart.bdd.report.utils.simplecarbuilder.valuebuilders;

import io.bitsmart.bdd.report.utils.Builder;
import io.bitsmart.bdd.report.utils.ValueOrBuilder;
import io.bitsmart.bdd.report.utils.simplecarbuilder.model.SimpleCar;
import io.bitsmart.bdd.report.utils.simplecarbuilder.model.SimpleEngine;

public final class SimpleCarBuilder implements Builder<SimpleCar> {
    ValueOrBuilder<SimpleEngine> engine;

    private SimpleCarBuilder() {
    }

    public static SimpleCarBuilder aCar() {
        return new SimpleCarBuilder();
    }

    public SimpleCarBuilder withEngine(SimpleEngine engine) {
        this.engine = new ValueOrBuilder<>(engine);
        return this;
    }

    public SimpleCarBuilder withEngine(SimpleEngineBuilder engine) {
        this.engine = new ValueOrBuilder<>(engine);
        return this;
    }

    public SimpleCar build() {
        return new SimpleCar(engine.get());
    }
}
