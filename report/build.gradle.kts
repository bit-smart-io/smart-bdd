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
    id("smart-bdd.java-lib")
    id("maven-publish")
}

repositories {
    mavenCentral()
}

dependencies {
    api(project(":wordify"))
    api(project(":test-utils"))

    implementation("com.fasterxml.jackson.core:jackson-databind:2.12.1")
    implementation("com.fasterxml.jackson.core:jackson-annotations:2.12.1")

    implementation("org.thymeleaf:thymeleaf:3.0.12.RELEASE")

    testImplementation("org.mockito:mockito-all:1.10.19")
    testImplementation("com.google.jimfs:jimfs:1.1")
}

publishing {
    publications {
        create<MavenPublication>("maven") {
            group = "io.bit-smart.bdd"
            version = "1.0-SNAPSHOT"
            description = "Generate the JUnit Report"
            from(components["java"])
        }
    }
}