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

package io.bitsmart.bdd.report.junit5.results.model.notes;

import io.bitsmart.bdd.report.mermaid.SequenceDiagram;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * usage:
 * note().text.add("This is a note")
 * note().uml.add(...)
 */
public class Notes {
    private final TextNotes textNotes = new TextNotes();
    private final List<SequenceDiagram> diagrams = new ArrayList<>();

    //TODO this needs to be the usual getter/setter and another object is the feature file view layer??
    public TextNotes text() {
        return textNotes;
    }

    public List<SequenceDiagram> diagram() {
        return diagrams;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Notes)) return false;
        Notes notes = (Notes) o;
        return Objects.equals(textNotes, notes.textNotes) && Objects.equals(diagrams, notes.diagrams);
    }

    @Override
    public int hashCode() {
        return Objects.hash(textNotes, diagrams);
    }

    @Override
    public String toString() {
        return "Notes{" +
            "textNotes=" + textNotes +
            ", diagrams=" + diagrams +
            '}';
    }
}
