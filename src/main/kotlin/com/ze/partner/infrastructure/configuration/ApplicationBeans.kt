package com.ze.partner.infrastructure.configuration

import com.mongodb.client.MongoClient
import com.ze.partner.infrastructure.provider.ObjectMapperProvider
import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.info.Info
import io.swagger.v3.oas.models.info.License
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.domain.Sort
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.data.mongodb.core.index.GeoSpatialIndexType
import org.springframework.data.mongodb.core.index.GeospatialIndex
import org.springframework.data.mongodb.core.index.Index


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

    @Bean
    fun mongoTemplate(
        @Value("\${spring.data.mongodb.database:partner}") database: String, @Autowired mongoClient: MongoClient): MongoTemplate {
        val mongoTemplate = MongoTemplate(mongoClient, database)
        mongoTemplate.indexOps("partner").ensureIndex(Index("document", Sort.Direction.ASC).unique())
        mongoTemplate.indexOps("partner").ensureIndex(GeospatialIndex("coverageArea").typed(GeoSpatialIndexType.GEO_2DSPHERE))
        mongoTemplate.indexOps("partner").ensureIndex(GeospatialIndex("address").typed(GeoSpatialIndexType.GEO_2DSPHERE))
        return mongoTemplate
    }
}