package by.mashnyuk.informationHandling.exception;

public class TextAnalysisException extends Exception {
    public enum ErrorType {
        IO_ERROR,
        VALIDATION_ERROR,
        PROCESSING_ERROR
    }

    private final ErrorType errorType;

    public TextAnalysisException(String message, ErrorType errorType) {
        super(message);
        this.errorType = errorType;
    }

    public TextAnalysisException(String message, ErrorType errorType, Throwable cause) {
        super(message, cause);
        this.errorType = errorType;
    }

    public ErrorType getErrorType() {
        return errorType;
    }
}
