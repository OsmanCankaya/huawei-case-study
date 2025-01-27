package com.huawei.huaweicasestudy.controller;

import com.huawei.huaweicasestudy.payload.request.part.CreatePartRequest;
import com.huawei.huaweicasestudy.payload.request.part.UpdatePartRequest;
import com.huawei.huaweicasestudy.payload.response.error.ValidationErrorResponse;
import com.huawei.huaweicasestudy.payload.response.part.CreatePartResponse;
import com.huawei.huaweicasestudy.payload.response.part.PartResponse;
import com.huawei.huaweicasestudy.payload.response.part.UpdatePartResponse;
import com.huawei.huaweicasestudy.service.PartService;
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
@RequestMapping("/api/v1/part")
@RequiredArgsConstructor
@Validated
@Tag(name = "Part", description = "It related to creating, reading, updating, deleting parts ")
public class PartController {

    private final PartService partService;

    @Operation(
            summary = "Create a new part object",
            description = "Creates a new part object with the specified details.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Part created successfully",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = CreatePartResponse.class))
                    ),
                    @ApiResponse(responseCode = "500", description = "Internal Server Error"),
                    @ApiResponse(responseCode = "400", description = "Invalid Input Data",
                            content = @Content(schema = @Schema(implementation = ValidationErrorResponse.class)))
            })
    @PostMapping
    public ResponseEntity<CreatePartResponse> createPart(
            @Valid
            @RequestBody
            CreatePartRequest createPartRequest) {
        return ResponseEntity.ok(partService.savePart(createPartRequest));
    }

    @Operation(
            summary = "Updates a part object ",
            description = "Updates a part by ID value with the specified details",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Part updated successfully",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = UpdatePartResponse.class))
                    ),
                    @ApiResponse(responseCode = "500", description = "Internal Server Error"),
                    @ApiResponse(responseCode = "404", description = "Part not found"),
                    @ApiResponse(responseCode = "400", description = "Invalid Input Data",
                            content = @Content(schema = @Schema(implementation = ValidationErrorResponse.class)))
            })
    @PutMapping("/{partId}")
    public ResponseEntity<UpdatePartResponse> updatePart(
            @PathVariable
            @Parameter(description = "part id", required = true)
            Long partId,
            @Valid
            @RequestBody
            UpdatePartRequest updatePartRequest) {
        UpdatePartResponse response = partService.updatePart(partId, updatePartRequest);
        return ResponseEntity.ok(response);
    }

    @Operation(
            summary = "Gets all parts",
            description = "Gets all parts",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Get All Parts  successfully",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = PartResponse.class))
                    ),
                    @ApiResponse(responseCode = "500", description = "Internal Server Error"),
                    @ApiResponse(responseCode = "400", description = "Invalid Input Data",
                            content = @Content(schema = @Schema(implementation = ValidationErrorResponse.class)))
            })
    @GetMapping
    public ResponseEntity<Page<PartResponse>> getParts(
            @Parameter(description = "Page number (pages starts from Zero )", required = false)
            @Min(0) @PathParam("page") Integer page
    ) {
        Page<PartResponse> partResponses = partService.findAll(page);
        return ResponseEntity.ok(partResponses);
    }

    @Operation(
            summary = "Deletes a part object ",
            description = "Deletes a part by ID value",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Part deleted successfully",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = PartResponse.class))
                    ),
                    @ApiResponse(responseCode = "500", description = "Internal Server Error"),
                    @ApiResponse(responseCode = "409", description = "Part is not deleteable"),
                    @ApiResponse(responseCode = "400", description = "Invalid Input Data",
                            content = @Content(schema = @Schema(implementation = ValidationErrorResponse.class)))
            })
    @DeleteMapping("/{partId}")
    public ResponseEntity<Void> deletePart(
            @PathVariable
            @Parameter(description = "Part id", required = true)
            Long partId) {
        partService.delete(partId);
        return ResponseEntity.ok().build();
    }
}
