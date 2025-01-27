package com.huawei.huaweicasestudy.controller.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


/**
 * To Ensure that the sum of the 'percentage' values in the 'plannings' list of a
 * ProjectGoal  equals 100.
 */
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = PercentageSumValidator.class)
public @interface ValidPercentageSum {
    String message() default "The sum of percentages must be 100";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}