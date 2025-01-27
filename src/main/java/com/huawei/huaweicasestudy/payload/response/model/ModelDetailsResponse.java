package com.huawei.huaweicasestudy.payload.response.model;

import java.util.List;

public record ModelDetailsResponse(
        Long id,
        String name,
        List<PartQuantityResponse> parts
) {
}