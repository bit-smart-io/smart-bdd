package results.junit.results;

import org.junit.jupiter.api.extension.ExtensionContext;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 * This concept could be generified.
 * We could have:
 * - Collector
 *   - ResultsForClass for timing, status, wordify etc...
 *   - ResultsForTest for timing, status, wordify etc...
 *
 *   TimedResult implements Result
 *   - start
 *   - end
 *   - total
 *   This could be applied to:
 *   - ResultsForClass how long did the whole class take
 *   - ResultsForTest how long did the test take
 *
 *   TODO same test with different args?
 *
 */
public class ResultsForClasses {
    // ClassName extends String
    // ClassName { String name }
    // ClassName.from(name)
    // className(name)
    private ConcurrentHashMap<String, ResultsForClass> resultsForClasses = new ConcurrentHashMap();

    public List<String> getClasses() {
        return Collections.list(resultsForClasses.keys());
    }

    public ConcurrentHashMap<String, ResultsForClass> getResultsForClasses() {
        return resultsForClasses;
    }

    public ResultsForClass getTestResultsForClass(ExtensionContext extensionContext) {
        return resultsForClasses.get(getClassName(extensionContext));
    }

    public ResultsForClass newResultsForClass(ExtensionContext context) {
        ResultsForClass resultsForClass = new ResultsForClass();
        resultsForClasses.put(getClassName(context), resultsForClass);
        return resultsForClass;
    }

    public String getClassName(ExtensionContext extensionContext) {
        return extensionContext.getRequiredTestClass().getSimpleName();
    }
}
