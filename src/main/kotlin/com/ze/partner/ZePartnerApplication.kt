package com.ze.partner

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.runApplication

@SpringBootApplication
@ConfigurationPropertiesScan
class ZePartnerApplication

fun main(args: Array<String>) {
	runApplication<ZePartnerApplication>(*args)
}
