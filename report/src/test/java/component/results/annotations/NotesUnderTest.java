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