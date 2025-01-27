package com.huawei.huaweicasestudy.payload.request.model;

import jakarta.validation.constraints.NotBlank;

import java.util.List;

public record CreateModelRequest(
        @NotBlank
        String name,
        List<UpdateModelPartQuantityRequest> Parts
) {
}
