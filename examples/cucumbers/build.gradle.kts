//apply(from = "../gradle/dependencies.gradle")
plugins {
    `java-library`
}


group = "io.bitsmart.bdd.example"
version = "1.0-SNAPSHOT"
description = "Eating cucumbers example"

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.junit.jupiter:junit-jupiter-api:5.7.0")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.7.0")
    testImplementation("org.junit.jupiter:junit-jupiter-params:5.7.0")
    testImplementation("org.junit.platform:junit-platform-runner:1.7.0")

    testImplementation("org.assertj:assertj-core:3.11.1")
    testImplementation("org.mockito:mockito-all:1.10.19")
    testImplementation(project(":report"))
    testImplementation(project(":test-utils")) // should this be an additional depedecy or report api?
}

tasks.test {
    useJUnitPlatform()
    exclude("**/ClassUnderTest.class")
    exclude("**/undertest")
}