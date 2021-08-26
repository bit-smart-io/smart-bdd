package io.bitsmart.bdd.report.junit5.results.model.notes;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class TextNotes {
    private final List<String> notes = new ArrayList<>();

    public List<String> getNotes() {
        return notes;
    }

    public void add(String note) {
        notes.add(note);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TextNotes)) return false;
        TextNotes textNotes1 = (TextNotes) o;
        return Objects.equals(notes, textNotes1.notes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(notes);
    }

    @Override
    public String toString() {
        return "TextNotes{" +
            "textNotes=" + notes +
            '}';
    }
}
