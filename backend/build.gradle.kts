plugins {
    kotlin("jvm") version "2.0.21"
    id("io.ktor.plugin") version "2.3.11"
    kotlin("plugin.serialization") version "2.0.21"
}

group = "com.example.kavyakanaja.backend"
version = "0.0.1"

application {
    mainClass.set("com.example.kavyakanaja.backend.ApplicationKt")
    
    val isDevelopment: Boolean = project.ext.has("development")
    applicationDefaultJvmArgs = listOf("-Dio.ktor.development=$isDevelopment")
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("io.ktor:ktor-server-core-jvm")
    implementation("io.ktor:ktor-server-netty-jvm")
    implementation("io.ktor:ktor-server-content-negotiation-jvm")
    implementation("io.ktor:ktor-serialization-kotlinx-json-jvm")
    implementation("io.ktor:ktor-server-cors-jvm")
    implementation("io.ktor:ktor-server-call-logging-jvm")
    implementation("ch.qos.logback:logback-classic:1.4.14")
}
