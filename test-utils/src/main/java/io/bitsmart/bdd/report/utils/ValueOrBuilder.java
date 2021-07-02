package io.bitsmart.bdd.report.utils;

import java.util.Optional;

public class ValueOrBuilder<T> {
    private final T value;
    private final Builder<T> builder;

    public ValueOrBuilder(T value) {
        this.value = value;
        this.builder = null;
    }

    public ValueOrBuilder(Builder<T> builder) {
        this.value = null;
        this.builder = builder;
    }

    public T get() {
        return Optional.ofNullable(value).orElseGet(this::build);
    }

    private T build() {
        return Optional.ofNullable(builder).map(Builder::build).orElse(null);
    }
}
