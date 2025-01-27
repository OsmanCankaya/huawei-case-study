package com.huawei.huaweicasestudy.repository.projection;

public record ProjectCalculationInfo(
        Long projectId,
        int goal,
        Long modelId,
        int percentage,
        Long partId,
        int quantity) {
}
