package io.bitsmart.bdd.report.utils.carbuilder.builders;

import io.bitsmart.bdd.report.utils.Builder;
import io.bitsmart.bdd.report.utils.carbuilder.model.Wheel;

public final class WheelBuilder implements Builder<Wheel> {
    int size;

    private WheelBuilder() {
    }

    public static WheelBuilder aWheel() {
        return new WheelBuilder();
    }

    public WheelBuilder withSize(int size) {
        this.size = size;
        return this;
    }

    public Wheel build() {
        return new Wheel(size);
    }
}
