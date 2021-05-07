plugins {
    java
}

group "org.example"
version "1.0-SNAPSHOT"
description = "Generate the JUnit Report"

repositories {
    mavenCentral()
}

dependencies {
    implementation(project(":wordify"))

    implementation("com.fasterxml.jackson.core:jackson-databind:2.12.1")
    implementation("com.fasterxml.jackson.core:jackson-annotations:2.12.1")

    implementation("org.junit.jupiter:junit-jupiter-api:5.7.0")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.7.0")

    testImplementation("org.junit.jupiter:junit-jupiter-params:5.7.0")
    testImplementation("org.junit.platform:junit-platform-runner:1.7.0")

    testImplementation("org.assertj:assertj-core:3.11.1")
    testImplementation("org.mockito:mockito-all:1.10.19")
}

tasks.test {
    useJUnitPlatform()
    exclude("**/ClassUnderTest.class")
    exclude("**/undertest")
}