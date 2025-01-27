package com.huawei.huaweicasestudy.controller.validation;

import com.huawei.huaweicasestudy.payload.request.log.LogSearchRequest;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class DateRangeValidator implements ConstraintValidator<ValidDateRange, LogSearchRequest> {

    @Override
    public boolean isValid(LogSearchRequest value, ConstraintValidatorContext context) {
        if (value.start() != null && value.end() != null) {
            return !value.start().isAfter(value.end());
        }
        return true;
    }
}
