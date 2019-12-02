package util;

import java.text.MessageFormat;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SystemLogger {

    private static final Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    public static void info(String msg, Object... args) {
        logger.info(MessageFormat.format(msg, args));
    }

    public static void print(String msg, Object... args) {
        System.out.println(MessageFormat.format(msg, args));
    }

    public static void log(Level level, String msg) {
        logger.log(level, msg);
    }
}
