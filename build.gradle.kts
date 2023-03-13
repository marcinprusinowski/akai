plugins {
    kotlin("jvm") version "1.8.0"
    application
}

group = "pl.akai"
version = "1.0"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
    // https://mvnrepository.com/artifact/com.fasterxml.jackson.module/jackson-module-kotlin
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin:2.14.2")

}

tasks.test {
    useJUnitPlatform()
}


application {
    mainClass.set("MainKt")
}