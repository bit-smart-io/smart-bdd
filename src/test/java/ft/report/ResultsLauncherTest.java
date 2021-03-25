package ft.report;

import junit5.results.ReportFactory;
import junit5.utils.TestLauncher;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import report.ResultsExtension;
import report.model.Result;
import report.model.Results;
import report.model.Status;

import static org.assertj.core.api.Assertions.assertThat;
import static report.model.ResultBuilder.aResult;

public class ResultsLauncherTest {
    private static final Class<?> CLASS_UNDER_TEST = ClassUnderTest.class;

    @BeforeEach
    void setUp() {
        ResultsExtension.reset();
    }

    @Test
    void componentTest() {
        TestLauncher.launch(CLASS_UNDER_TEST);

        // WIP - below is not in order
        Results results = ReportFactory.create(ResultsExtension.getTestResultsForClasses());
        assertThat(results).isNotNull();
        assertThat(results.getResults()).hasSize(5);

        Result result = aResult()
            .withWordify("Assert that \"first test\" is equal to \"first test\"")
            .withStatus(Status.PASSED)
            .withMethodName("method name")
            .withClassName("class name")
            .withPackageName("package name")
            .build();

        assertThat(results.getResults()).contains(result);
    }

    //TODO may result finder findMethod() find(class, method etc...)
}
