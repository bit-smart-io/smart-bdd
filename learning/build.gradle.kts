//apply(from = "../gradle/dependencies.gradle")
plugins {
    `java-library`
}

group = "io.bitsmart.bdd.learning"
version = "1.0-SNAPSHOT"
description = "Project for learning"

repositories {
    mavenCentral()
}

dependencies {
    implementation("com.fasterxml.jackson.core:jackson-databind:2.12.1")
    implementation("com.fasterxml.jackson.core:jackson-annotations:2.12.1")

    implementation("org.junit.jupiter:junit-jupiter-api:5.7.0")
    runtimeOnly("org.junit.jupiter:junit-jupiter-engine:5.7.0")

    implementation("org.junit.jupiter:junit-jupiter-params:5.7.0")
    implementation("org.junit.platform:junit-platform-runner:1.7.0")

    testImplementation("org.assertj:assertj-core:3.11.1")
}