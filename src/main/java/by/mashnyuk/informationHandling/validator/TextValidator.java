package by.mashnyuk.informationHandling.validator;

import by.mashnyuk.informationHandling.entity.impl.Text;

public interface TextValidator {
    boolean isValidText(Text text);

    boolean isValidFileName(String fileName);

    boolean isValidWordCountThreshold(int threshold);

    boolean isValidFileSize(long fileSizeBytes);
}
