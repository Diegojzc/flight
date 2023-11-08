package com.tokioschool.flightapp.core.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

public class EnumValidImpl implements ConstraintValidator<EnumValid,String> {

    private List<String> entries;
    private boolean required;
    @Override
    public void initialize(EnumValid constraintAnnotation) {
        Enum<?>[] enumConstants = constraintAnnotation.target().getEnumConstants();
        entries= Arrays.stream(enumConstants).map(Enum::toString).toList();
        required= constraintAnnotation.required();
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        String trimmed= StringUtils.trimToNull(s);
        return !required && trimmed == null || trimmed != null && entries.contains(trimmed);
    }
}
