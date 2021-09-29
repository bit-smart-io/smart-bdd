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

package component.results.annotations;

import io.bitsmart.bdd.report.junit5.annotations.InjectNotes;
import io.bitsmart.bdd.report.junit5.results.extension.ReportExtension;
import io.bitsmart.bdd.report.junit5.results.extension.TestCaseNotesParameterResolver;
import io.bitsmart.bdd.report.junit5.results.model.notes.Notes;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.assertj.core.api.Assertions.assertThat;

@TestMethodOrder(OrderAnnotation.class)
@ExtendWith({ReportExtension.class, TestCaseNotesParameterResolver.class})
public class NotesUnderTest {

    private Notes notes = new Notes();

    @BeforeEach
    public void setUp(@InjectNotes Notes notes) {
        this.notes = notes;
    }

    @Order(0)
    @Test
    void testMethod() {
        passingAssertion();
    }

    private void passingAssertion() {
        assertThat(true).isTrue();
        notes.text().add("This is a new note");
    }
}