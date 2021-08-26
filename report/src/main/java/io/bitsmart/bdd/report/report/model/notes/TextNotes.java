package io.bitsmart.bdd.report.report.model.notes;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.Objects;

public class TextNotes {
    private final List<String> notes;

    @JsonCreator
    public TextNotes(@JsonProperty("notes") List<String> notes) {
        this.notes = notes;
    }

    public List<String> getNotes() {
        return notes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TextNotes)) return false;
        TextNotes textNotes = (TextNotes) o;
        return Objects.equals(notes, textNotes.notes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(notes);
    }

    @Override
    public String toString() {
        return "TextNotes{" +
            "notes=" + notes +
            '}';
    }
}
