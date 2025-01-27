package com.huawei.huaweicasestudy.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.web.config.EnableSpringDataWebSupport;

import static org.springframework.data.web.config.EnableSpringDataWebSupport.PageSerializationMode.VIA_DTO;

//PageImpl instances to be wrapped into PagedModel instances before rendering them as JSON to make sure the representation stays stable even if PageImpl is changed.
@EnableSpringDataWebSupport(pageSerializationMode = VIA_DTO)
@Configuration
public class PageableConfig {
}
