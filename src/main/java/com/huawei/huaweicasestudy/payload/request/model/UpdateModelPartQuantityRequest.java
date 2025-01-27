package com.huawei.huaweicasestudy.payload.request.model;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record UpdateModelPartQuantityRequest(
        @NotNull
        Long partId,
        @Min(value = 1)
        int quantity
) {
}