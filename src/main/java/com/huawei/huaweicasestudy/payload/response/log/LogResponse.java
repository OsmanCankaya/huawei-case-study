package com.huawei.huaweicasestudy.payload.response.log;

import com.huawei.huaweicasestudy.entity.OperationType;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

public record LogResponse(
        Long id,
        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
        LocalDateTime createdTime,
        OperationType operationType,
        String methodName
) {
}
