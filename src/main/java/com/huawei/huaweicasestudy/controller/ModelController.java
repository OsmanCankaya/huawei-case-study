package com.huawei.huaweicasestudy.controller;

import com.huawei.huaweicasestudy.payload.request.model.CreateModelRequest;
import com.huawei.huaweicasestudy.payload.request.model.UpdateModelPartQuantityRequest;
import com.huawei.huaweicasestudy.payload.response.error.ValidationErrorResponse;
import com.huawei.huaweicasestudy.payload.response.model.CreateModelResponse;
import com.huawei.huaweicasestudy.payload.response.model.ModelDetailsResponse;
import com.huawei.huaweicasestudy.payload.response.model.ModelResponse;
import com.huawei.huaweicasestudy.payload.response.model.UpdateModelPartQuantityResponse;
import com.huawei.huaweicasestudy.service.ModelService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.websocket.server.PathParam;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/model")
@RequiredArgsConstructor
@Validated
@Tag(name = "Model", description = "It related to creating, reading, updating, deleting models ")
public class ModelController {

    private final ModelService modelService;

    @Operation(
            summary = "Create a new model",
            description = "Creates a new model with the specified details.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Model created successfully",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = CreateModelResponse.class))
                    ),
                    @ApiResponse(responseCode = "500", description = "Internal Server Error"),
                    @ApiResponse(responseCode = "400", description = "Invalid Input Data",
                            content = @Content(schema = @Schema(implementation = ValidationErrorResponse.class)))
            })
    @PostMapping
    public ResponseEntity<CreateModelResponse> createModel(
            @Valid
            @RequestBody
            CreateModelRequest createModelRequest) {
        CreateModelResponse response = modelService.saveModel(createModelRequest);
        return ResponseEntity.ok(response);
    }

    @Operation(
            summary = "Updates a part quantity of model ",
            description = "Updates a part quantiy of model by mode ID value with the specified details",
            responses = {
                    @ApiResponse(responseCode = "200", description = "model updated successfully",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = UpdateModelPartQuantityResponse.class))
                    ),
                    @ApiResponse(responseCode = "500", description = "Internal Server Error"),
                    @ApiResponse(responseCode = "404", description = "model not found"),
                    @ApiResponse(responseCode = "400", description = "Invalid Input Data",
                            content = @Content(schema = @Schema(implementation = ValidationErrorResponse.class)))
            })
    @PutMapping("/{modelId}/update-part-quantity")
    public ResponseEntity<UpdateModelPartQuantityResponse> updateModelPartQuantity(
            @PathVariable
            @Parameter(description = "model id", required = true)
            Long modelId,
            @Valid
            @RequestBody
            UpdateModelPartQuantityRequest updateModelPartQuantityRequest) {
        UpdateModelPartQuantityResponse response = modelService.updateModelPartQuantity(modelId, updateModelPartQuantityRequest);
        return ResponseEntity.ok(response);
    }

    @Operation(
            summary = "Gets all models",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Get All models  successfully",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ModelResponse.class))
                    ),
                    @ApiResponse(responseCode = "500", description = "Internal Server Error"),
                    @ApiResponse(responseCode = "400", description = "Invalid Input Data",
                            content = @Content(schema = @Schema(implementation = ValidationErrorResponse.class)))
            })
    @GetMapping()
    public ResponseEntity<Page<ModelResponse>> getModels(
            @Parameter(description = "Page number (pages starts from Zero )", required = false)
            @Min(0) @PathParam("page") Integer page
    ) {
        Page<ModelResponse> modelResponses = modelService.findAll(page);
        return ResponseEntity.ok(modelResponses);
    }

    @Operation(
            summary = "Get model details",
            description = "Get model details (including part quantity) by id",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Get model details  successfully",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ModelDetailsResponse.class))
                    ),
                    @ApiResponse(responseCode = "500", description = "Internal Server Error"),
                    @ApiResponse(responseCode = "404", description = "model not found"),
                    @ApiResponse(responseCode = "400", description = "Invalid Input Data",
                            content = @Content(schema = @Schema(implementation = ValidationErrorResponse.class)))
            })
    @GetMapping("/{modelId}")
    public ResponseEntity<ModelDetailsResponse> getModelDetails(
            @PathVariable
            @Parameter(description = "Model id", required = true)
            Long modelId) {
        ModelDetailsResponse modelDetailsResponse = modelService.findModelDetails(modelId);
        return ResponseEntity.ok(modelDetailsResponse);
    }
}
