package org.ricone.logging;

import ch.qos.logback.classic.pattern.ClassicConverter;
import ch.qos.logback.classic.spi.ILoggingEvent;

import java.util.UUID;

public class UUIDConverter extends ClassicConverter {
    @Override
    public String convert(final ILoggingEvent event) {
        // for every logging event return processId from mx bean or better alternative)
        return UUID.randomUUID().toString();
    }
}