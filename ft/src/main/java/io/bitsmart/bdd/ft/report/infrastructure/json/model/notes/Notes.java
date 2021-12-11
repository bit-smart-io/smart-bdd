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

package io.bitsmart.bdd.ft.report.infrastructure.json.model.notes;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.bitsmart.bdd.report.junit5.results.model.notes.TextNotes;

import java.util.Objects;

public class Notes {
    private final TextNotes textNotes;

    @JsonCreator
    public Notes(@JsonProperty("textNotes") TextNotes textNotes) {
        this.textNotes = textNotes;
    }

    public TextNotes getTextNotes() {
        return textNotes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Notes)) return false;
        Notes notes = (Notes) o;
        return Objects.equals(textNotes, notes.textNotes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(textNotes);
    }

    @Override
    public String toString() {
        return "Notes{" +
            "textNotes=" + textNotes +
            '}';
    }
}
