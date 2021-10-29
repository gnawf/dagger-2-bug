plugins {
    java
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("com.google.dagger:dagger:2.40")
    annotationProcessor("com.google.dagger:dagger-compiler:2.40")
}
