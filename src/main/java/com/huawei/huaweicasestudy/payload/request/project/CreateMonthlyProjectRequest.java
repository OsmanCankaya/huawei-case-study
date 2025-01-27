package com.huawei.huaweicasestudy.payload.request.project;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

import java.util.List;

public record CreateMonthlyProjectRequest(
        @NotBlank
        String name,
        @Valid
        @NotEmpty
        List<MonthlyProjectGoalRequest> projectGoals
) {
}
