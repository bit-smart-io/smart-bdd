package io.bitsmart.wordify;

public class WordifyString {
    private static final char NEW_LINE = '\n';
    private static final char SPACE = ' ';
    private static final char TAB = '\t';
    private static final char COMMA = ',';
    private static final char SEMI_COLON = ';';
    private static final char HYPHEN = '-';
    private static final char FULL_STOP = '.';
    private static final char LEFT_PARENTHESIS = '(';
    private static final char RIGHT_PARENTHESIS = ')';

    private static final char END = '$';

    private int index = 0;
    private final StringBuilder result = new StringBuilder();
    private final char[] input;

    public WordifyString(String original) {
        this.input = original.trim().toCharArray();
    }

    private void seekPastNewLineToNextCharToWrite() {
        if (current() == NEW_LINE) {
            incrementIndex();
            seekPastNewLineToNextCharToWrite();
        }
    }

    private void seekToNextCharToWrite() {
        if (current() == SPACE || current() == TAB || currentIsCharToBeReplaced()) {
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
        return (current() == HYPHEN || current() == FULL_STOP || Character.isDigit(current()));
    }

    private boolean currentIsCharToBeReplaced() {
        return current() == COMMA || current() == LEFT_PARENTHESIS || current() == RIGHT_PARENTHESIS || current() == FULL_STOP;
    }

    public String wordify() {
        seekPastNewLineToNextCharToWrite();
        seekToNextCharToWrite();
        appendResult(Character.toTitleCase(current()));

        while (inBounds()) {
            if (current() == NEW_LINE) {
                seekPastNewLineToNextCharToWrite();
                seekToNextCharToWrite();
                result.append(NEW_LINE);
                appendResult(Character.toTitleCase(current()));
            } else if (currentIsCharToBeReplaced()) {
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