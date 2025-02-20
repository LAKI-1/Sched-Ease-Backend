package com.sched_ease.backend.database.converters;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

@Converter(autoApply = true)
public class ZonedDateTimeConverter implements AttributeConverter<ZonedDateTime, String> {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ISO_ZONED_DATE_TIME;

    @Override
    public String convertToDatabaseColumn(ZonedDateTime zonedDateTime) {
        return (zonedDateTime == null) ? null : FORMATTER.format(zonedDateTime);
    }

    @Override
    public ZonedDateTime convertToEntityAttribute(String dbData) {
        return (dbData == null) ? null : ZonedDateTime.parse(dbData, FORMATTER);
    }
}

