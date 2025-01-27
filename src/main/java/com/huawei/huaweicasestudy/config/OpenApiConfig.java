package com.huawei.huaweicasestudy.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;

@OpenAPIDefinition(
        info = @Info(
                contact = @Contact(
                        name = "Osman ÇANKAYA",
                        url = "https://www.huawei.com/tr/",
                        email = "osmancankayabilmuh@gmail.com"
                ),
                title = "Huawei Case Study",
                version = "1.0.0"
        )
)
public class OpenApiConfig {
}
