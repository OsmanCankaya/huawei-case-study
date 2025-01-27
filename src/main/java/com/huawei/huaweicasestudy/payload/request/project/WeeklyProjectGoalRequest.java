package com.huawei.huaweicasestudy.payload.request.project;

import com.huawei.huaweicasestudy.controller.validation.ValidPercentageSum;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;
import org.hibernate.validator.constraints.Range;

import java.util.List;

public record WeeklyProjectGoalRequest(
        @Positive
        int goal,
        @Range(min = 1, max = 12)
        int month,
        @Range(min = 1, max = 4)
        int weekInMonth,
        @Valid
        @NotEmpty
        @ValidPercentageSum
        List<PlanningRequest> plannings
) {
}
