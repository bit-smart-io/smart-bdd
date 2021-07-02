package io.bitsmart.bdd.report.utils.carbuilder.valuebuilders;

import io.bitsmart.bdd.report.utils.Builder;
import io.bitsmart.bdd.report.utils.carbuilder.model.Engine;
import io.bitsmart.bdd.report.utils.carbuilder.model.EngineType;

public final class EngineBuilder implements Builder<Engine> {
    EngineType type;
    int size;

    private EngineBuilder() {
    }

    public static EngineBuilder anEngine() {
        return new EngineBuilder();
    }

    public EngineBuilder withType(EngineType type) {
        this.type = type;
        return this;
    }

    public EngineBuilder withSize(int size) {
        this.size = size;
        return this;
    }

    public Engine build() {
        return new Engine(type, size);
    }
}
