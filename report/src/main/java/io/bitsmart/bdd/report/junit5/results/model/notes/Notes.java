package io.bitsmart.bdd.report.junit5.results.model.notes;

import java.util.Objects;

/**
 * usage:
 * note().text.add("This is a note")
 * note().uml.add(...)
 */
public class Notes {
    private final TextNotes textNotes = new TextNotes();
//  UmlDiagrams - List<UmlDiagrams> or

    public TextNotes text() {
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
