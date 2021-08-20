package com.example.cucumbers;

import com.example.cucumbers.model.Cucumber;
import com.example.cucumbers.builders.CucumberBuilder;
import io.bitsmart.bdd.report.junit5.results.extension.ReportExtension;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Tutorial and example usage of without builders. You don't want to do this, but it's possible.
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@ExtendWith(ReportExtension.class)
public class EatCucumbersWithoutBuildersTest {
    private CucumberService cucumberService;

    @BeforeEach
    private void reset() {
        cucumberService = new CucumberService();
    }

    @Order(0)
    @Test
    void eat5OutOf12RedCucumbers() {
        givenThereAreCucumbers(12, "red");
        whenIEatCucumbers(5, "red");
        thenIShouldHaveCucumbers(7);
    }

    @Order(1)
    @Test
    void eat5OutOf12BlueCucumbers() {
        givenThereAreCucumbers(12, "blue");
        whenIEatCucumbers(5, "blue");
        thenIShouldHaveCucumbers(7);
    }

    @Order(2)
    @ParameterizedTest(name = "#{index} - Eat {1} out of {0}")
    @CsvSource({"12,5,7", "20,5,15"})
    void eat(int start, int eat, int left) {
        givenThereAreCucumbers(start, "red");
        whenIEatCucumbers(eat, "red");
        thenIShouldHaveCucumbers(left);
    }

    private void givenThereAreCucumbers(int amount, String colour) {
        final List<Cucumber> cucumbers = IntStream.range(0, amount)
            .mapToObj(i -> CucumberBuilder.aCucumber().withColour(colour).build())
            .collect(Collectors.toList());
        cucumberService.setCucumbers(cucumbers);
    }

    private void whenIEatCucumbers(int amount, String colour) {
        cucumberService.eat(amount, colour);
    }

    private void  thenIShouldHaveCucumbers(int amount) {
        assertThat(cucumberService.getCucumbers().size()).isEqualTo(amount);
    }
}
