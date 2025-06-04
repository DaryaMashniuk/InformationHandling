package by.mashnyuk.informationHandling.validator.impl;

import by.mashnyuk.informationHandling.entity.impl.Text;
import by.mashnyuk.informationHandling.validator.TextValidator;

public class TextValidatorImpl implements TextValidator {
    private static final String FILE_NAME_PATTERN = "^[a-zA-Z0-9._-]+\\.txt$";
    private static final int MAX_FILE_SIZE_KB = 1024;

    @Override
    public boolean isValidText(Text text) {
        return text != null && !text.toString().trim().isEmpty();
    }

    @Override
    public boolean isValidFileName(String fileName) {
        return fileName != null && fileName.matches(FILE_NAME_PATTERN);
    }

    @Override
    public boolean isValidWordCountThreshold(int threshold) {
        return threshold > 0 && threshold <= 100;
    }

    @Override
    public boolean isValidFileSize(long fileSizeBytes) {
        return fileSizeBytes > 0 && fileSizeBytes <= MAX_FILE_SIZE_KB * 1024;
    }
}
