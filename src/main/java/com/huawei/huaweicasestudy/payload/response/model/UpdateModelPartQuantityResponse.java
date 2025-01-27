package com.huawei.huaweicasestudy.payload.response.model;

public record UpdateModelPartQuantityResponse(
        Long modelId,
        Long partId,
        int quantity
) {
}