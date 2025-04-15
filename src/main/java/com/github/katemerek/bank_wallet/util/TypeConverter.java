package com.github.katemerek.bank_wallet.util;

import com.github.katemerek.bank_wallet.enumiration.TypeOfOperation;
import jakarta.persistence.AttributeConverter;

@jakarta.persistence.Converter(autoApply = true)
public class TypeConverter implements AttributeConverter<TypeOfOperation, String> {

    @Override
    public String convertToDatabaseColumn(TypeOfOperation typeOfOperation) {
        if (typeOfOperation == null) {
            return null;
        }
        return typeOfOperation.name();
    }

    @Override
    public TypeOfOperation convertToEntityAttribute(String s) {
        if (s == null) {
            return null;
        }
        return TypeOfOperation.valueOf(s);
    }
}
