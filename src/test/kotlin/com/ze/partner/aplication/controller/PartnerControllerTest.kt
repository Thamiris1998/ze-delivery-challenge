package com.ze.partner.aplication.controller

import com.github.javafaker.Faker
import com.jayway.jsonpath.JsonPath.read
import com.ze.partner.infrastructure.provider.ObjectMapperProvider.readJsonResource
import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import org.testcontainers.containers.FixedHostPortGenericContainer

@ExtendWith(SpringExtension::class)
@SpringBootTest
@AutoConfigureMockMvc
class PartnerControllerTest {

    @Autowired
    protected lateinit var mockMvc: MockMvc

    companion object {
        private val database = Container("mongo")
            .withFixedExposedPort(27017,27017)
        val pinheirosJson = readJsonResource("partner/pinheiros")
        val consolacaoJson = readJsonResource("partner/consolacao")
        val itaimJson = readJsonResource("partner/itaim")
        val jardinsJson = readJsonResource("partner/jardins")

        @BeforeAll
        @JvmStatic
        fun setUpClass() {
            database.start()
        }

        @AfterAll
        @JvmStatic
        fun tearDownClass() {
            database.stop()
        }
    }

    @Test
    @Throws(Exception::class)
    fun  `given call invalid partner_id then return 404 status code`() {
        mockMvc.perform(
            MockMvcRequestBuilders
                .get("/partner/${Faker.instance().idNumber()}")
                .accept(MediaType.APPLICATION_JSON)
        ).andExpect(MockMvcResultMatchers.status().isNotFound)
    }

    @Test
    fun `given valid partner then return 201 status code`() {
        mockMvc.perform(
            MockMvcRequestBuilders
                .post("/partner")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jardinsJson)
        )
            .andExpect(MockMvcResultMatchers.status().isCreated)
    }

    @Test
    fun `given the same partner 2 times then return 409 status code`() {
        mockMvc.perform(
            MockMvcRequestBuilders
                .post("/partner")
                .contentType(MediaType.APPLICATION_JSON)
                .content(itaimJson)
        ).andDo {
            mockMvc.perform(
                MockMvcRequestBuilders
                    .post("/partner")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(itaimJson))
                    .andExpect(MockMvcResultMatchers.status().isConflict)
        }
    }

    @Test
    fun  `given call valid partner_id then return 200 status code`() {
        mockMvc.perform(
            MockMvcRequestBuilders
                .post("/partner")
                .contentType(MediaType.APPLICATION_JSON)
                .content(pinheirosJson))
            .andDo {
            mockMvc.perform(
                MockMvcRequestBuilders
                    .get("/partner/${read<String>(it.response.contentAsString, "$.id")}")
                    .accept(MediaType.APPLICATION_JSON)
            ).andExpect(MockMvcResultMatchers.status().isOk)
        }
    }


    @Test
    fun  `given a lat and long valid then return the nearest partner with 200 status code`() {
        mockMvc.perform(
            MockMvcRequestBuilders
                .post("/partner")
                .contentType(MediaType.APPLICATION_JSON)
                .content(pinheirosJson))
            .andDo {
                mockMvc.perform(
                    MockMvcRequestBuilders
                        .get("/partner/nearest/long/30/lat/35")
                        .accept(MediaType.APPLICATION_JSON)
                ).andExpect(MockMvcResultMatchers.status().isOk)
            }
    }

    @Test
    fun  `given a lat and long valid then return the nearest between 2 partners with 200 status code`() {
        mockMvc.perform(
            MockMvcRequestBuilders
                .post("/partner")
                .contentType(MediaType.APPLICATION_JSON)
                .content(pinheirosJson))
            .andDo {
                mockMvc.perform(
                    MockMvcRequestBuilders
                        .post("/partner")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(consolacaoJson))
                    .andDo {



                        mockMvc.perform(
                            MockMvcRequestBuilders
                                .get("/partner/nearest/long/17/lat/35")
                                .accept(MediaType.APPLICATION_JSON)

                        ).andExpect(MockMvcResultMatchers.status().isOk)
                            .andExpect(MockMvcResultMatchers.jsonPath("$.ownerName")
                                .value(read<String>(it.response.contentAsString, "$.ownerName")))
                    }
            }
    }


}

internal class Container(imageName: String) : FixedHostPortGenericContainer<Container>(imageName)