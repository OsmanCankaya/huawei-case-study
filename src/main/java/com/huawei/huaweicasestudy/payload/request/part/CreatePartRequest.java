package com.huawei.huaweicasestudy.payload.request.part;

import jakarta.validation.constraints.NotBlank;

public record CreatePartRequest(
        @NotBlank
        String name
) {
}
