import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {

    kotlin("jvm") version "1.6.0"
    application
}

group = "me.user"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))

    testImplementation ("io.mockk:mockk:1.12.3")
    testImplementation("junit:junit:4.13")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.0")

    runtimeOnly("org.jetbrains.kotlinx:kotlinx-coroutines-swing:1.6.0")
}

tasks.test {
    useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}

application {
    mainClass.set("MainKt")
}