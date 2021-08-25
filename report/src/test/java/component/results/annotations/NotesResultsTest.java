package component.results.annotations;

import component.results.AbstractResultsForTestSuite;
import io.bitsmart.bdd.report.junit5.results.model.notes.Notes;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class NotesResultsTest extends AbstractResultsForTestSuite {

    @Override
    public Class<?> classUnderTest() {
        return NotesUnderTest.class;
    }

    @Test
    void verifyResultsForPassingTestCases() {
        Notes notes = new Notes();
        notes.text().add("This is a new note");

        assertThat(testCaseResult("testMethod").getNotes()).isEqualTo(notes);
    }
}
