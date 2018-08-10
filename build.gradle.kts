import org.jetbrains.kotlin.gradle.dsl.Coroutines
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    application
    kotlin("jvm") version "1.2.60"
}

application {
    mainClassName = "MainKt"
}

kotlin {
    experimental.coroutines = Coroutines.ENABLE
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}

repositories {
    jcenter()
    maven("https://dl.bintray.com/kotlin/ktor")
}

dependencies {
    compile(kotlin("stdlib-jdk8"))
    // The same with Netty and CIO
    compile("io.ktor:ktor-server-jetty:0.9.3")
    compile("ch.qos.logback:logback-classic:1.2.1")
}
