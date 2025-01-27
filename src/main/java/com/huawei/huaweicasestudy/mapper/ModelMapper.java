package com.huawei.huaweicasestudy.mapper;

import com.huawei.huaweicasestudy.entity.Model;
import com.huawei.huaweicasestudy.entity.ModelPartQuantity;
import com.huawei.huaweicasestudy.payload.response.model.*;
import com.huawei.huaweicasestudy.repository.projection.ModelItemProjection;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ModelMapper {

    CreateModelResponse modelToCreateModelResponse(Model model);

    ModelResponse modelItemProjectionToModelResponse(ModelItemProjection part);

    @Mapping(source = "model.id", target = "modelId")
    @Mapping(source = "part.id", target = "partId")
    UpdateModelPartQuantityResponse toAddPartToModelResponse(ModelPartQuantity modelPartQuantity);

    // ModelPartQuantity -> PartQuantityResponse
    @Mapping(source = "part.id", target = "partId")
    @Mapping(source = "quantity", target = "quantity")
    PartQuantityResponse toPartQuantityResponse(ModelPartQuantity modelPartQuantity);

    // Model -> ModelDetailsResponse
    @Mapping(source = "id", target = "id")
    @Mapping(source = "name", target = "name")
    @Mapping(target = "parts", expression = "java(mapPartQuantities(model))")
    ModelDetailsResponse toModelDetailsResponse(Model model);

    // ModelPartQuantity list -> PartQuantityResponse list
    default List<PartQuantityResponse> mapPartQuantities(Model model) {
        return model.getPartQuantities().stream()
                .map(this::toPartQuantityResponse)
                .collect(Collectors.toList());
    }

}
