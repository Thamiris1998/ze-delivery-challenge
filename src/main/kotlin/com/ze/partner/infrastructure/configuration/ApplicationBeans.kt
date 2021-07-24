package com.ze.partner.infrastructure.configuration

import com.ze.partner.infrastructure.provider.ObjectMapperProvider
import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.info.Info
import io.swagger.v3.oas.models.info.License
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class ApplicationBeans {

    @Bean
    fun objectMapper() {
       ObjectMapperProvider.provide()

    }

    @Bean
    fun customOpenAPI(): OpenAPI {
        return OpenAPI()
            .info(
                Info()
                    .title("Zé Partner Challenge")
                    .version("1.0")
                    .description("Application responsible for provide and manage Zé Partners")
                    .termsOfService("http://swagger.io/terms/")
                    .license(License().name("Apache 2.0").url("http://springdoc.org"))
            )
    }
}