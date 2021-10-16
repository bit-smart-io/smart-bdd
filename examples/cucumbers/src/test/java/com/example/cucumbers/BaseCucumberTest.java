/*
 * Smart BDD - The smart way to do behavior-driven development.
 * Copyright (C)  2021  James Bayliss
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */

package com.example.cucumbers;

import com.example.cucumbers.builders.CucumberBuilder;
import com.example.cucumbers.builders.CucumberGivenBuilder;
import com.example.cucumbers.builders.UserGivenBuilder;
import com.example.cucumbers.model.Cucumber;
import com.example.cucumbers.model.CucumberGiven;
import com.example.cucumbers.model.CucumberThen;
import com.example.cucumbers.model.CucumberWhen;
import com.example.cucumbers.model.UserGiven;
import io.bitsmart.bdd.report.mermaid.Message;
import io.bitsmart.bdd.report.mermaid.SequenceDiagram;
import io.bitsmart.bdd.report.utils.ThenBuilder;
import io.bitsmart.bdd.report.utils.WhenBuilder;
import org.junit.jupiter.api.BeforeEach;

import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

public class BaseCucumberTest extends BaseTest {

    private final CucumberService cucumberService = new CucumberService();

    @BeforeEach
    void setUp() {
        // notes() -> doc()?
        // doc().request(cucumberService).method(setHungry).value(false)
        // doc().setup().request()
        // doc().setup().updatePersistence()
        // doc().setState()

        // if you did want to document set state would you ever want:
        //   1. a diagram, notes and or capture request/response header/bodies/messages etc...
        //   2. If you did want a diagram, would you want a separate one for setup? Or just different arrows?

        context.test().notes().diagram().add(new SequenceDiagram());
        context.test().notes().diagram().get(0).addActor("User");
        context.test().notes().diagram().get(0).addParticipant("CucumberService");
    }

    @Override
    public void doc() {}

    public void given(CucumberBuilder... cucumbers) {
        given(CucumberGivenBuilder.iHave(cucumbers));
    }

    public void given(UserGivenBuilder builder) {
        UserGiven given = builder.build();
        if (!given.isHungry()) {
            notes().text().add("Not hungry, so will not eat");
        }
        // below is both given and kinda is when:
        //   should setting state be on a sequence diagram? should it have different arrows?
        sequenceDiagram().addMessage(new Message("User", "CucumberService", "setHungry false"));
        cucumberService.setHungry(given.isHungry());
    }

    public void given(CucumberGivenBuilder builder) {
        CucumberGiven cucumberGiven = builder.build();
        cucumberService.setCucumbers(cucumberGiven.getCucumbers());
    }

    public void when(WhenBuilder<CucumberWhen> builder) {
        CucumberWhen cucumberWhen = builder.build();
        cucumberService.eat(cucumberWhen.getQuantity(), cucumberWhen.getColour());
        sequenceDiagram().addMessage(new Message("User", "CucumberService", "eat " + cucumberWhen.getQuantity() + " " + cucumberWhen.getColour()));
    }

    public void then(ThenBuilder<CucumberThen> builder) {
        CucumberThen cucumberThen = builder.build();
        final List<Cucumber> actualCucumbers = cucumberService.getCucumbers();
        sequenceDiagram().addMessage(new Message("CucumberService", "User", "getCucumbers: " + actualCucumbers));

        cucumberThen.getColour().ifPresent(colour -> actualCucumbers
            .forEach((cucumber -> assertThat(cucumber.getColour()).isEqualTo(colour))));
        cucumberThen.getQuantity().ifPresent(quantity -> assertThat(actualCucumbers.size()).isEqualTo(quantity));

        cucumberThen.getCucumbers().ifPresent(
            cucumbers -> assertThat(actualCucumbers).containsAll(
                cucumbers.stream().map(CucumberBuilder::build).collect(Collectors.toList())));
    }
}
