package com.huawei.huaweicasestudy.controller.validation;

import com.huawei.huaweicasestudy.payload.request.project.PlanningRequest;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.List;

public class PercentageSumValidator implements ConstraintValidator<ValidPercentageSum, List<PlanningRequest>> {

    @Override
    public boolean isValid(List<PlanningRequest> value, ConstraintValidatorContext context) {
        if (value == null || value.isEmpty()) {
            return true;
        }

        int totalPercentage = value.stream()
                .mapToInt(PlanningRequest::percentage)
                .sum();
        return totalPercentage == 100;
    }
}