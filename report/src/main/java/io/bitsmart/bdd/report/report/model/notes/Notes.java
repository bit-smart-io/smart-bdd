package io.bitsmart.bdd.report.report.model.notes;

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
