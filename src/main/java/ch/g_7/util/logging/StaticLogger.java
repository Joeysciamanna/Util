package ch.g_7.util.logging;

import java.util.ArrayList;

public class StaticLogger {

    private final static Logger LOGGER = Logger.getInstance();

    public static void fatal(String message) {
        LOGGER.fatal(message);
    }

    public static void fatal(Throwable throwable) {
        LOGGER.fatal(throwable);
    }

    public static void fatal(String message, Throwable throwable) {
        LOGGER.fatal(message, throwable);
    }

    public static void error(String message) {
        LOGGER.error(message);
    }

    public static void error(Throwable throwable) {
        LOGGER.error(throwable);
    }

    public static void error(String message, Throwable throwable) {
        LOGGER.error(message, throwable);
    }

    public static void warning(String message) {
        LOGGER.warning(message);
    }

    public static void warning(Throwable throwable) {
        LOGGER.warning(throwable);
    }

    public static void warning(String message, Throwable throwable) {
        LOGGER.warning(message, throwable);
    }

    public static void info(String message) {
        LOGGER.info(message);
    }

    public static void info(Throwable throwable) {
        LOGGER.info(throwable);
    }

    public static void info(String message, Throwable throwable) {
        LOGGER.info(message, throwable);
    }

    public static void debug(String message) {
        LOGGER.debug(message);
    }

    public static void debug(Throwable throwable) {
        LOGGER.debug(throwable);
    }

    public static void debug(String message, Throwable throwable) {
        LOGGER.debug(message, throwable);
    }

    public static void wtf(String message) {
        LOGGER.wtf(message);
    }

    public static void wtf(Throwable throwable) {
        LOGGER.wtf(throwable);
    }

    public static void wtf(String message, Throwable throwable) {
        LOGGER.wtf(message, throwable);
    }

    public static void log(LogLevel level, String message) {
        LOGGER.log(level, message);
    }

    public static void log(LogLevel level, Throwable throwable) {
        LOGGER.log(level, throwable);
    }

    public static void log(LogLevel level, String message, Throwable throwable) {
        LOGGER.log(level, message, throwable);
    }

    public static void log(LogMessage message) {
        LOGGER.log(message);
    }
}
