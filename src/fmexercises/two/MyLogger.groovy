package fmexercises.two

import ch.qos.logback.classic.encoder.PatternLayoutEncoder
import ch.qos.logback.core.FileAppender
import groovy.util.logging.Slf4j
import org.slf4j.LoggerFactory

@Slf4j
class MyLogger {
    static {
        new FileAppender().with {
            name = 'File appender'
            file = 'groovy.log'
            context = LoggerFactory.getILoggerFactory()
            encoder = new PatternLayoutEncoder().with {
                context = LoggerFactory.getILoggerFactory()
                pattern = "%date{HH:mm:ss.SSS} [%thread] %-5level %logger{35} - %msg%n"
                start()
                it
            }
            start()
            log.addAppender(it)
        }
    }

    // debug, error, info, warn

    def debug(String message) {
        log.debug message
    }

    def error(String message) {
        log.error message
    }

    def info(String message) {
        log.info message
    }

    def warn(String message) {
        log.warn message
    }
}
