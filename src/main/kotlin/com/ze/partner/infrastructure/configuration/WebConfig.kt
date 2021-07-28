
package com.ze.partner.infrastructure.configuration

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.InterceptorRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@Configuration
class WebConfig : WebMvcConfigurer {

    @Autowired
    lateinit var applicationLogInterceptor: ApplicationLogInterceptor

    override fun addInterceptors(registry: InterceptorRegistry) {
        registry.addInterceptor(applicationLogInterceptor)
    }
}