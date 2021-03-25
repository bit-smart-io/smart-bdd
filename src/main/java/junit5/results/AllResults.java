package junit5.results;

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
 *   Should we use domain objects?
 *   ClassName extends String
 *   ClassName { String name }
 *   ClassName.from(name)
 *   className(name)
 */
public class AllResults {
    private final ConcurrentHashMap<String, ClassResults> classNameToClassResults = new ConcurrentHashMap<>();

    public List<String> getClasses() {
        return Collections.list(classNameToClassResults.keys());
    }

    public ConcurrentHashMap<String, ClassResults> getClassNameToClassResults() {
        return classNameToClassResults;
    }

    public ClassResults getTestResultsForClass(ExtensionContext extensionContext) {
        return classNameToClassResults.get(getClassName(extensionContext));
    }

    public ClassResults newResultsForClass(ExtensionContext context) {
        ClassResults classResults = new ClassResults();
        classNameToClassResults.put(getClassName(context), classResults);
        return classResults;
    }

    public String getClassName(ExtensionContext extensionContext) {
        return extensionContext.getRequiredTestClass().getSimpleName();
    }

    public void reset() {
        classNameToClassResults.clear();
    }
}
