import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	id("org.springframework.boot") version "2.5.3"
	id("io.spring.dependency-management") version "1.0.11.RELEASE"
	kotlin("jvm") version "1.5.21"
	kotlin("plugin.spring") version "1.5.21"
}

group = "com.ze"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_11

repositories {
	mavenCentral()
}
val slf4jVersion by extra { "1.7.30" }
val openApiVersion by extra { "1.2.32" }
val testContainersVersion by extra { "1.15.3" }
val jacksonVersion by extra { "2.12.1" }

dependencies {
	// DATABASE
	implementation("org.springframework.boot:spring-boot-starter-data-mongodb")

	// KOTLIN
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")

	// TEST ENGINE
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation("org.testcontainers:junit-jupiter")
	testImplementation("org.testcontainers:mongodb")

	//SWAGGER
	implementation("org.springdoc:springdoc-openapi-ui:$openApiVersion")

	// JACKSON
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin:$jacksonVersion")
	implementation("com.fasterxml.jackson.datatype:jackson-datatype-jsr310:$jacksonVersion")

	// LOGS
	implementation("org.slf4j:slf4j-api:$slf4jVersion")
}

dependencyManagement {
	imports {
		mavenBom("org.testcontainers:testcontainers-bom:$testContainersVersion")
	}
}

tasks.withType<KotlinCompile> {
	kotlinOptions {
		freeCompilerArgs = listOf("-Xjsr305=strict")
		jvmTarget = "11"
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
}
