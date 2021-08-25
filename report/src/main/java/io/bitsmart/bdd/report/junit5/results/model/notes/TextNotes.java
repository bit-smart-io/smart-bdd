package io.bitsmart.bdd.report.junit5.results.model.notes;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class TextNotes {
    private final List<String> textNotes = new ArrayList<>();

    public List<String> getTextNotes() {
        return textNotes;
    }

    public void add(String note) {
        textNotes.add(note);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TextNotes)) return false;
        TextNotes textNotes1 = (TextNotes) o;
        return Objects.equals(textNotes, textNotes1.textNotes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(textNotes);
    }

    @Override
    public String toString() {
        return "TextNotes{" +
            "textNotes=" + textNotes +
            '}';
    }
}
