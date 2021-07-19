package io.bitsmart.bdd.report.utils.simplecarbuilder.model;

import java.util.Objects;

public class SimpleEngine {
    final EngineType type;
    final int size;

    public SimpleEngine(EngineType type, int size) {
        this.type = type;
        this.size = size;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SimpleEngine)) return false;
        SimpleEngine engine = (SimpleEngine) o;
        return size == engine.size && type == engine.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, size);
    }

    @Override
    public String toString() {
        return "Engine{" +
            "type=" + type +
            ", size=" + size +
            '}';
    }
}
