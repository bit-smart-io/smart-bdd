package com.example.cucumbers.builders;

import com.example.cucumbers.model.Cucumber;
import io.bitsmart.bdd.report.utils.Builder;
import io.bitsmart.bdd.report.utils.BuilderUtils;

import java.util.ArrayList;
import java.util.List;

import static java.util.Arrays.asList;

public final class CucumbersBuilder implements Builder<List<Cucumber>> {
    private final List<CucumberBuilder> cucumbers = new ArrayList<>();

    private CucumbersBuilder() {
    }

    public static CucumbersBuilder cucumbers() {
        return new CucumbersBuilder();
    }

    public CucumbersBuilder with(CucumberBuilder... cucumbers) {
        this.cucumbers.clear();
        this.cucumbers.addAll(asList(cucumbers));
        return this;
    }

    public CucumbersBuilder with(List<CucumberBuilder> cucumbers) {
        this.cucumbers.clear();
        this.cucumbers.addAll(cucumbers);
        return this;
    }

    public CucumbersBuilder with(CucumberBuilder cucumber) {
        this.cucumbers.add(cucumber);
        return this;
    }

    public List<Cucumber> build() {
        return BuilderUtils.build(cucumbers);
    }
}
