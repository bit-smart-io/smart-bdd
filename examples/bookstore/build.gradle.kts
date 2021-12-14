/*
 * Smart BDD - The smart way to do behavior-driven development.
 * Copyright (C)  2021  James Bayliss
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */

plugins {
    `java-library`
//    `java-test-fixtures` //TODO use this if we want to export test code to be used in the fts
    id("org.springframework.boot") version "2.5.3"
    id("io.spring.dependency-management") version "1.0.11.RELEASE"
}

group = "io.bitsmart.bdd.example"
version = "1.0-SNAPSHOT"
description = "Bookstore example"

repositories {
    mavenCentral()
}

//java {
//    toolchain {
//        languageVersion.set(JavaLanguageVersion.of(11))
//    }
//}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-actuator")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("com.github.tomakehurst:wiremock-jre8:2.31.0")
    implementation("org.apache.httpcomponents.client5:httpclient5:5.1")
    implementation("org.apache.httpcomponents.client5:httpclient5-fluent:5.1")

    testImplementation("org.springframework.boot:spring-boot-starter-test")

    testImplementation(project(":report"))
    testImplementation(project(":test-utils")) // should this be an additional dependency or report api?
}

tasks.test {
    useJUnitPlatform()
}