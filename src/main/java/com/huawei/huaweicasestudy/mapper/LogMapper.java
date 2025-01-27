package com.huawei.huaweicasestudy.mapper;

import com.huawei.huaweicasestudy.entity.Log;
import com.huawei.huaweicasestudy.payload.response.log.LogResponse;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface LogMapper {

    LogResponse mapToLogResponse(Log part);

    List<LogResponse> mapToLogResponse(List<Log> part);

}
