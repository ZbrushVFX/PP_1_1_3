package jm.task.core.jdbc.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoggerUtil {
    private LoggerUtil() {
        throw new UnsupportedOperationException("Utility class should not be instantiated");
    }
    public static Logger getLogger(Class<?> clazz) {
        return LoggerFactory.getLogger(clazz);
    }
}
