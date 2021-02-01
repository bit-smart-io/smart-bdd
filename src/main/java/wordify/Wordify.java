package wordify;

public class Wordify {
    private static final char REPLACE_CHAR = ' ';
    private static final char SPACE = ' ';
    private static final char COMMA = ',';
    private static final char SEMI_COLON = ';';

    private static final char END = '$';

    private int index = 0;
    private final StringBuilder result = new StringBuilder();
    private final char[] input;
    private final String original;

    public Wordify(String original) {
        this.original = original;
        this.input = original.toCharArray();
    }

    private void seekToNextCharToWrite() {
        if (current() == SPACE || currentIsCharToBeReplaced()) {
            incrementIndex();
            seekToNextCharToWrite();
        }
    }

    private void incrementIndex() {
        index++;
    }

    private boolean hasNext() {
        return index < input.length - 1;
    }

    private boolean inBounds() {
        return index < input.length;
    }

    private char current() {
        if (inBounds()) {
            return input[index];
        } else {
            return END;
        }
    }

    private void appendResult(char ch) {
        if (ch == SPACE && index >= 1 && result.charAt(result.length() - 1) == SPACE) {
            incrementIndex();
        } else {
            result.append(ch);
            incrementIndex();
        }
    }

    private void appendNumber() {
        if (isNumber()) {
            appendResult(current());
            appendNumber();
        }
    }

    private boolean isCapitol() {
        return Character.isUpperCase(current());
    }

    private boolean isNumber() {
        return (current() == '-' || current() == '.' || Character.isDigit(current()));
    }

    private boolean currentIsCharToBeReplaced() {
        return current() == COMMA || current() == '(' || current() == ')' || current() == '.';
    }

    public String wordify() {
        seekToNextCharToWrite();
        appendResult(Character.toTitleCase(current()));

        while (inBounds()) {
            if (currentIsCharToBeReplaced()) {
                if (hasNext()) {
                    appendResult(SPACE);
                    seekToNextCharToWrite();
                } else {
                    // we are at the end. seekToNextNonWhiteSpace() should increment to end??
                    incrementIndex();
                }
            } else if (isCapitol()) {
                //appendResult(SPACE); - amend without inc
                result.append(SPACE);
                appendResult(Character.toLowerCase(current()));
            } else if (isNumber()) {
                appendNumber();
            }
            else if (current() == SEMI_COLON) {
                incrementIndex();
            } else {
                appendResult(current());
            }
        }
        return result.toString().trim();
    }
}