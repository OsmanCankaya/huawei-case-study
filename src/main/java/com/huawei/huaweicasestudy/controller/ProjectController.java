package com.huawei.huaweicasestudy.controller;

import com.huawei.huaweicasestudy.payload.request.project.CreateFixedProjectRequest;
import com.huawei.huaweicasestudy.payload.request.project.CreateMonthlyProjectRequest;
import com.huawei.huaweicasestudy.payload.request.project.CreateWeeklyProjectRequest;
import com.huawei.huaweicasestudy.payload.response.error.ValidationErrorResponse;
import com.huawei.huaweicasestudy.payload.response.project.CreateProjectResponse;
import com.huawei.huaweicasestudy.payload.response.project.ProjectPartUsageResponse;
import com.huawei.huaweicasestudy.service.ProjectService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.hibernate.validator.constraints.Range;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/project")
@RequiredArgsConstructor
@Validated
@Tag(name = "Project", description = "It related to creating project and calculation of part usage")
public class ProjectController {
    private final ProjectService projectService;

    @Operation(
            summary = "Create a new fixed planing project",
            description = "Creates a new fixed planing  project with the specified details.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Project created successfully",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = CreateProjectResponse.class))
                    ),
                    @ApiResponse(responseCode = "500", description = "Internal Server Error"),
                    @ApiResponse(responseCode = "400", description = "Invalid Input Data",
                            content = @Content(schema = @Schema(implementation = ValidationErrorResponse.class)))
            })
    @PostMapping("/fixed")
    public ResponseEntity<CreateProjectResponse> createFixedProject(
            @Valid
            @RequestBody
            CreateFixedProjectRequest request) {
        CreateProjectResponse response = projectService.saveProject(request);
        return ResponseEntity.ok(response);
    }

    @Operation(
            summary = "Create a new fixed monthly project",
            description = "Creates a new fixed monthly  project with the specified details.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Project created successfully",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = CreateProjectResponse.class))
                    ),
                    @ApiResponse(responseCode = "500", description = "Internal Server Error"),
                    @ApiResponse(responseCode = "400", description = "Invalid Input Data",
                            content = @Content(schema = @Schema(implementation = ValidationErrorResponse.class)))
            })
    @PostMapping("/monthly")
    public ResponseEntity<CreateProjectResponse> createMonthlyProject(
            @Valid
            @RequestBody
            CreateMonthlyProjectRequest request) {
        CreateProjectResponse response = projectService.saveProject(request);
        return ResponseEntity.ok(response);
    }

    @Operation(
            summary = "Create a new weekly monthly project",
            description = "Creates a new weekly monthly  project with the specified details.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Project created successfully",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = CreateProjectResponse.class))
                    ),
                    @ApiResponse(responseCode = "500", description = "Internal Server Error"),
                    @ApiResponse(responseCode = "400", description = "Invalid Input Data",
                            content = @Content(schema = @Schema(implementation = ValidationErrorResponse.class)))
            })
    @PostMapping("/weekly")
    public ResponseEntity<CreateProjectResponse> createWeeklyProject(
            @Valid
            @RequestBody
            CreateWeeklyProjectRequest request) {
        CreateProjectResponse response = projectService.saveProject(request);
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Get a Part usages",
            description = "Calculates Part usages in project based on the provided project ID and mounth.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Part Usage calculated",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ProjectPartUsageResponse.class))
                    ),
                    @ApiResponse(responseCode = "404", description = "Project not found"),
                    @ApiResponse(responseCode = "400", description = "Invalid Input Data",
                            content = @Content(schema = @Schema(implementation = ValidationErrorResponse.class))),
                    @ApiResponse(responseCode = "500", description = "Internal Server Error"),
            })
    @GetMapping("/{projectId}/part-usage")
    public ResponseEntity<ProjectPartUsageResponse> calculatePartUsageInProject(
            @PathVariable
            @Parameter(description = "ID value for the project you need", required = true)
            Long projectId,
            @RequestParam
            @Range(min = 1, max = 12)
            @Parameter(description = "month value for calculate", required = true)
            int monthToCalculate
    ) {
        ProjectPartUsageResponse projectPartUsageResponse = projectService.calculatePartUsageInProject(projectId, monthToCalculate);
        return ResponseEntity.ok(projectPartUsageResponse);
    }
}
