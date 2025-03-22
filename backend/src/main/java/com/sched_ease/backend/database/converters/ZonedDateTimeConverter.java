package com.sched_ease.backend.database.converters;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

@Converter
public class ZonedDateTimeConverter implements AttributeConverter<ZonedDateTime, String> {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ISO_ZONED_DATE_TIME;

    @Override
    public String convertToDatabaseColumn(ZonedDateTime zonedDateTime) {
        return (zonedDateTime == null) ? null : FORMATTER.format(zonedDateTime);
    }

    @Override
    public ZonedDateTime convertToEntityAttribute(String dbData) {
        if (dbData == null || dbData.trim().isEmpty()) {
            return null;
        }

        try {
            return ZonedDateTime.parse(dbData, FORMATTER);
        } catch (DateTimeParseException e) {
            // Log the error
            System.err.println("Error parsing date: " + dbData + " - " + e.getMessage());
            return null;
        }
    }
}