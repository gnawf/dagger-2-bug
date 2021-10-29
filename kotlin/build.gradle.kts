import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.5.31"
    kotlin("kapt") version "1.5.31"
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("com.google.dagger:dagger:2.40")
    kapt("com.google.dagger:dagger-compiler:2.40")
}

tasks.withType<KotlinCompile>() {
    kotlinOptions.jvmTarget = "11"
}
