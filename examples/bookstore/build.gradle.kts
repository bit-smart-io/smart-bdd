//apply(from = "../gradle/dependencies.gradle")
plugins {
    `java-library`
    id("org.springframework.boot") version "2.5.3"
}

apply(plugin = "io.spring.dependency-management")

group = "io.bitsmart.bdd.example"
version = "1.0-SNAPSHOT"
description = "Bookstore example"

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-actuator")
    implementation("org.springframework.boot:spring-boot-starter-web")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation(project(":report"))
}