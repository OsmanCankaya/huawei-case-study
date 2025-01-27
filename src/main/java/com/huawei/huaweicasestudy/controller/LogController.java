package com.huawei.huaweicasestudy.controller;

import com.huawei.huaweicasestudy.payload.request.log.LogSearchRequest;
import com.huawei.huaweicasestudy.payload.response.error.ValidationErrorResponse;
import com.huawei.huaweicasestudy.payload.response.log.LogResponse;
import com.huawei.huaweicasestudy.service.LogService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/log")
@RequiredArgsConstructor
@Tag(name = "Log", description = "It related to searching logs")
public class LogController {

    private final LogService logService;

    @Operation(
            summary = "Search logs",
            description = "Search logs with the specified details. NOTE: Pages starts from Zero",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Logs searched successfully",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = LogResponse.class))
                    ),
                    @ApiResponse(responseCode = "500", description = "Internal Server Error"),
                    @ApiResponse(responseCode = "400", description = "Invalid Input Data",
                            content = @Content(schema = @Schema(implementation = ValidationErrorResponse.class)))
            })
    @GetMapping("/search")
    public ResponseEntity<Page<LogResponse>> getLogs(
            @Valid LogSearchRequest logSearchRequestParam
    ) {
        Page<LogResponse> response = logService.getLogs(logSearchRequestParam);
        return ResponseEntity.ok(response);
    }
}
