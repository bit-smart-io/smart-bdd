package io.bitsmart.bdd.report.utils.simplecarbuilder.valuebuilders;

import io.bitsmart.bdd.report.utils.Builder;
import io.bitsmart.bdd.report.utils.simplecarbuilder.model.EngineType;
import io.bitsmart.bdd.report.utils.simplecarbuilder.model.SimpleEngine;

public final class SimpleEngineBuilder implements Builder<SimpleEngine> {
    EngineType type;
    int size;

    private SimpleEngineBuilder() {
    }

    public static SimpleEngineBuilder anEngine() {
        return new SimpleEngineBuilder();
    }

    public SimpleEngineBuilder withType(EngineType type) {
        this.type = type;
        return this;
    }

    public SimpleEngineBuilder withSize(int size) {
        this.size = size;
        return this;
    }

    public SimpleEngine build() {
        return new SimpleEngine(type, size);
    }
}
