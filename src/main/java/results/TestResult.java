package results;

import java.util.ArrayList;
import java.util.List;

public class TestResult {

    private List<String> junitLifeCycleMethods = new ArrayList<>();
    private String wordify;
    private Status status;

    public enum Status {
        PASSED,
        FAILED,
    }

    public void appendJunitLifeCycleMethod(String method) {
        junitLifeCycleMethods.add(method);
    }

    public List<String> junitLifeCycleMethods() {
        return junitLifeCycleMethods;
    }
}
