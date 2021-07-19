package io.bitsmart.bdd.report.utils.carbuilder.model;

import java.util.Objects;

public class Wheel {
    final int size;

    public Wheel(int size) {
        this.size = size;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Wheel)) return false;
        Wheel wheel = (Wheel) o;
        return size == wheel.size;
    }

    @Override
    public int hashCode() {
        return Objects.hash(size);
    }

    @Override
    public String toString() {
        return "Wheel{" +
            "size=" + size +
            '}';
    }
}
