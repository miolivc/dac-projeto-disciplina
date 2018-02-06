package com.ifpb.dac.conversores;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

/**
 *
 * @author rodrigobento
 */
@Converter(autoApply = true)
public class ConversorLocalDateTime implements AttributeConverter<LocalDateTime, Timestamp>{

    @Override
    public Timestamp convertToDatabaseColumn(LocalDateTime attribute) {
        if(attribute == null){
            return null;
        }
        return Timestamp.valueOf(attribute);
    }

    @Override
    public LocalDateTime convertToEntityAttribute(Timestamp dbData) {
        if(dbData == null){
            return null;
        }
        return dbData.toLocalDateTime();
    }
    
}
