package by.mashnyuk.InformationHandling.validator;

public class TextValidator {
    private static final String FILE_NAME_PATTERN = "^[a-zA-Z0-9._-]+\\.txt$";
    private static final int MAX_FILE_SIZE_KB = 1024; // 1MB

    public boolean isValidText(String text) {
        return text != null && !text.trim().isEmpty();
    }

    public boolean isValidFileName(String fileName) {
        return fileName != null && fileName.matches(FILE_NAME_PATTERN);
    }

    public boolean isValidWordCountThreshold(int threshold) {
        return threshold > 0 && threshold <= 100; // Максимум 100 слов
    }

    public boolean isValidFileSize(long fileSizeBytes) {
        return fileSizeBytes > 0 && fileSizeBytes <= MAX_FILE_SIZE_KB * 1024;
    }
}
