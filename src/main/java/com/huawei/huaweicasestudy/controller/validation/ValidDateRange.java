package com.huawei.huaweicasestudy.controller.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

/**
 * To Ensure that the start date is not after the end date
 */
@Documented
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = DateRangeValidator.class)
public @interface ValidDateRange {
    String message() default "Start date must be before end date.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}