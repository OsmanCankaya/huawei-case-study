package com.huawei.huaweicasestudy.payload.request.project;

import com.huawei.huaweicasestudy.controller.validation.ValidPercentageSum;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;

import java.util.List;

public record FixedProjectGoalRequest(
        @Positive
        int goal,
        @Valid
        @NotEmpty
        @ValidPercentageSum
        List<PlanningRequest> plannings
) {
}
