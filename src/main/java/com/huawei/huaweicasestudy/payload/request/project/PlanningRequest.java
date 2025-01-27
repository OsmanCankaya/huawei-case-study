package com.huawei.huaweicasestudy.payload.request.project;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Range;

public record PlanningRequest(
        @Valid
        @NotNull
        ModelRequest model,
        @Range(min = 1, max = 100)
        int percentage
) {
}
