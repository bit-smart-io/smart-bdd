package results.junit.results;

public class ResultsForTest {
    private String wordify;
    private Status status;

    public enum Status {
        PASSED,
        FAILED,
    }

    public ResultsForTest() {
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
