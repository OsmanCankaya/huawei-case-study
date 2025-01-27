package com.huawei.huaweicasestudy.mapper;

import com.huawei.huaweicasestudy.entity.Part;
import com.huawei.huaweicasestudy.payload.request.part.CreatePartRequest;
import com.huawei.huaweicasestudy.payload.response.part.CreatePartResponse;
import com.huawei.huaweicasestudy.payload.response.part.PartResponse;
import com.huawei.huaweicasestudy.payload.response.part.UpdatePartResponse;
import com.huawei.huaweicasestudy.repository.projection.PartItemProjection;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface PartMapper {

    Part mapFromCreatePartRequest(CreatePartRequest createPartRequest);

    CreatePartResponse mapToCreatePartResponse(Part part);

    UpdatePartResponse mapToUpdatePartResponse(Part part);

    PartResponse mapToPartResponse(PartItemProjection part);
}
