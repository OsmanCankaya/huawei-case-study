package com.huawei.huaweicasestudy.payload.request.log;

import com.huawei.huaweicasestudy.entity.OperationType;
import com.huawei.huaweicasestudy.controller.validation.ValidDateRange;
import jakarta.validation.constraints.Min;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;

@ValidDateRange
public record LogSearchRequest(
        @RequestParam(required = false)
        OperationType operationType,

        @RequestParam(required = false)
        String methodName,

        @RequestParam(required = false)
        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
        LocalDateTime start,

        @RequestParam(required = false)
        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
        LocalDateTime end,

        @Min(0)
        @RequestParam(required = false)
        Integer page
) {
}
