package io.bitsmart.bdd.report.utils.carbuilder.model;

import java.util.Objects;

public class Engine {
    final EngineType type;
    final int size;

    public Engine(EngineType type, int size) {
        this.type = type;
        this.size = size;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Engine)) return false;
        Engine engine = (Engine) o;
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
