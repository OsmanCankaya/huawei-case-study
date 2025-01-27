package com.huawei.huaweicasestudy.payload.request.project;

import jakarta.validation.constraints.NotNull;

public record ModelRequest(
        @NotNull
        Long id
) {
}
