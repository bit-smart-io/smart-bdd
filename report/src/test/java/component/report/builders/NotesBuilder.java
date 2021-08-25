package component.report.builders;

import io.bitsmart.bdd.report.junit5.results.model.notes.Notes;
import io.bitsmart.bdd.report.utils.Builder;

/**
 * TODO should we create builders for things that don't have logic in the build()?
 *
 * Usage:
 * notes().addTextNote("This is a new note").build();
 * Vs
 * Notes notes = new Notes();
 * notes.text().add("This is a new note");
 */
public final class NotesBuilder implements Builder<Notes> {
    private final Notes notes = new Notes();

    private NotesBuilder() {
    }

    public static NotesBuilder notes() {
        return new NotesBuilder();
    }

    public NotesBuilder addTextNote(String note) {
        notes.text().add(note);
        return this;
    }

    public Notes build() {
        return notes;
    }
}
