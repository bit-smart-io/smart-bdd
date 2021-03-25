package junit5.results;

public class TestResult {
    private String wordify;
    private Status status;

    public enum Status {
        PASSED,
        FAILED,
    }

    public TestResult() {
    }

    public String getWordify() {
        return wordify;
    }

    public Status getStatus() {
        return status;
    }

    public void setWordify(String wordify) {
        this.wordify = wordify;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
