package results.domain;

public class Result {
//    TODO
//    private String className;
//    private String packageName;
    private final String wordify;
    private final Status status;

    public Result(String wordify, Status status) {
        this.wordify = wordify;
        this.status = status;
    }

    public String getWordify() {
        return wordify;
    }

    public Status getStatus() {
        return status;
    }
}
