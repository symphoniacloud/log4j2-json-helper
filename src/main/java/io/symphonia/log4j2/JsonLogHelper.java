package io.symphonia.log4j2;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.message.ObjectMessage;

import java.util.HashMap;

public class JsonLogHelper {
    private Logger logger;

    public JsonLogHelper() {
        this(LogManager.getLogger());
    }

    public JsonLogHelper(Class clazz) {
        this(LogManager.getLogger(clazz));
    }

    public JsonLogHelper(Logger logger) {
        this.logger = logger;
    }

    public void debug(Object... args) {
        log(Level.DEBUG, args);
    }

    public void error(Object... args) {
        log(Level.ERROR, args);
    }

    public void fatal(Object... args) {
        log(Level.FATAL, args);
    }

    public void info(Object... args) {
        log(Level.INFO, args);
    }

    public void trace(Object... args) {
        log(Level.TRACE, args);
    }

    public void warn(Object... args) {
        log(Level.WARN, args);
    }

    public void log(Level level, Object... args) {
        if ((args.length & 1) != 0) {
            throw new IllegalArgumentException("Odd number of message arguments.");
        }
        if (logger.isEnabled(level)) {
            HashMap<Object, Object> m = new HashMap<>(args.length / 2, 1);
            for (int i = 0; i < args.length; i += 2) {
                m.put(args[i], args[i + 1]);
            }
            logger.log(level, new ObjectMessage(m));
        }
    }

}
