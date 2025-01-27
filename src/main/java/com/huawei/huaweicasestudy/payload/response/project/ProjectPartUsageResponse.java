package com.huawei.huaweicasestudy.payload.response.project;

import java.util.HashMap;

public record ProjectPartUsageResponse(
        Long projectId,
        int month,
        HashMap<Long, Integer> partUsage
) {
}